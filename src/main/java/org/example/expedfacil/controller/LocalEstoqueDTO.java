package org.example.expedfacil.controller;

import java.util.List;

public class LocalEstoqueDTO {

    private String numeroEmbarque;
    private List<ResumoProdutoLocalDTO> produtos;


    public String getNumeroEmbarque() {
        return numeroEmbarque;
    }

    public void setNumeroEmbarque(String numeroEmbarque) {
        this.numeroEmbarque = numeroEmbarque;
    }

    public List<ResumoProdutoLocalDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ResumoProdutoLocalDTO> produtos) {
        this.produtos = produtos;
    }


}
