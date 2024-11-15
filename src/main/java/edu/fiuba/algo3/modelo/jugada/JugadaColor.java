package edu.fiuba.algo3.modelo.jugada;

import edu.fiuba.algo3.modelo.Poker;

import java.util.ArrayList;

public class JugadaColor extends Jugada {

    public JugadaColor(ArrayList<Poker> cartas) {
        super(cartas);
        valorBase = 30;
        multiplicadorBase = 4;
    }
}
