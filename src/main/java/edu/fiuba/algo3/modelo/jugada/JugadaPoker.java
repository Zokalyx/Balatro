package edu.fiuba.algo3.modelo.jugada;

import edu.fiuba.algo3.modelo.Poker;

import java.util.ArrayList;
import java.util.Collections;

public class JugadaPoker extends Jugada {
    public JugadaPoker() {
        valorBase = 60;
        multiplicadorBase = 7;
    }

    @Override
    public ArrayList<Poker> formarseConCartas(ArrayList<Poker> cartas) {
        ArrayList<Poker> cartasInvolucradas = new ArrayList<>();

        // Caso base #1
        if (cartas.size() < 4) {
            return cartasInvolucradas;
        }

        // Caso recursivo
        if (cartas.size() == 5) {
            ArrayList<Poker> cartasAuxiliar = new ArrayList<>(cartas);
            Collections.sort(cartasAuxiliar);

            boolean cartaDistintaEsLaPrimera = !cartasAuxiliar.get(0).esMismoSimboloQue(cartasAuxiliar.get(1));
            if (cartaDistintaEsLaPrimera) {
                cartasAuxiliar.remove(0);
            } else {
                cartasAuxiliar.remove(4);
            }

            return formarseConCartas(cartasAuxiliar);
        }

        // Caso base #2
        if (!cartas.get(0).esMismoSimboloQue(cartas.get(1)) || !cartas.get(1).esMismoSimboloQue(cartas.get(2)) || !cartas.get(2).esMismoSimboloQue(cartas.get(3))) {
            return cartasInvolucradas;
        }

        cartasInvolucradas.addAll(cartas);
        return cartasInvolucradas;
    }
}
