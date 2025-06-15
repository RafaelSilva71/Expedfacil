package org.example.expedfacil.model;

import jakarta.persistence.*;
import org.example.expedfacil.model.enums.MotivoAvaria;

@Entity
public class ProdutoAvariado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigoProduto;

    private String nomeProduto;

    private String lote;

    private Integer quantidade;

    @Enumerated(EnumType.STRING)
    private MotivoAvaria motivo;

    private String observacao; // usado apenas se motivo == OUTROS

    @ManyToOne
    @JoinColumn(name = "avaria_carga_id")
    private AvariaCarga avariaCarga;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public MotivoAvaria getMotivo() {
        return motivo;
    }

    public void setMotivo(MotivoAvaria motivo) {
        this.motivo = motivo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public AvariaCarga getAvariaCarga() {
        return avariaCarga;
    }

    public void setAvariaCarga(AvariaCarga avariaCarga) {
        this.avariaCarga = avariaCarga;
    }
}
