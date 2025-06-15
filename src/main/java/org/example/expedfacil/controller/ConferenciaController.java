package org.example.expedfacil.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;

import org.example.expedfacil.controller.dto.conferencia.*;
import org.example.expedfacil.controller.dto.conferencia.response.CargaConferidaResponseDTO;
import org.example.expedfacil.model.Carga;
import org.example.expedfacil.model.Produto;
import org.example.expedfacil.model.ProdutoEntrega;
import org.example.expedfacil.repository.CargaRepository;
import org.example.expedfacil.repository.ProdutoRepository;
import org.example.expedfacil.service.ConferenciaLotesService;
import org.example.expedfacil.util.ConversorLoteUtil;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/conferencia")
@Tag(name = "Conferência de Lotes", description = "APIs para gerenciar conferência de lotes")
public class ConferenciaController {

    private final ProdutoRepository produtoRepository;
    private final ConferenciaLotesService conferenciaLotesService;
    private final CargaRepository cargaRepository;

    public ConferenciaController(ProdutoRepository produtoRepository,
                                 ConferenciaLotesService conferenciaLotesService,
                                 CargaRepository cargaRepository) {
        this.produtoRepository = produtoRepository;
        this.conferenciaLotesService = conferenciaLotesService;
        this.cargaRepository = cargaRepository;
    }

    @Operation(
            summary = "Registrar conferência de lotes",
            description = "Registra a conferência de lotes para uma carga existente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Conferência de lotes registrada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos ou inconsistentes"),
                    @ApiResponse(responseCode = "404", description = "Carga não encontrada", content = @Content)
            }
    )
    @PostMapping("/lotes")
    public ResponseEntity<?> registrarConferenciaDeLotes(
            @Valid @RequestBody CargaConferidaDTO dto) {

        // Busca a carga original no banco
        Carga cargaOriginal = cargaRepository.findByNumeroEmbarque(dto.getNumeroEmbarqueOriginal())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Carga com número " + dto.getNumeroEmbarqueOriginal() + " não encontrada."));

        // Lista os números de entrega da carga original e da conferência recebida
        List<String> entregasOriginais = cargaOriginal.getEntregas().stream()
                .map(entrega -> String.valueOf(entrega.getNumeroEntrega()))
                .toList();

        List<String> entregasConferidas = dto.getEntregasConferidas().stream()
                .map(EntregaConferidaDTO::getNumeroEntrega)
                .toList();

        // Validação 1: quantidade de entregas deve bater
        if (entregasConferidas.size() != entregasOriginais.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Número de entregas conferidas não bate com a carga original.");
        }

        // Validação 2: cada entrega enviada deve pertencer à carga original
        for (String entregaEnviada : entregasConferidas) {
            if (!entregasOriginais.contains(entregaEnviada)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Entrega número '" + entregaEnviada + "' não pertence à carga.");
            }
        }

        // Validação 3: cada entrega deve conter exatamente os mesmos produtos da carga original
        for (EntregaConferidaDTO entregaConferida : dto.getEntregasConferidas()) {
            Integer numeroEntrega = Integer.valueOf(entregaConferida.getNumeroEntrega());

            var entregaOriginal = cargaOriginal.getEntregas().stream()
                    .filter(e -> e.getNumeroEntrega().equals(numeroEntrega))
                    .findFirst()
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Entrega número " + numeroEntrega + " não encontrada."));

            List<String> codigosOriginais = entregaOriginal.getProdutos().stream()
                    .map(ProdutoEntrega::getCodigoProduto)
                    .filter(codigo -> codigo != null)
                    .sorted()
                    .toList();

            List<String> codigosConferidos = entregaConferida.getProdutos().stream()
                    .map(ProdutoConferidoDTO::getCodigoProduto)
                    .filter(codigo -> codigo != null)
                    .sorted()
                    .toList();

            if (!codigosOriginais.equals(codigosConferidos)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Produtos conferidos na entrega " + numeroEntrega + " não correspondem aos originais.");
            }
        }

        // Validação por produto: nome, soma dos lotes e validade
        for (EntregaConferidaDTO entrega : dto.getEntregasConferidas()) {
            for (ProdutoConferidoDTO produto : entrega.getProdutos()) {

                // Preenche automaticamente o nome do produto
                Produto produtoEncontrado = produtoRepository.findById(produto.getCodigoProduto())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "Produto com código " + produto.getCodigoProduto() + " não encontrado."));
                produto.setNomeProduto(produtoEncontrado.getNome());

                // Valida se a soma das quantidades dos lotes bate com a quantidade total
                int soma = produto.getLotes().stream()
                        .mapToInt(LoteConferidoDTO::getQuantidade)
                        .sum();
                if (soma != produto.getQuantidadeTotal()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Quantidade total do produto " + produto.getCodigoProduto()
                                    + " não bate com a soma dos lotes.");
                }

                // Validação de cada lote
                for (LoteConferidoDTO lote : produto.getLotes()) {
                    LocalDate dataProducao = ConversorLoteUtil.converterParaDataProducao(lote.getLote());

                    if (dataProducao.isAfter(LocalDate.now())) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "Lote " + lote.getLote() + " possui data de produção futura.");
                    }

                    // Validação da data de validade
                    if (lote.getValidade() == null || lote.getValidade().isBlank()) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "Validade do lote " + lote.getLote() + " está ausente.");
                    }

                    LocalDate validade;
                    try {
                        validade = LocalDate.parse(lote.getValidade(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    } catch (Exception e) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "Formato inválido para validade do lote " + lote.getLote());
                    }

                    if (validade.isBefore(LocalDate.now())) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "Lote " + lote.getLote() + " está com validade vencida.");
                    }

                    // Salva a data de produção no DTO
                    lote.setDataProducao(dataProducao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                }
            }
        }

        // Salva a conferência no banco de dados
        conferenciaLotesService.salvar(dto.getNumeroEmbarqueOriginal(), dto);

        return ResponseEntity.ok("Conferência de lotes registrada com sucesso.");
    }

    @Operation(summary = "Listar todas as conferências salvas",
            description = "Retorna todas as conferências registradas no sistema")
    @GetMapping("/lotes")
    public ResponseEntity<?> listarConferenciasSalvas() {
        List<CargaConferidaResponseDTO> dtos = conferenciaLotesService.listarTodas();
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Deletar a conferência pelo número do embarque",
            description = "Remove uma conferência salva pelo número do embarque original",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Conferência removida com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Conferência não encontrada")
            })
    @DeleteMapping("/lotes/{numeroEmbarqueOriginal}")
    public ResponseEntity<?> deletarConferencia(
            @Parameter(description = "Número do embarque original da conferência a ser removida", required = true)
            @PathVariable String numeroEmbarqueOriginal) {
        boolean removido = conferenciaLotesService.remover(numeroEmbarqueOriginal);

        if (removido) {
            return ResponseEntity.ok("Conferência removida com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nenhuma conferência encontrada para o embarque informado.");
        }
    }
}
