package edu.fiuba.algo3.modelo.jugada;

import edu.fiuba.algo3.modelo.Poker;

import java.util.ArrayList;
import java.util.Collections;

public class JugadaCartaAlta extends Jugada{
    public JugadaCartaAlta() {
        valorBase = 5;
        multiplicadorBase = 1;
    }

    @Override
    public ArrayList<Poker> formarseConCartas(ArrayList<Poker> cartas) {
        ArrayList<Poker> cartasInvolucradas = new ArrayList<>();

        if (!cartas.isEmpty()) {
            ArrayList<Poker> cartasAuxiliar = new ArrayList<>(cartas);
            Collections.sort(cartasAuxiliar);
            if (cartasAuxiliar.get(0).esAs()) {
                cartasInvolucradas.add(cartasAuxiliar.get(0));
            } else {
                cartasInvolucradas.add(cartasAuxiliar.get(cartasAuxiliar.size() - 1));
            }
        }

        return cartasInvolucradas;
    }
}
