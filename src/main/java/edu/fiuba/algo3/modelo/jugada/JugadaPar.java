package edu.fiuba.algo3.modelo.jugada;

import edu.fiuba.algo3.modelo.Poker;

import java.util.ArrayList;

public class JugadaPar extends Jugada {
    public JugadaPar(ArrayList<Poker> cartas) {
        super(cartas);
        valorBase = 10;
        multiplicadorBase = 2;
        nombre = "par";
    }
}
