package org.example.expedfacil.controller;

import org.example.expedfacil.controller.dto.conferencia.*;
import org.example.expedfacil.model.Carga;
import org.example.expedfacil.model.Produto;
import org.example.expedfacil.model.ProdutoEntrega;
import org.example.expedfacil.repository.CargaRepository;
import org.example.expedfacil.repository.ProdutoRepository;
import org.example.expedfacil.service.ConferenciaTempStorageService;
import org.example.expedfacil.util.ConversorLoteUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/conferencia")
public class ConferenciaController {

    private final ProdutoRepository produtoRepository;
    private final ConferenciaTempStorageService tempStorageService;
    private final CargaRepository cargaRepository;

    public ConferenciaController(ProdutoRepository produtoRepository,
                                 ConferenciaTempStorageService tempStorageService,
                                 CargaRepository cargaRepository) {
        this.produtoRepository = produtoRepository;
        this.tempStorageService = tempStorageService;
        this.cargaRepository = cargaRepository;
    }

    @PostMapping("/lotes")
    public ResponseEntity<?> registrarConferenciaDeLotes(
            @Valid @RequestBody CargaConferidaDTO dto) {

        // Buscar a carga original pelo número do embarque
        Carga cargaOriginal = cargaRepository.findByNumeroEmbarque(dto.getNumeroEmbarqueOriginal())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Carga com número " + dto.getNumeroEmbarqueOriginal() + " não encontrada."));

        // Extrair os números de entrega da carga original
        List<String> entregasOriginais = cargaOriginal.getEntregas().stream()
                .map(entrega -> String.valueOf(entrega.getNumeroEntrega()))
                .toList();

        // Extrair os números de entrega da conferência recebida
        List<String> entregasConferidas = dto.getEntregasConferidas().stream()
                .map(EntregaConferidaDTO::getNumeroEntrega)
                .toList();

        // Validação 1: quantidade total de entregas deve bater
        if (entregasConferidas.size() != entregasOriginais.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Número de entregas conferidas (" + entregasConferidas.size() +
                            ") não bate com o número de entregas da carga (" + entregasOriginais.size() + ").");
        }

        // Validação 2: cada entrega enviada deve existir na carga original
        for (String entregaEnviada : entregasConferidas) {
            if (!entregasOriginais.contains(entregaEnviada)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "A entrega número '" + entregaEnviada + "' não pertence à carga " + dto.getNumeroEmbarqueOriginal());
            }
        }

        // Validação 3: cada entrega deve conter exatamente os mesmos produtos que a entrega original
        for (EntregaConferidaDTO entregaConferida : dto.getEntregasConferidas()) {
            Integer numeroEntrega = Integer.valueOf(entregaConferida.getNumeroEntrega());


            // Buscar a entrega correspondente na carga original
            var entregaOriginal = cargaOriginal.getEntregas().stream()
                    .filter(e -> e.getNumeroEntrega().equals(numeroEntrega))
                    .findFirst()
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Entrega número " + numeroEntrega + " não encontrada na carga."));

            // Extrair códigos dos produtos da entrega original
            List<String> codigosOriginais = entregaOriginal.getProdutos().stream()
                    .map(ProdutoEntrega::getCodigoProduto)
                    .filter(codigo -> codigo != null)
                    .sorted()
                    .toList();

            // Extrair códigos dos produtos conferidos
            List<String> codigosConferidos = entregaConferida.getProdutos().stream()
                    .map(ProdutoConferidoDTO::getCodigoProduto)
                    .filter(codigo -> codigo != null)
                    .sorted()
                    .toList();

            // Comparar listas
            if (!codigosOriginais.equals(codigosConferidos)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Os produtos conferidos na entrega " + numeroEntrega + " não correspondem aos produtos da entrega original.");
            }
        }


        for (EntregaConferidaDTO entrega : dto.getEntregasConferidas()) {
            for (ProdutoConferidoDTO produto : entrega.getProdutos()) {

                // Buscar nome do produto no banco de dados
                Produto produtoEncontrado = produtoRepository.findById(produto.getCodigoProduto())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "Produto com código " + produto.getCodigoProduto() + " não encontrado."));

                // Setar nome automaticamente no DTO
                produto.setNomeProduto(produtoEncontrado.getNome());

                // Validar se soma dos lotes bate com a quantidade total
                int soma = produto.getLotes().stream()
                        .mapToInt(LoteConferidoDTO::getQuantidade)
                        .sum();

                if (soma != produto.getQuantidadeTotal()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Quantidade total do produto " + produto.getCodigoProduto()
                                    + " não bate com a soma dos lotes.");
                }

                // Validar e preencher a data de produção de cada lote
                for (LoteConferidoDTO lote : produto.getLotes()) {
                    LocalDate dataProducao = ConversorLoteUtil.converterParaDataProducao(lote.getLote());

                    if (dataProducao.isAfter(LocalDate.now())) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "Lote " + lote.getLote() + " possui data de produção futura: "
                                        + dataProducao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    }

                    // Validar se a data de validade foi informada corretamente
                    if (lote.getValidade() == null || lote.getValidade().isBlank()) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "Data de validade do lote " + lote.getLote() + " está ausente ou inválida.");
                    }

                    // Verificar se a validade é igual ou maior que hoje
                    LocalDate validade;
                    try {
                        validade = LocalDate.parse(lote.getValidade(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    } catch (Exception e) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "Formato inválido para a data de validade do lote " + lote.getLote() + ". Use dd/MM/yyyy.");
                    }

                    if (validade.isBefore(LocalDate.now())) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "Data de validade do lote " + lote.getLote() + " está vencida: "
                                        + lote.getValidade());
                    }

                    // Setar a data de produção formatada
                    lote.setDataProducao(dataProducao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                }
            }
        }

        // Salvar a conferência temporariamente na memória
        tempStorageService.salvar(dto.getNumeroEmbarqueOriginal(), dto);

        return ResponseEntity.ok("Conferência de lotes registrada com sucesso.");
    }

    @GetMapping("/lotes")
    public ResponseEntity<?> listarConferenciasTemporarias() {
        Map<String, CargaConferidaDTO> todas = tempStorageService.listarTodas();
        return ResponseEntity.ok(todas);
    }

    @DeleteMapping("/lotes/{numeroEmbarqueOriginal}")
    public ResponseEntity<?> deletarConferenciaTemporaria(@PathVariable String numeroEmbarqueOriginal) {
        boolean removido = tempStorageService.remover(numeroEmbarqueOriginal);
        if (removido) {
            return ResponseEntity.ok("Conferência temporária removida com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma conferência encontrada para o embarque informado.");
        }
    }




}