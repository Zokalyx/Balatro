package edu.fiuba.algo3.modelo.jugada;

import edu.fiuba.algo3.modelo.Poker;

import java.util.ArrayList;

public class JugadaEscaleraColor extends Jugada {
    public JugadaEscaleraColor() {
        valorBase = 100;
        multiplicadorBase = 8;
    }

    @Override
    public ArrayList<Poker> formarseConCartas(ArrayList<Poker> cartas) {
        ArrayList<Poker> cartasInvolucradas = new ArrayList<>();

        Jugada jugadaAuxiliarEscalera = new JugadaEscalera();
        ArrayList<Poker> cartasEscalera = jugadaAuxiliarEscalera.formarseConCartas(cartas);
        if (cartasEscalera.isEmpty()) {
            return cartasInvolucradas;
        }

        Jugada jugadaAuxiliarColor = new JugadaColor();
        ArrayList<Poker> cartasColor = jugadaAuxiliarColor.formarseConCartas(cartas);
        if (cartasColor.isEmpty()) {
            return cartasInvolucradas;
        }

        cartasInvolucradas.addAll(cartas);
        return cartasInvolucradas;
    }
}
