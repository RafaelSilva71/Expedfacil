package org.example.expedfacil.controller.dto.avaria;

import java.time.LocalDateTime;
import java.util.List;

public class AvariaCargaResponseDTO {
    private Long id;
    private String numeroEmbarque;
    private LocalDateTime dataRegistro;
    private List<ProdutoAvariadoResponseDTO> produtos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroEmbarque() {
        return numeroEmbarque;
    }

    public void setNumeroEmbarque(String numeroEmbarque) {
        this.numeroEmbarque = numeroEmbarque;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public List<ProdutoAvariadoResponseDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoAvariadoResponseDTO> produtos) {
        this.produtos = produtos;
    }
}
