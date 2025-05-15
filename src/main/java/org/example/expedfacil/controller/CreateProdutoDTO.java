package org.example.expedfacil.controller;

import jakarta.validation.constraints.NotNull;


public record CreateProdutoDTO(
        String codigo,
        String descricao,
        String lote,
        @NotNull Integer quantidade,
        String localEstoque)
{}
