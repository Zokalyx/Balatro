package edu.fiuba.algo3.modelo.jugada;

import edu.fiuba.algo3.modelo.Poker;

import java.util.ArrayList;
import java.util.Collections;

public class JugadaEscaleraReal extends Jugada {
    public JugadaEscaleraReal() {
        valorBase = 100;
        multiplicadorBase = 8;
    }

    @Override
    public ArrayList<Poker> formarseConCartas(ArrayList<Poker> cartas) {
        ArrayList<Poker> cartasInvolucradas = new ArrayList<>();

        Jugada jugadaAuxiliar = new JugadaEscaleraColor();
        ArrayList<Poker> cartasEscaleraColor = jugadaAuxiliar.formarseConCartas(cartas);
        if (cartasEscaleraColor.isEmpty()) {
            return cartasInvolucradas;
        }

        ArrayList<Poker> cartasAuxiliar = new ArrayList<>(cartas);
        Collections.sort(cartasAuxiliar);

        if (!cartasAuxiliar.get(0).esAs() || !cartasAuxiliar.get(0).esSimboloSiguienteA(cartasAuxiliar.get(4))) {
            return cartasInvolucradas;
        }

        cartasInvolucradas.addAll(cartas);
        return cartasInvolucradas;
    }
}
