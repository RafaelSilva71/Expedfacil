package org.example.expedfacil.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/**
 * Representa um produto incluído em uma entrega específica.
 *
 * Contém informações como código, nome e quantidade de caixas do produto,
 * além da referência à entrega à qual pertence.
 */


@Entity
public class ProdutoEntrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigoProduto;
    private String nomeProduto;
    private Integer quantidadeCaixas;

    @ManyToOne
    @JoinColumn(name = "entrega_id")
    @JsonBackReference
    private Entrega entrega;


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

    public Integer getQuantidadeCaixas() {
        return quantidadeCaixas;
    }

    public void setQuantidadeCaixas(Integer quantidadeCaixas) {
        this.quantidadeCaixas = quantidadeCaixas;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

}
