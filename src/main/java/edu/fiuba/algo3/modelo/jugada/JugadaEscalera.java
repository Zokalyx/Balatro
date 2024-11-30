package edu.fiuba.algo3.modelo.jugada;

import edu.fiuba.algo3.modelo.Poker;

import java.util.ArrayList;
import java.util.Collections;

public class JugadaEscalera extends Jugada {
    public JugadaEscalera() {
        valorBase = 30;
        multiplicadorBase = 4;
    }

    @Override
    public ArrayList<Poker> formarseConCartas(ArrayList<Poker> cartas) {
        ArrayList<Poker> cartasInvolucradas = new ArrayList<>();

        if (cartas.size() != 5) {
            return cartasInvolucradas;
        }

        ArrayList<Poker> cartasAuxiliar = new ArrayList<>(cartas);
        Collections.sort(cartasAuxiliar);

        // Caso especial: Hay un As y un Rey.
        // Movemos el As al final.
        if (cartasAuxiliar.get(0).esAs() && cartasAuxiliar.get(0).esSimboloSiguienteA(cartasAuxiliar.get(4))) {
            Poker as = cartasAuxiliar.get(0);
            cartasAuxiliar.remove(as);
            cartasAuxiliar.add(as);
        }

        // Verificar que las cartas tengan valores consecutivos
        for (int i = 0; i < cartasAuxiliar.size() - 1; i++) {
            if (!cartasAuxiliar.get(i).esSimboloAnteriorA(cartasAuxiliar.get(i + 1))) {
                return cartasInvolucradas;
            }
        }

        // Agregar de esta manera para mantener el orden
        for (Poker carta : cartas) {
            if (cartasAuxiliar.contains(carta)) {
                cartasInvolucradas.add(carta);
            }
        }

        return cartasInvolucradas;
    }
}
