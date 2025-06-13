package org.example.expedfacil.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.expedfacil.controller.dto.CreateCargaDTO;
import org.example.expedfacil.controller.dto.CreateProdutoDTO;
import org.example.expedfacil.model.Carga;
import org.example.expedfacil.model.ProdutoEntrega;
import org.example.expedfacil.repository.CargaRepository;
import org.example.expedfacil.service.CargaService;
import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name ="Carga",
        description="Contém todas as operações relativas aos recursos para a Carga contem o CRUD completo")
@RestController
@RequestMapping("/carga")
public class CargaController {

    @Autowired
    private CargaService cargaService;

    @Autowired
    private CargaRepository cargaRepository;
    @Operation(
            summary = "Cria uma nova Carga",
            description = "Endpoint para criação de uma nova carga no sistema.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Carga criada com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Carga.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Carga já cadastrada",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Dados de entrada inválidos",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )

    @PostMapping
    public ResponseEntity<Carga> criarCarga(@RequestBody CreateCargaDTO dto) {
        Carga novaCarga = cargaService.create(dto);
        return ResponseEntity.ok(novaCarga);
    }

    @Operation(
            summary = "Lista todas as cargas",
            description = "Recupera todas as cargas cadastradas no sistema.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de cargas retornada com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Carga.class))
                            )
                    )
            }
    )


    @GetMapping
    public ResponseEntity<List<Carga>> listarTodas() {
        return ResponseEntity.ok(cargaRepository.findAll());
    }
    @Operation(
            summary = "Busca uma carga por ID",
            description = "Retorna os dados de uma carga específica com base no ID informado.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Carga encontrada com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Carga.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Carga não encontrada"
                    )
            }
    )

    @GetMapping("/{id}")
    public ResponseEntity<Carga> buscarPorId(@PathVariable String id) {
        return cargaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(
            summary = "Deleta uma carga por ID",
            description = "Remove uma carga do sistema com base no ID informado.",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Carga deletada com sucesso"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Carga não encontrada"
                    )
            }
    )

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        if (!cargaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        cargaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(
            summary = "Lista as placas da carreta de uma carga",
            description = "Retorna uma lista de placas da carreta associadas à carga identificada pelo ID.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Placas retornadas com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = String.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Carga não encontrada"
                    )
            }
    )

    @GetMapping("/placas/{id}")
    public ResponseEntity<List<String>> listarPlacas(@PathVariable String id) {
        return cargaRepository.findById(id)
                .map(carga -> ResponseEntity.ok(carga.getPlacasCarreta()))
                .orElse(ResponseEntity.notFound().build());
    }



}
