package edu.fiuba.algo3.modelo.jugada;

import edu.fiuba.algo3.modelo.Poker;

import java.util.ArrayList;

public class JugadaPar extends Jugada {
    public JugadaPar() {
        valorBase = 10;
        multiplicadorBase = 2;
    }

    @Override
    public ArrayList<Poker> formarseConCartas(ArrayList<Poker> cartas) {
        ArrayList<Poker> cartasInvolucradas = new ArrayList<>();

        for (Poker carta1 : cartas) {
            for (Poker carta2 : cartas) {
                if (carta1.equals(carta2)) {
                    continue;
                }

                if (carta1.esMismoSimboloQue(carta2)) {
                    cartasInvolucradas.add(carta1);
                    cartasInvolucradas.add(carta2);
                    return cartasInvolucradas;
                }
            }
        }

        return cartasInvolucradas;
    }
}
