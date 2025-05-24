package org.example.expedfacil.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.example.expedfacil.model.enums.TipoEntrega;

import java.util.List;

/**
 * Representa uma entrega específica dentro de uma carga.
 *
 * Cada entrega possui informações como destinatário, observações e tipo de entrega
 * (cliente ou transferência), além de conter a lista de produtos que a compõem.
 * Está associada diretamente a uma única carga.
 */

@Entity
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numeroEntrega;
    private String destinatario;
    private String observacao;
    private Double pesoLiquido;
    private Double pesoBruto;
    private Integer quantidadeTotalCaixas;


    @Enumerated(EnumType.STRING)
    private TipoEntrega tipoEntrega;

    @ManyToOne
    @JoinColumn(name = "carga_id")
    @JsonBackReference    // - Evitar loop infinito
    private Carga carga;

    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<ProdutoEntrega> produtos;


    public List<ProdutoEntrega> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoEntrega> produtos) {
        this.produtos = produtos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroEntrega() {
        return numeroEntrega;
    }

    public void setNumeroEntrega(Integer numeroEntrega) {
        this.numeroEntrega = numeroEntrega;
    }

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

    public Integer getQuantidadeTotalCaixas() {
        return quantidadeTotalCaixas;
    }

    public void setQuantidadeTotalCaixas(Integer quantidadeTotalCaixas) {
        this.quantidadeTotalCaixas = quantidadeTotalCaixas;
    }

    public TipoEntrega getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(TipoEntrega tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public Carga getCarga() {
        return carga;
    }

    public void setCarga(Carga carga) {
        this.carga = carga;
    }
}
