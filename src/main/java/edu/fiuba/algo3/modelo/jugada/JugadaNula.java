package edu.fiuba.algo3.modelo.jugada;

import edu.fiuba.algo3.modelo.Poker;

import java.util.ArrayList;

public class JugadaNula extends Jugada {
    public JugadaNula(ArrayList<Poker> cartas) {
        super(cartas);
        valorBase = 0;
        multiplicadorBase = 0;
        nombre = "nula";
    }
}
