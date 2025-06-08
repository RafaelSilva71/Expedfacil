package org.example.expedfacil.service;

/** Imports */
import org.example.expedfacil.controller.dto.*;
import org.example.expedfacil.model.*;
import org.example.expedfacil.model.enums.*;
import org.example.expedfacil.repository.CargaRepository;
import org.example.expedfacil.repository.LocalEstoqueProdutoRepository;
import org.example.expedfacil.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Classe responsável por conter a lógica de negócio relacionada à entidade Carga.
 *
 * Aqui são implementadas operações como criação, validação e processamento
 * de cargas, incluindo o vínculo com os produtos que compõem cada carga.
 *
 * Esta classe atua como ponte entre o Controller (entrada de dados) e o Repository
 * (acesso ao banco), aplicando as regras do sistema antes de persistir ou retornar dados.
 */

@Service
public class CargaService {

    @Autowired
    private CargaRepository cargaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private LocalEstoqueProdutoRepository localEstoqueProdutoRepository;

    //Configuração da leitura da carga e do produto em cache e buscando eles no cache
    @Cacheable(value = "cargas", key="#numeroEmbarque")
    public Optional<Carga> findByNumeroEmbarque(String numeroEmbarque) {
        System.out.println("DEBUG: Buscando carga em barque: '" + numeroEmbarque + "' no banco de dados (cache expirado/ausente)");
        return cargaRepository.findById(numeroEmbarque);
    }

    @Cacheable(value = "produtos", key = "#codigoProduto")
    public Optional<Produto> findProdutoByCodigo(String codigoProduto) {
        System.out.println("DEBUG: Buscando produto em barque: '" + codigoProduto + "'no no banco de dados (cache expirado/ausente)");
        return produtoRepository.findById(codigoProduto);
    }

    //Se uma nova carga é criada, a antiga (se existir) deve ser invalidada
    @Caching(evict = {
            @CacheEvict(value = "cargas", key = "#dto.numeroEmbarque"),
            @CacheEvict(value = "cargas", allEntries = true)
    })
    public Carga create(CreateCargaDTO dto) {
        // Primeiro verificar se já existe carga com esse numero de embarque
        if (cargaRepository.existsById(dto.getNumeroEmbarque())) {
            throw new RuntimeException("Já existe carga com esse numero embarque");
        }

        Carga carga = new Carga();
        carga.setNumeroEmbarque(dto.getNumeroEmbarque());
        carga.setDestino(dto.getDestino());
        carga.setNumeroEntregas(dto.getNumeroEntregas());
        carga.setTipoCarregamento(TipoCarregamento.valueOf(dto.getTipoCarregamento()));
        carga.setTransportadora(dto.getTransportadora());
        carga.setTipoVeiculo(TipoVeiculo.valueOf(dto.getTipoVeiculo()));
        carga.setPlacaCavalo(dto.getPlacaCavalo());
        carga.setPlacasCarreta(dto.getPlacasCarreta());
        carga.setNomeMotorista(dto.getNomeMotorista());
        carga.setTipoCarga(TipoCarga.valueOf(dto.getTipoCarga()));
        carga.setStatus(StatusCarga.CRIADA);
        carga.setCriadoPor("sistema"); // futuramente virá do usuário autenticado

        List<Entrega> listaEntregas = new ArrayList<>();
        double pesoTotalLiquido = 0.0;
        double pesoTotalBruto = 0.0;
        int totalCaixasCarga = 0;
        int contadorEntrega = 1;

        for (EntregaDTO entregaDTO : dto.getEntregas()) {   //Percorre cada entrega recebida
            Entrega entrega = new Entrega();

            entrega.setNumeroEntrega(contadorEntrega++);       // Numera cada entrega dentro da carga (1, 2, 3...)
            entrega.setDestinatario(entregaDTO.getDestinatario());
            entrega.setObservacao(entregaDTO.getObservacao());
            entrega.setTipoEntrega(TipoEntrega.valueOf(entregaDTO.getTipoEntrega()));
            entrega.setPesoLiquido(entregaDTO.getPesoLiquido());
            entrega.setPesoBruto(entregaDTO.getPesoBruto());
            entrega.setCarga(carga);                           //Liga cada entrega à sua carga mãe

            // Acumuladores de totais da carga
            pesoTotalLiquido += entregaDTO.getPesoLiquido();
            pesoTotalBruto += entregaDTO.getPesoBruto();

            List<ProdutoEntrega> listaProdutos = new ArrayList<>();
            int totalCaixasEntrega = 0;

            for (ProdutoEntregaDTO produtoDTO : entregaDTO.getProdutos()) {   //Percorre os produtos de cada entrega
                ProdutoEntrega produto = new ProdutoEntrega();
                produto.setCodigoProduto(produtoDTO.getCodigoProduto());

                // **** AQUI USAMOS O NOVO MÉTODO findProdutoByCodigo COM CACHE ****
                Produto produtoCatalogo = findProdutoByCodigo(produtoDTO.getCodigoProduto())
                        .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + produtoDTO.getCodigoProduto()));

                produto.setNomeProduto(produtoCatalogo.getNome());
                produto.setQuantidadeCaixas(produtoDTO.getQuantidadeCaixas());
                produto.setEntrega(entrega);                                    // Liga o produto à entrega correta

                listaProdutos.add(produto);

                totalCaixasEntrega += produtoDTO.getQuantidadeCaixas();
            }

            entrega.setProdutos(listaProdutos);
            entrega.setQuantidadeTotalCaixas(totalCaixasEntrega);

            totalCaixasCarga += totalCaixasEntrega;

            listaEntregas.add(entrega);
        }

        // Setar totais calculados na carga
        carga.setPesoTotalLiquido(pesoTotalLiquido);
        carga.setPesoTotalBruto(pesoTotalBruto);
        carga.setQuantidadeTotalCaixas(totalCaixasCarga);

        // Setar entregas na carga
        carga.setEntregas(listaEntregas);

        Carga cargaSalva = cargaRepository.save(carga);

        this.gerarResumoLocalEstoque(cargaSalva);

        return cargaSalva;
    }

    @CacheEvict(value = "cargas", key="#carga.numeroEmbarque")
    public LocalEstoqueDTO gerarResumoLocalEstoque(Carga carga) {
        Map<String, Integer> totaisPorProduto = new HashMap<>();

        // 1. Somar total de caixas por produto em todas as entregas da carga
        for (Entrega entrega : carga.getEntregas()) {
            for (ProdutoEntrega produto : entrega.getProdutos()) {
                totaisPorProduto.merge(
                        produto.getCodigoProduto(),
                        produto.getQuantidadeCaixas(),
                        Integer::sum
                );
            }
        }

        // 2. Montar lista de DTOs para visualização
        List<ResumoProdutoLocalDTO> listaResumo = new ArrayList<>();

        // 3. Montar lista de entidades para salvar no banco
        List<LocalEstoqueProduto> entidades = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : totaisPorProduto.entrySet()) {
            String codigo = entry.getKey();
            int totalCaixas = entry.getValue();

            Produto produtoCatalogo = findProdutoByCodigo(codigo)
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + codigo));//Mensagem formatada na carga para produto não encontrado

            int divisor = produtoCatalogo.getQuantCxFd();
            int inteiro = totalCaixas / divisor;
            int resto = totalCaixas % divisor;

            String resultado = inteiro + "Paletes e mais " + resto + " = " + totalCaixas;

            // DTO para exibição
            ResumoProdutoLocalDTO dto = new ResumoProdutoLocalDTO();
            dto.setCodigoProduto(codigo);
            dto.setNomeProduto(produtoCatalogo.getNome());
            dto.setResultado(resultado);
            dto.setLocalEstoque(null); // ainda não atribuído

            listaResumo.add(dto);

            // Entidade para salvar
            LocalEstoqueProduto entidade = new LocalEstoqueProduto();
            entidade.setNumeroEmbarque(carga.getNumeroEmbarque());
            entidade.setCodigoProduto(codigo);
            entidade.setNomeProduto(produtoCatalogo.getNome());
            entidade.setResultadoCalculo(resultado);
            entidade.setLocalEstoque(null); // será preenchido depois

            entidades.add(entidade);
        }

        // 4. Salvar todas as entidades no banco
        localEstoqueProdutoRepository.saveAll(entidades);

        // 5. Retornar o DTO com a lista formatada (opcional, para exibição)
        LocalEstoqueDTO localEstoqueDTO = new LocalEstoqueDTO();
        localEstoqueDTO.setNumeroEmbarque(carga.getNumeroEmbarque());
        localEstoqueDTO.setProdutos(listaResumo);

        return localEstoqueDTO;
    }



}
