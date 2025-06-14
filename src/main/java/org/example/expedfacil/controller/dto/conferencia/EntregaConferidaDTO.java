package org.example.expedfacil.controller.dto.conferencia;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class EntregaConferidaDTO {

    @NotBlank(message = "O número da entrega é obrigatório")
    private String numeroEntrega;

    @NotNull(message = "A lista de produtos conferidos é obrigatória")
    private List<ProdutoConferidoDTO> produtos;

    // Getters e Setters
    public String getNumeroEntrega() {
        return numeroEntrega;
    }

    public void setNumeroEntrega(String numeroEntrega) {
        this.numeroEntrega = numeroEntrega;
    }

    public List<ProdutoConferidoDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoConferidoDTO> produtos) {
        this.produtos = produtos;
    }
}