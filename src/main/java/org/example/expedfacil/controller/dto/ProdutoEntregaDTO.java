package org.example.expedfacil.controller.dto;

/**
 * Representa os dados de um produto enviados pelo usuário
 * durante a criação de uma entrega dentro de uma carga.
 *
 * Contém o código do produto e a quantidade de caixas informada.
 */

public class ProdutoEntregaDTO {

    private String codigoProduto;
    private Integer quantidadeCaixas;

    public Integer getQuantidadeCaixas() {
        return quantidadeCaixas;
    }

    public void setQuantidadeCaixas(Integer quantidadeCaixas) {
        this.quantidadeCaixas = quantidadeCaixas;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }
}
