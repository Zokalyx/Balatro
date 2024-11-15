package edu.fiuba.algo3.modelo.jugada;

import edu.fiuba.algo3.modelo.Poker;

import java.util.ArrayList;

public class JugadaEscalera extends Jugada {
    public JugadaEscalera(ArrayList<Poker> cartas) {
        super(cartas);
        valorBase = 30;
        multiplicadorBase = 4;
    }
}
