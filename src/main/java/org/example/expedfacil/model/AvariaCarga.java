package org.example.expedfacil.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class AvariaCarga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroEmbarque;

    private LocalDateTime dataRegistro;

    @OneToMany(mappedBy = "avariaCarga", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProdutoAvariado> produtosAvariados;

    @PrePersist
    public void aoCriar() {
        this.dataRegistro = LocalDateTime.now();
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroEmbarque() {
        return numeroEmbarque;
    }

    public void setNumeroEmbarque(String numeroEmbarque) {
        this.numeroEmbarque = numeroEmbarque;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public List<ProdutoAvariado> getProdutosAvariados() {
        return produtosAvariados;
    }

    public void setProdutosAvariados(List<ProdutoAvariado> produtosAvariados) {
        this.produtosAvariados = produtosAvariados;
    }
}
