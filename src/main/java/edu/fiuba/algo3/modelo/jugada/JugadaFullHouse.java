package edu.fiuba.algo3.modelo.jugada;

import edu.fiuba.algo3.modelo.Poker;

import java.util.ArrayList;

public class JugadaFullHouse extends Jugada {
    public JugadaFullHouse(ArrayList<Poker> cartas) {
        super(cartas);
        valorBase = 40;
        multiplicadorBase = 4;
        nombre = "full house";
    }
}
