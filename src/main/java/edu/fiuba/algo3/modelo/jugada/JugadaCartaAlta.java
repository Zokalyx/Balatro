package edu.fiuba.algo3.modelo.jugada;

import edu.fiuba.algo3.modelo.Poker;

import java.util.ArrayList;

public class JugadaCartaAlta extends Jugada{
    public JugadaCartaAlta(ArrayList<Poker> cartas) {
        super(cartas);
        valorBase = 5;
        multiplicadorBase = 1;
        nombre = "carta mas alta";
    }
}
