package edu.fiuba.algo3.modelo.jugada;

import edu.fiuba.algo3.modelo.Poker;

import java.util.ArrayList;

public class JugadaColor extends Jugada {

    public JugadaColor() {
        valorBase = 30;
        multiplicadorBase = 4;
    }

    @Override
    public ArrayList<Poker> formarseConCartas(ArrayList<Poker> cartas) {
        ArrayList<Poker> cartasInvolucradas = new ArrayList<>();

        if (cartas.size() != 5) {
            return cartasInvolucradas;
        }

        for (Poker carta : cartas) {
            if (!carta.esMismoPaloQue(cartas.get(0))) {
                return cartasInvolucradas;
            }
        }

        cartasInvolucradas.addAll(cartas);
        return cartasInvolucradas;
    }
}
