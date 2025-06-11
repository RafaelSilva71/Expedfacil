package org.example.expedfacil.controller.dto.conferencia;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CargaConferidaDTO {

    @NotBlank(message = "O número do embarque original é obrigatório")
    private String numeroEmbarqueOriginal;

    @NotNull(message = "A lista de entregas conferidas é obrigatória")
    private List<EntregaConferidaDTO> entregasConferidas;

    // Getters e Setters
    public String getNumeroEmbarqueOriginal() {
        return numeroEmbarqueOriginal;
    }

    public void setNumeroEmbarqueOriginal(String numeroEmbarqueOriginal) {
        this.numeroEmbarqueOriginal = numeroEmbarqueOriginal;
    }

    public List<EntregaConferidaDTO> getEntregasConferidas() {
        return entregasConferidas;
    }

    public void setEntregasConferidas(List<EntregaConferidaDTO> entregasConferidas) {
        this.entregasConferidas = entregasConferidas;
    }
}
