package org.example.expedfacil.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class EntregaConferida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numeroEntrega;

    @ManyToOne
    @JoinColumn(name = "conferencia_id", nullable = false)
    @JsonBackReference
    private ConferenciaLotes conferencia;


    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProdutoConferido> produtos = new ArrayList<>();

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public Integer getNumeroEntrega() {
        return numeroEntrega;
    }

    public void setNumeroEntrega(Integer numeroEntrega) {
        this.numeroEntrega = numeroEntrega;
    }

    public ConferenciaLotes getConferencia() {
        return conferencia;
    }

    public void setConferencia(ConferenciaLotes conferencia) {
        this.conferencia = conferencia;
    }

    public List<ProdutoConferido> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoConferido> produtos) {
        this.produtos = produtos;
        this.produtos.forEach(p -> p.setEntrega(this)); // garante o vínculo
    }
}
