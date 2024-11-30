package edu.fiuba.algo3.modelo.jugada;

import edu.fiuba.algo3.modelo.Poker;

import java.util.ArrayList;

public class JugadaPierna extends Jugada {
    public JugadaPierna() {
        valorBase = 30;
        multiplicadorBase = 3;
    }

    @Override
    public ArrayList<Poker> formarseConCartas(ArrayList<Poker> cartas) {
        ArrayList<Poker> cartasInvolucradas = new ArrayList<>();

        for (Poker carta1 : cartas) {
            for (Poker carta2 : cartas) {
                for (Poker carta3 : cartas) {
                    if (carta1.equals(carta2) || carta2.equals(carta3) || carta3.equals(carta1)) {
                        continue;
                    }

                    if (carta1.esMismoSimboloQue(carta2) && carta2.esMismoSimboloQue(carta3)) {
                        cartasInvolucradas.add(carta1);
                        cartasInvolucradas.add(carta2);
                        cartasInvolucradas.add(carta3);
                        return cartasInvolucradas;
                    }
                }
            }
        }

        return cartasInvolucradas;
    }
}
