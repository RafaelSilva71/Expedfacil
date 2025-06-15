package org.example.expedfacil.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Produto {


    @Id
    @Column(nullable = false, unique = true)
    private String id;

    private String nome;
    private Integer  quantPorCaixa; //quantidade por caixa (latas dentro de uma caixa) (MV DIA% 24/200)
    private Integer quantCxFd; //CaixaTotal (CX) por palete

    public Produto() {}

    public Produto(String id, String nome, Integer quantPorCaixa, Integer quantCxFd) {
        this.id = id;
        this.nome = nome;
        this.quantPorCaixa = quantPorCaixa;
        this.quantCxFd = quantCxFd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantPorCaixa() {
        return quantPorCaixa;
    }

    public void setQuantPorCaixa(Integer quantPorCaixa) {
        this.quantPorCaixa = quantPorCaixa;
    }

    public Integer getQuantCxFd() {
        return quantCxFd;
    }

    public void setQuantCxFd(Integer quantCxFd) {
        this.quantCxFd = quantCxFd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id) && Objects.equals(nome, produto.nome) && Objects.equals(quantPorCaixa, produto.quantPorCaixa) && Objects.equals(quantCxFd, produto.quantCxFd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, quantPorCaixa, quantCxFd);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", quantPorCaixa=" + quantPorCaixa +
                ", quantCxFd='" + quantCxFd + '\'' +
                '}';
    }
}
