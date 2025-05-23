package org.example.expedfacil.controller;

import java.util.List;

/**
 * Representa uma entrega enviada pelo usuário durante a criação de uma carga.
 *
 * Inclui informações como destinatário, observações, tipo da entrega
 * e a lista de produtos que compõem essa entrega.
 */


public class EntregaDTO {

    private String destinatario;
    private String observacao;
    private String tipoEntrega;
    private List<ProdutoEntregaDTO> produtos;
    private Double pesoLiquido;
    private Double pesoBruto;


    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(String tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public List<ProdutoEntregaDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoEntregaDTO> produtos) {
        this.produtos = produtos;
    }

    public Double getPesoLiquido() {
        return pesoLiquido;
    }

    public void setPesoLiquido(Double pesoLiquido) {
        this.pesoLiquido = pesoLiquido;
    }

    public Double getPesoBruto() {
        return pesoBruto;
    }

    public void setPesoBruto(Double pesoBruto) {
        this.pesoBruto = pesoBruto;
    }
}
