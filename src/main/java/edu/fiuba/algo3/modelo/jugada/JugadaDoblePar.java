package edu.fiuba.algo3.modelo.jugada;

import edu.fiuba.algo3.modelo.Poker;

import java.util.ArrayList;

public class JugadaDoblePar extends Jugada {

    public JugadaDoblePar(ArrayList<Poker> cartas) {
        super(cartas);
        valorBase = 20;
        multiplicadorBase = 2;
    }
}
