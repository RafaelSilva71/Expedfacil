package org.example.expedfacil.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ProdutoConferido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String codigoProduto;

    @Column(nullable = false)
    private String nomeProduto;

    @Column(nullable = false)
    private Integer quantidadeTotal;

    @ManyToOne
    @JoinColumn(name = "entrega_id", nullable = false)
    private EntregaConferida entrega;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LoteConferido> lotes = new ArrayList<>();

    // Getters e Setters

    public Long getId() {
        return id;
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

    public Integer getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public void setQuantidadeTotal(Integer quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }

    public EntregaConferida getEntrega() {
        return entrega;
    }

    public void setEntrega(EntregaConferida entrega) {
        this.entrega = entrega;
    }

    public List<LoteConferido> getLotes() {
        return lotes;
    }

    public void setLotes(List<LoteConferido> lotes) {
        this.lotes = lotes;
        this.lotes.forEach(l -> l.setProduto(this)); // garante o vínculo
    }
}
