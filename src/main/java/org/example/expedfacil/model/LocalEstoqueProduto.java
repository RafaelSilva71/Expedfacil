package org.example.expedfacil.model;

import jakarta.persistence.*;

@Entity
public class LocalEstoqueProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroEmbarque;       // referência da carga
    private String codigoProduto;
    private String nomeProduto;
    private String resultadoCalculo;     // "2 e mais 34 = 130"
    private String localEstoque;         // preenchido manualmente

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

    public String getResultadoCalculo() {
        return resultadoCalculo;
    }

    public void setResultadoCalculo(String resultadoCalculo) {
        this.resultadoCalculo = resultadoCalculo;
    }

    public String getLocalEstoque() {
        return localEstoque;
    }

    public void setLocalEstoque(String localEstoque) {
        this.localEstoque = localEstoque;
    }
}