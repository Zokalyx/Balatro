package edu.fiuba.algo3.modelo.jugada;

import edu.fiuba.algo3.modelo.Poker;

import java.util.ArrayList;

public class JugadaEscaleraReal extends Jugada {
    public JugadaEscaleraReal(ArrayList<Poker> cartas) {
        super(cartas);
        valorBase = 100;
        multiplicadorBase = 8;
        nombre = "escalera real";
    }
}
