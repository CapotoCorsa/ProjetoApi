package br.com.serratec.projetoapi.exception;

public class CheckoutException extends RuntimeException{
    public CheckoutException(String message) {
        super(message);
    }
}