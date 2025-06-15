package org.example.expedfacil.model;

import jakarta.persistence.*;

@Entity
public class LoteConferido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String lote;

    @Column(nullable = false)
    private String validade; // formato dd/MM/yyyy

    @Column(nullable = false)
    private Integer quantidade;

    private String dataProducao;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoConferido produto;

    // Getters e Setters

    public Long getId() {
        return id;
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

    public String getDataProducao() {
        return dataProducao;
    }

    public void setDataProducao(String dataProducao) {
        this.dataProducao = dataProducao;
    }

    public ProdutoConferido getProduto() {
        return produto;
    }

    public void setProduto(ProdutoConferido produto) {
        this.produto = produto;
    }
}
