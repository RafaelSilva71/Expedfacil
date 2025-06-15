package org.example.expedfacil.exception;

public class NotaFiscalNaoEncontradaException extends RuntimeException {
    public NotaFiscalNaoEncontradaException(String numeroEmbarque) {
        super("Nenhuma nota fiscal foi encontrada para a carga de número de embarque: " + numeroEmbarque);
    }
}
