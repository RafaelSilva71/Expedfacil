package org.example.expedfacil.exception;

public class ProdutoJaExisteException extends RuntimeException {
    public ProdutoJaExisteException(String id) {
        super("Já existe um produto com o código informado: " + id);
    }
}
