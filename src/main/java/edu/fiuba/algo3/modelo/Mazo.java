package edu.fiuba.algo3.modelo;

public class Mazo {
    public Carta tomarCarta() {
        return new Poker(1, "Q", 10, new Diamante());
    }
}
