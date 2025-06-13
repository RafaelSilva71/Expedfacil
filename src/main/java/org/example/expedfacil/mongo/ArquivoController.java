package org.example.expedfacil.mongo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.expedfacil.controller.dto.CreateProdutoDTO;
import org.example.expedfacil.model.Produto;
import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file,
                                         @RequestParam(value = "descricao", required = false) String descricao) {
        try {
            String id = arquivoService.salvarArquivo(file, descricao != null ? descricao : "sem descrição");
            return ResponseEntity.ok("Arquivo salvo com ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao salvar: " + e.getMessage());
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
    public ResponseEntity<?> getArquivo(@PathVariable String id) {
        try {
            GridFsResource arquivo = arquivoService.buscarArquivoPorId(id);

            if (arquivo == null || !arquivo.exists()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"" + arquivo.getFilename() + "\"")
                    .contentType(MediaType.parseMediaType(arquivo.getContentType()))
                    .body(new InputStreamResource(arquivo.getInputStream()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao buscar arquivo: " + e.getMessage());
        }
    }
}
