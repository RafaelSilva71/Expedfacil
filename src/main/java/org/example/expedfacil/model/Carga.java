package org.example.expedfacil.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.example.expedfacil.model.enums.StatusCarga;
import org.example.expedfacil.model.enums.TipoCarga;
import org.example.expedfacil.model.enums.TipoCarregamento;
import org.example.expedfacil.model.enums.TipoVeiculo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Representa uma carga completa no sistema, contendo os dados gerais da expedição,
 * como destino, motorista, transportadora, tipo de veículo, e a lista de produtos.
 *
 * Essa entidade evolui conforme as etapas de conferência são realizadas,
 * permitindo o registro de status, histórico de criação e associação com produtos.
 */


@Entity
public class Carga {

    @Id
    private String numeroEmbarque; // chave primária manual

    private String destino;

    private Integer numeroEntregas;

    @Enumerated(EnumType.STRING)
    private TipoCarregamento tipoCarregamento;

    private String transportadora;

    @Enumerated(EnumType.STRING)
    private TipoVeiculo tipoVeiculo;

    private String placaCavalo;

    @ElementCollection
    private List<String> placasCarreta;

    private String nomeMotorista;

    @Enumerated(EnumType.STRING)
    private TipoCarga tipoCarga;

    @Enumerated(EnumType.STRING)
    private StatusCarga status;

    private String criadoPor;

    private LocalDateTime dataCriacao;

    @OneToMany(mappedBy = "carga", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Entrega> entregas;

    private Double pesoTotalLiquido;

    private Double pesoTotalBruto;

    private Integer quantidadeTotalCaixas;

    @PrePersist
    public void aoCriar() {
        this.dataCriacao = LocalDateTime.now();
    }

    public String getNumeroEmbarque() {
        return numeroEmbarque;
    }

    public void setNumeroEmbarque(String numeroEmbarque) {
        this.numeroEmbarque = numeroEmbarque;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Integer getNumeroEntregas() {
        return numeroEntregas;
    }

    public void setNumeroEntregas(Integer numeroEntregas) {
        this.numeroEntregas = numeroEntregas;
    }

    public TipoCarregamento getTipoCarregamento() {
        return tipoCarregamento;
    }

    public void setTipoCarregamento(TipoCarregamento tipoCarregamento) {
        this.tipoCarregamento = tipoCarregamento;
    }

    public String getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(String transportadora) {
        this.transportadora = transportadora;
    }

    public TipoVeiculo getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public String getPlacaCavalo() {
        return placaCavalo;
    }

    public void setPlacaCavalo(String placaCavalo) {
        this.placaCavalo = placaCavalo;
    }

    public List<String> getPlacasCarreta() {
        return placasCarreta;
    }

    public void setPlacasCarreta(List<String> placasCarreta) {
        this.placasCarreta = placasCarreta;
    }

    public String getNomeMotorista() {
        return nomeMotorista;
    }

    public void setNomeMotorista(String nomeMotorista) {
        this.nomeMotorista = nomeMotorista;
    }

    public TipoCarga getTipoCarga() {
        return tipoCarga;
    }

    public void setTipoCarga(TipoCarga tipoCarga) {
        this.tipoCarga = tipoCarga;
    }

    public StatusCarga getStatus() {
        return status;
    }

    public void setStatus(StatusCarga status) {
        this.status = status;
    }

    public String getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(String criadoPor) {
        this.criadoPor = criadoPor;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<Entrega> getEntregas() {
        return entregas;
    }

    public void setEntregas(List<Entrega> entregas) {
        this.entregas = entregas;
    }

    public Double getPesoTotalLiquido() {
        return pesoTotalLiquido;
    }

    public void setPesoTotalLiquido(Double pesoTotalLiquido) {
        this.pesoTotalLiquido = pesoTotalLiquido;
    }

    public Double getPesoTotalBruto() {
        return pesoTotalBruto;
    }

    public void setPesoTotalBruto(Double pesoTotalBruto) {
        this.pesoTotalBruto = pesoTotalBruto;
    }

    public Integer getQuantidadeTotalCaixas() {
        return quantidadeTotalCaixas;
    }

    public void setQuantidadeTotalCaixas(Integer quantidadeTotalCaixas) {
        this.quantidadeTotalCaixas = quantidadeTotalCaixas;
    }
}
