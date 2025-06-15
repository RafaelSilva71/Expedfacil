package org.example.expedfacil.controller.dto.conferencia.response;

import java.time.LocalDateTime;
import java.util.List;

public class CargaConferidaResponseDTO {
    private Long id;
    private String numeroEmbarqueOriginal;
    private LocalDateTime dataRegistro;
    private List<EntregaConferidaResponseDTO> entregasConferidas;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroEmbarqueOriginal() {
        return numeroEmbarqueOriginal;
    }

    public void setNumeroEmbarqueOriginal(String numeroEmbarqueOriginal) {
        this.numeroEmbarqueOriginal = numeroEmbarqueOriginal;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public List<EntregaConferidaResponseDTO> getEntregasConferidas() {
        return entregasConferidas;
    }

    public void setEntregasConferidas(List<EntregaConferidaResponseDTO> entregasConferidas) {
        this.entregasConferidas = entregasConferidas;
    }
}
