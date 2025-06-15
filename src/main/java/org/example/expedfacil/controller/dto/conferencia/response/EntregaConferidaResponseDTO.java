package org.example.expedfacil.controller.dto.conferencia.response;

import java.util.List;

public class EntregaConferidaResponseDTO {
    private Long id;
    private Integer numeroEntrega;
    private List<ProdutoConferidoResponseDTO> produtos;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroEntrega() {
        return numeroEntrega;
    }

    public void setNumeroEntrega(Integer numeroEntrega) {
        this.numeroEntrega = numeroEntrega;
    }

    public List<ProdutoConferidoResponseDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoConferidoResponseDTO> produtos) {
        this.produtos = produtos;
    }
}
