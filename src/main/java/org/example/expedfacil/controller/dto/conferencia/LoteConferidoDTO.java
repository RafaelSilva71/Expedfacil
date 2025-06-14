package org.example.expedfacil.controller.dto.conferencia;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoteConferidoDTO {

    @NotBlank(message = "O código do lote é obrigatório")
    private String lote;

    @NotBlank(message = "A data de validade é obrigatória")
    private String validade; // formato dd/MM/yyyy

    @NotNull(message = "A quantidade é obrigatória")
    private Integer quantidade;

    private String dataProducao;


    // Getters e Setters

    public String getDataProducao() {
        return dataProducao;
    }

    public void setDataProducao(String dataProducao) {
        this.dataProducao = dataProducao;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}