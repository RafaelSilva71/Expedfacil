package org.example.expedfacil.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.expedfacil.model.Carga;
import org.example.expedfacil.model.LocalEstoqueProduto;
import org.example.expedfacil.repository.CargaRepository;
import org.example.expedfacil.repository.LocalEstoqueProdutoRepository;
import org.example.expedfacil.service.CargaService;
import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name ="Local de Estoque",
        description="Contém todas as operações relativas aos recursos para o Local de Estoque!")
@RestController
@RequestMapping("/local-estoque")
public class LocalEstoqueController {

    @Autowired
    private LocalEstoqueProdutoRepository localEstoqueProdutoRepository;

    // Listar todos os locais de estoque salvos
    @GetMapping
    public ResponseEntity<List<LocalEstoqueProduto>> listarTodos() {
        List<LocalEstoqueProduto> todos = localEstoqueProdutoRepository.findAll();
        return ResponseEntity.ok(todos);
    }

    // Listar locais de uma carga específica
    @GetMapping("/carga/{numeroEmbarque}")
    public ResponseEntity<List<LocalEstoqueProduto>> listarPorCarga(@PathVariable String numeroEmbarque) {
        List<LocalEstoqueProduto> locais = localEstoqueProdutoRepository.findByNumeroEmbarque(numeroEmbarque);
        return ResponseEntity.ok(locais);
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
                                    schema = @Schema(implementation = LocalEstoqueProduto.class)
                            )
                    ),

                    @ApiResponse(
                            responseCode = "404",
                            description = "Produto não encontrado!",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse( // Opcional, para casos onde a string enviada poderia ser inválida para seu serviço
                            responseCode = "400",
                            description = "Requisição inválida (ex: formato incorreto do local de estoque)",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )
    //  Atualizar o campo "localEstoque" de um produto específico
    @PutMapping("/{id}")
    public ResponseEntity<LocalEstoqueProduto> atualizarLocalEstoque(@PathVariable Long id,
                                                                     @RequestBody String novoLocal) {
        LocalEstoqueProduto produto = localEstoqueProdutoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));

        produto.setLocalEstoque(novoLocal);
        localEstoqueProdutoRepository.save(produto);

        return ResponseEntity.ok(produto);
    }
}
