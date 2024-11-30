package edu.fiuba.algo3.modelo.jugada;

import edu.fiuba.algo3.modelo.Poker;

import java.util.ArrayList;

public class JugadaDoblePar extends Jugada {

    public JugadaDoblePar() {
        valorBase = 20;
        multiplicadorBase = 2;
    }

    @Override
    public ArrayList<Poker> formarseConCartas(ArrayList<Poker> cartas) {
        ArrayList<Poker> cartasInvolucradas = new ArrayList<>();

        Jugada jugadaAuxiliar = new JugadaPar();
        ArrayList<Poker> primerPar = jugadaAuxiliar.formarseConCartas(cartas);
        if (primerPar.isEmpty()) {
            return cartasInvolucradas;
        }

        ArrayList<Poker> cartasRestantes = new ArrayList<>();
        for (Poker carta : cartas) {
            if (!primerPar.contains(carta)) {
                cartasRestantes.add(carta);
            }
        }

        ArrayList<Poker> segundoPar = jugadaAuxiliar.formarseConCartas(cartasRestantes);
        if (segundoPar.isEmpty()) {
            return cartasInvolucradas;
        }

        // Agregar de esta manera para mantener orden original
        for (Poker carta : cartas) {
            if (primerPar.contains(carta) || segundoPar.contains(carta)) {
                cartasInvolucradas.add(carta);
            }
        }
        return cartasInvolucradas;
    }
}
