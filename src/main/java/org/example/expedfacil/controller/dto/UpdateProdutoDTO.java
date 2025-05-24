package org.example.expedfacil.controller.dto;

public record UpdateProdutoDTO(
        String id,
        String nome,
        Integer quantPorCaixa,
        Integer quantCxFd)

{}
