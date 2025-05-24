
package org.example.expedfacil.controller.dto;



import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;


public record CreateProdutoDTO(

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "000000.00")
        String id,
        String nome,
        Integer quantPorCaixa,
        Integer quantCxFd)
{}
