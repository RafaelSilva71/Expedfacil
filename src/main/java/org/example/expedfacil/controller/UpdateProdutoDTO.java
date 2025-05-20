package org.example.expedfacil.controller;

import jakarta.validation.constraints.NotNull;

public record UpdateProdutoDTO(
        String id,
        String nome,
        Integer quantPorCaixa,
        Integer quantCxFd)

{}
