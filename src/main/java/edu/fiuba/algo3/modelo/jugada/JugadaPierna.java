package edu.fiuba.algo3.modelo.jugada;

import edu.fiuba.algo3.modelo.Poker;

import java.util.ArrayList;

public class JugadaPierna extends Jugada {
    public JugadaPierna(ArrayList<Poker> cartasUsadas) {
        super(cartasUsadas);
        valorBase = 30;
        multiplicadorBase = 3;
    }
}
