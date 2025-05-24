package org.example.expedfacil.controller.dto;

public record CreateProdutoDTO(
        String id,
        String nome,
        Integer quantPorCaixa,
        Integer quantCxFd)
{}
