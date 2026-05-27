package br.com.serratec.projetoapi.exception;

public class ClienteException extends RuntimeException{
    public ClienteException(String message) {
        super(message);
    }
}
