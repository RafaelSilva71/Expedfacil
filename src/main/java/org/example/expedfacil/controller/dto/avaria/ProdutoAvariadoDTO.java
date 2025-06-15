package org.example.expedfacil.controller.dto.avaria;

import org.example.expedfacil.model.enums.MotivoAvaria;

public class ProdutoAvariadoDTO {
    private String codigoProduto;
    private String lote;
    private Integer quantidade;
    private MotivoAvaria motivo;
    private String observacao; // opcional
    private String nomeProduto;
    private String numeroEmbarque;

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

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public MotivoAvaria getMotivo() {
        return motivo;
    }

    public void setMotivo(MotivoAvaria motivo) {
        this.motivo = motivo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getNumeroEmbarque() {
        return numeroEmbarque;
    }

    public void setNumeroEmbarque(String numeroEmbarque) {
        this.numeroEmbarque = numeroEmbarque;
    }
}
