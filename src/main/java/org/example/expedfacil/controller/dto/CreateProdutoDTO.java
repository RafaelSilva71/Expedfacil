
package org.example.expedfacil.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Pattern;

public record CreateProdutoDTO(

        // Formatação que deve ficar o codigo/id do produto para fazer a criação
        @Pattern(regexp = "\\d{6}\\.\\d{2}", message = "O código deve estar no formato 000000.00")
        String id,
        String nome,
        Integer quantPorCaixa,
        Integer quantCxFd)
{}
