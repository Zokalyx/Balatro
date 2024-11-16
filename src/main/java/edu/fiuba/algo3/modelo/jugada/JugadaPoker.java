package edu.fiuba.algo3.modelo.jugada;

import edu.fiuba.algo3.modelo.Poker;

import java.util.ArrayList;

public class JugadaPoker extends Jugada {
    public JugadaPoker(ArrayList<Poker> cartasUsadas) {
        super(cartasUsadas);
        valorBase = 60;
        multiplicadorBase = 7;
        nombre = "poker";
    }
}
