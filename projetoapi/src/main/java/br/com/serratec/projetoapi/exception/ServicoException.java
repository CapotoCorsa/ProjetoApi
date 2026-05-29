package br.com.serratec.projetoapi.exception;

public class ServicoException extends RuntimeException{
    public ServicoException(String message) {
        super(message);
    }
}
