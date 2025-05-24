package org.example.expedfacil.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "logs")
public class LogDeCarga {

    private String id;
    private String cargaId;
    private String mensagem;
    private String data;

    public String getCargaId() {
        return cargaId;
    }

    public void setCargaId(String cargaId) {
        this.cargaId = cargaId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
