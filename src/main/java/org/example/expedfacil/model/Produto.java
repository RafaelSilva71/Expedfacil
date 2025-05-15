package org.example.expedfacil.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Produto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;
    private String descricao;
    private String lote;
    private Integer  quantidade;
    private String localEstoque;

    public Produto() {}

    public Produto(Long id, String codigo, String descricao, String lote, Integer  quantidade, String localEstoque) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
        this.lote = lote;
        this.quantidade = quantidade;
        this.localEstoque = localEstoque;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getLocalEstoque() {
        return localEstoque;
    }

    public void setLocalEstoque(String localEstoque) {
        this.localEstoque = localEstoque;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return quantidade == produto.quantidade && Objects.equals(id, produto.id) && Objects.equals(codigo, produto.codigo) && Objects.equals(descricao, produto.descricao) && Objects.equals(lote, produto.lote) && Objects.equals(localEstoque, produto.localEstoque);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo, descricao, lote, quantidade, localEstoque);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "Id=" + id +
                ", codigo='" + codigo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", lote='" + lote + '\'' +
                ", quantidade=" + quantidade +
                ", localEstoque='" + localEstoque + '\'' +
                '}';
    }


}
