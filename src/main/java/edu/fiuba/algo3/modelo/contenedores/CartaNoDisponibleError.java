package edu.fiuba.algo3.modelo.contenedores;

public class CartaNoDisponibleError extends RuntimeException {
    public CartaNoDisponibleError(String message) {
        super(message);
    }
}
