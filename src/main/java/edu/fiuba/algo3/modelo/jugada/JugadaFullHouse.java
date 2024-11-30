package edu.fiuba.algo3.modelo.jugada;

import edu.fiuba.algo3.modelo.Poker;

import java.util.ArrayList;

public class JugadaFullHouse extends Jugada {
    public JugadaFullHouse() {
        valorBase = 40;
        multiplicadorBase = 4;
    }

    @Override
    public ArrayList<Poker> formarseConCartas(ArrayList<Poker> cartas) {
        ArrayList<Poker> cartasInvolucradas = new ArrayList<>();

        if (cartas.size() != 5) {
            return cartasInvolucradas;
        }

        Jugada jugadaAuxiliarPierna = new JugadaPierna();
        ArrayList<Poker> cartasPierna = jugadaAuxiliarPierna.formarseConCartas(cartas);
        if (cartasPierna.isEmpty()) {
            return cartasInvolucradas;
        }

        ArrayList<Poker> cartasRestantes = new ArrayList<>();
        for (Poker carta : cartas) {
            if (!cartasPierna.contains(carta)) {
                cartasRestantes.add(carta);
            }
        }

        Jugada jugadaAuxiliarPar = new JugadaPar();
        ArrayList<Poker> cartasPar = jugadaAuxiliarPar.formarseConCartas(cartasRestantes);
        if (cartasPar.isEmpty()) {
            return cartasInvolucradas;
        }

        cartasInvolucradas.addAll(cartas);
        return cartasInvolucradas;
    }
}
