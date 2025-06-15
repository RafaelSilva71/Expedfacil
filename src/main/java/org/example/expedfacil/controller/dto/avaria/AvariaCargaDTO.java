package org.example.expedfacil.controller.dto.avaria;

import java.util.List;

public class AvariaCargaDTO {
    private String numeroEmbarque;
    private List<ProdutoAvariadoDTO> produtos;

    public String getNumeroEmbarque() {
        return numeroEmbarque;
    }

    public void setNumeroEmbarque(String numeroEmbarque) {
        this.numeroEmbarque = numeroEmbarque;
    }

    public List<ProdutoAvariadoDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoAvariadoDTO> produtos) {
        this.produtos = produtos;
    }
}
