package edu.fiuba.algo3.modelo.jugada;

import edu.fiuba.algo3.modelo.Poker;

import java.util.ArrayList;

public class JugadaNula extends Jugada {

    public JugadaNula() {
        super();
        valorBase=0;
        multiplicadorBase=1;
    }

    @Override
    public ArrayList<Poker> formarseConCartas(ArrayList<Poker> cartas) {
        return new ArrayList<>();
    }
}
