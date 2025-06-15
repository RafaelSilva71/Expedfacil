package org.example.expedfacil.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.expedfacil.controller.dto.avaria.ProdutoAvariadoDTO;
import org.example.expedfacil.controller.dto.avaria.ProdutoAvariadoResponseDTO;
import org.example.expedfacil.service.AvariaCargaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avarias")
@Tag(name = "Avarias de Carga", description = "Gerencia os produtos avariados durante a conferência da carga")
public class AvariaCargaController {

    private final AvariaCargaService service;

    public AvariaCargaController(AvariaCargaService service) {
        this.service = service;
    }

    @Operation(summary = "Registrar avarias na carga",
            description = "Registra uma nova lista de produtos avariados associados a um número de embarque.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Avarias registradas com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Carga não encontrada", content = @Content)
            })
    @PostMapping
    public ResponseEntity<String> registrar(@Valid @RequestBody List<ProdutoAvariadoDTO> dtoList) {
        service.registrarAvarias(dtoList);
        return ResponseEntity.status(HttpStatus.CREATED).body("Avarias registradas com sucesso.");
    }

    @Operation(summary = "Listar todas as avarias",
            description = "Retorna todas as avarias de todas as cargas.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de avarias retornada com sucesso")
            })
    @GetMapping
    public ResponseEntity<List<ProdutoAvariadoResponseDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @Operation(summary = "Listar avarias por número de embarque",
            description = "Retorna todas as avarias associadas a uma carga específica.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Avarias retornadas com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Carga não encontrada", content = @Content)
            })
    @GetMapping("/{numeroEmbarque}")
    public ResponseEntity<List<ProdutoAvariadoResponseDTO>> listarPorEmbarque(
            @Parameter(description = "Número de embarque da carga") @PathVariable String numeroEmbarque) {
        return ResponseEntity.ok(service.listarPorNumeroEmbarque(numeroEmbarque));
    }

    @Operation(summary = "Atualizar observação de um produto avariado",
            description = "Atualiza a observação de um produto avariado (usado quando motivo é OUTROS).",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Observação atualizada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Produto avariado não encontrado", content = @Content)
            })
    @PutMapping("/{id}/observacao")
    public ResponseEntity<String> atualizarObservacao(
            @Parameter(description = "ID do produto avariado") @PathVariable Long id,
            @RequestBody String novaObservacao) {
        service.atualizarObservacao(id, novaObservacao);
        return ResponseEntity.ok("Observação atualizada com sucesso.");
    }

    @Operation(summary = "Deletar produto avariado",
            description = "Remove um produto avariado da lista.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Produto removido com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Produto avariado não encontrado", content = @Content)
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@Parameter(description = "ID do produto avariado") @PathVariable Long id) {
        service.deletarProdutoAvariado(id);
        return ResponseEntity.noContent().build();
    }
}
