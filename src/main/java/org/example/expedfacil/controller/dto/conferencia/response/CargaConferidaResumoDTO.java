package org.example.expedfacil.controller.dto.conferencia.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.RepresentationModel;

public class CargaConferidaResumoDTO extends RepresentationModel<CargaConferidaResumoDTO> {

    @JsonProperty("numeroEmbarqueConferido")
    private String numeroEmbarqueConferido;

    @JsonProperty("numeroEmbarqueOriginal")
    private String numeroEmbarqueOriginal;

    @JsonProperty("dataRegistro")
    private String dataRegistro;

    @JsonProperty("status")
    private String status;

    // Getters e Setters

    public String getNumeroEmbarqueConferido() {
        return numeroEmbarqueConferido;
    }

    public void setNumeroEmbarqueConferido(String numeroEmbarqueConferido) {
        this.numeroEmbarqueConferido = numeroEmbarqueConferido;
    }

    public String getNumeroEmbarqueOriginal() {
        return numeroEmbarqueOriginal;
    }

    public void setNumeroEmbarqueOriginal(String numeroEmbarqueOriginal) {
        this.numeroEmbarqueOriginal = numeroEmbarqueOriginal;
    }

    public String getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(String dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
