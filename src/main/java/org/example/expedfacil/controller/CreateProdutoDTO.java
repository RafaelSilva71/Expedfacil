package org.example.expedfacil.controller;

import jakarta.validation.constraints.NotNull;

public record CreateProdutoDTO(
        String id,
        String nome,
        Integer quantPorCaixa,
        Integer quantCxFd)
{}
