package org.example.expedfacil.controller.dto.conferencia.response;

import java.util.List;

public class ProdutoConferidoResponseDTO {
    private Long id;
    private String codigoProduto;
    private String nomeProduto;
    private Integer quantidadeTotal;
    private List<LoteConferidoResponseDTO> lotes;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Integer getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public void setQuantidadeTotal(Integer quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }

    public List<LoteConferidoResponseDTO> getLotes() {
        return lotes;
    }

    public void setLotes(List<LoteConferidoResponseDTO> lotes) {
        this.lotes = lotes;
    }
}
