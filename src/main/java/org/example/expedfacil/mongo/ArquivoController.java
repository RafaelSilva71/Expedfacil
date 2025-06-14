package org.example.expedfacil.mongo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.expedfacil.model.Carga;
import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Tag(name ="Arquivos",
    description="Contém todas as operações relativas aos recursos para o Arquivos contem o CRUD, Criado Especificamente para salvar imagens ! ")
@RestController
@RequestMapping("/arquivo")
public class ArquivoController {

    @Autowired
    private ArquivoService arquivoService;

    @Operation(
            summary = "Cria um novo Arquivo",
            description = "Recurso para criar um Novo Arquivo",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recurso criado com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = String.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Arquivo já cadastrado!",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Recurso não processado por dados de entrada inválidos",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )
    @PostMapping("/upload/{numeroEmbarque}")
    public ResponseEntity<String> uploadNotaFiscal(@RequestParam("file") MultipartFile file,
                                                   @PathVariable String numeroEmbarque) {
        try {
            String id = arquivoService.salvarNotaFiscalComNumeroEmbarque(file, numeroEmbarque);
            return ResponseEntity.ok("Nota fiscal salva com ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao salvar nota fiscal: " + e.getMessage());
        }
    }


    @Operation(
            summary = "Recupera uma nota fiscal informando a número da carga",
            description = "Retorna um arquivo associado a um arquvo a partir do ID fornecido.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Nota fiscal recuperada com sucesso",
                            content = @Content(
                                    mediaType = "application/octet-stream",
                                    schema = @Schema(type = "string", format = "binary")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Recurso não encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Erro interno ao buscar o arquivo"
                    )
            }
    )
    @GetMapping("/nota-fiscal/{numeroEmbarque}")
    public ResponseEntity<?> getNotaFiscalPorCarga(@PathVariable String numeroEmbarque) {
        try {
            GridFsResource arquivo = arquivoService.buscarNotaFiscalPorNumeroEmbarque(numeroEmbarque);

            if (arquivo == null || !arquivo.exists()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + arquivo.getFilename() + "\"")
                    .contentType(MediaType.parseMediaType(arquivo.getContentType()))
                    .body(new InputStreamResource(arquivo.getInputStream()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao buscar nota fiscal: " + e.getMessage());
        }
    }

    @Operation(
            summary = "Recupera um recurso do Arquivo por ID",
            description = "Retorna um arquivo associado a um arquvo a partir do ID fornecido.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Arquivo recuperado com sucesso",
                            content = @Content(
                                    mediaType = "application/octet-stream",
                                    schema = @Schema(type = "string", format = "binary")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Recurso não encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Erro interno ao buscar o arquivo"
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getArquivoPorId(@PathVariable String id) {
        try {
            GridFsResource arquivo = arquivoService.buscarArquivoPorId(id);

            if (arquivo == null || !arquivo.exists()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + arquivo.getFilename() + "\"")
                    .contentType(MediaType.parseMediaType(arquivo.getContentType()))
                    .body(new InputStreamResource(arquivo.getInputStream()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao buscar arquivo: " + e.getMessage());
        }
    }


    @Operation(
            summary = "Deleta uma nota-fiscal pelo número de embarque",
            description = "Remove a nota-fiscal relacionada pelo número de embarque do sistema com base no ID informado.",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Nota-Fiscal deletada com sucesso"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Nota-fiscal não encontrada não encontrada"
                    )
            }
    )
    @DeleteMapping("/nota-fiscal/{numeroEmbarque}")
    public ResponseEntity<String> deletarNotaFiscalPorNumeroEmbarque(@PathVariable String numeroEmbarque) {
        try {
            boolean removido = arquivoService.deletarNotaFiscalPorNumeroEmbarque(numeroEmbarque);

            if (removido) {
                return ResponseEntity.ok("Nota fiscal removida com sucesso para a carga: " + numeroEmbarque);
            } else {
                return ResponseEntity.status(404).body("Nenhuma nota fiscal encontrada para o número de embarque: " + numeroEmbarque);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao deletar a nota fiscal: " + e.getMessage());
        }
    }

}
