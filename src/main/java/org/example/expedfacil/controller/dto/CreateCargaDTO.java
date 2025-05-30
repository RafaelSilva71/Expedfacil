package org.example.expedfacil.controller.dto;

import java.util.List;

/**
 * DTO principal para criação de uma carga completa.
 *
 * Contém os dados gerais da carga e uma lista de entregas,
 * cada uma com seus respectivos produtos.
 */


public class CreateCargaDTO {

    private String numeroEmbarque;
    private String destino;                 /** Destino: Cidade ou Estado*/
    private Integer numeroEntregas;
    private String tipoCarregamento;        /** Tipo do carregamento: paletizado/Batido/ambos */
    private String transportadora;
    private String tipoVeiculo;             /** Tipo do veiculo: Baú, tampa baixa, Rodo trem, etc.) */
    private String placaCavalo;
    private List<String> placasCarreta;     /** Placa(s) da carreta, pode ser uma ou mais, em caso de bitrem por exemplo*/
    private String nomeMotorista;
    private String tipoCarga;               /** Define se é carga para cliente, transferência ou ambas  */
    private List<EntregaDTO> entregas;      /** Quantidade de entregas na mesma carga */

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

    public String getTipoCarregamento() {
        return tipoCarregamento;
    }

    public void setTipoCarregamento(String tipoCarregamento) {
        this.tipoCarregamento = tipoCarregamento;
    }

    public String getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(String transportadora) {
        this.transportadora = transportadora;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
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

    public String getTipoCarga() {
        return tipoCarga;
    }

    public void setTipoCarga(String tipoCarga) {
        this.tipoCarga = tipoCarga;
    }

    public List<EntregaDTO> getEntregas() {
        return entregas;
    }

    public void setEntregas(List<EntregaDTO> entregas) {
        this.entregas = entregas;
    }
}
