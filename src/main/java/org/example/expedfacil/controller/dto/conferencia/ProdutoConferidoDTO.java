package org.example.expedfacil.controller.dto.conferencia;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class ProdutoConferidoDTO {

    @NotBlank(message = "O código do produto é obrigatório")
    private String codigoProduto;

    @NotNull(message = "A quantidade total é obrigatória")
    private Integer quantidadeTotal;

    @NotNull(message = "A lista de lotes é obrigatória")
    private List<LoteConferidoDTO> lotes;

    @NotBlank(message = "O nome do produto será preenchido automaticamente")
    private String nomeProduto;

    // Getters e Setters

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public Integer getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public void setQuantidadeTotal(Integer quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }

    public List<LoteConferidoDTO> getLotes() {
        return lotes;
    }

    public void setLotes(List<LoteConferidoDTO> lotes) {
        this.lotes = lotes;
    }

}

