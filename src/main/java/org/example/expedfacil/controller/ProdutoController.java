package org.example.expedfacil.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.expedfacil.controller.dto.CreateProdutoDTO;
import org.example.expedfacil.controller.dto.UpdateProdutoDTO;
import org.example.expedfacil.model.Produto;
import org.example.expedfacil.service.ProdutoService;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name ="Produto",
        description="Contém todas as operações relativas aos recursos para o produtos contem o CRUD completo")
@RestController
@RequestMapping("/Produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Operation(
            summary = "Cria um novo Produto",
            description = "Recurso para criar um Novo Produto",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recurso criado com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CreateProdutoDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Produto já cadastrado!",
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
    @PostMapping
    public ResponseEntity<String> createProduto(@RequestBody @Valid CreateProdutoDTO dto) {
        var newProduto = produtoService.createProduto(dto);
        return ResponseEntity.ok(newProduto.getId());
    }


    @Operation(
            summary = "Recupera um recusos pelo id",
            description = "Recurso para recuperar um recurso do produto pelo id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recurso recuperado com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Produto.class)
                            )
                    ),

                    @ApiResponse(
                            responseCode = "404",
                            description = "Recurso não encontrado !",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable String id) {
        Produto produto = produtoService.buscarPorId(id);
        if (produto != null) {
            return ResponseEntity.ok(produto);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Recupera todos os recusos de produto",
            description = "Recurso para recuperar um todos os dados de produto",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recurso recuperado com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Produto.class))
                                    // schema = @Schema(implementation = List.class)
                            )
                    ),

                    @ApiResponse(
                            responseCode = "500",
                            description = "Erro interno do servidor!",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<Produto>> getAllProdutos() {
        var produto =   produtoService.ListarProdutos();
        return ResponseEntity.ok(produto);
    }

    @Operation(
            summary = "Atualizar o Prroduto",
            description = "Recurso para atualizar um recurso do produto pelo id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recurso atualizado com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = void.class)
                            )
                    ),

                    @ApiResponse(
                            responseCode = "404",
                            description = "Recurso não encontrado !",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProdutoById(@PathVariable String id, @RequestBody UpdateProdutoDTO dto) {
        produtoService.updateProduto(id, dto);
        return ResponseEntity.noContent().build();
    }


    @Operation(
            summary = "Exclui um produto pelo ID",
            description = "Recurso para excluir um produto existente com base no seu ID.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Produto excluído com sucesso",
                            content = @Content(schema = @Schema(hidden = true))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Produto não encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Erro interno do servidor",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
        produtoService.deleteById(String.valueOf(id));
        return ResponseEntity.noContent().build();
    }

}