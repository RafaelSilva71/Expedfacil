package org.example.expedfacil.service;

/** Imports */
import org.example.expedfacil.model.*;
import org.example.expedfacil.controller.CreateCargaDTO;
import org.example.expedfacil.controller.EntregaDTO;
import org.example.expedfacil.controller.ProdutoEntregaDTO;
import org.example.expedfacil.repository.CargaRepository;
import org.example.expedfacil.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

                // Buscar o nome do produto a partir do código
                Produto produtoCatalogo = produtoRepository.findById(produtoDTO.getCodigoProduto())
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

        // Salvar tudo no banco e retornar
        return cargaRepository.save(carga);

    }
}
