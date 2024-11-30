package edu.fiuba.algo3.modelo.jugada;

import edu.fiuba.algo3.modelo.Poker;

import java.util.ArrayList;

public class JugadaFactory {
    ArrayList<Jugada> jugadas;

    public JugadaFactory() {
        jugadas = new ArrayList<>();
        jugadas.add(new JugadaEscaleraReal());
        jugadas.add(new JugadaEscaleraColor());
        jugadas.add(new JugadaPoker());
        jugadas.add(new JugadaFullHouse());
        jugadas.add(new JugadaColor());
        jugadas.add(new JugadaEscalera());
        jugadas.add(new JugadaPierna());
        jugadas.add(new JugadaDoblePar());
        jugadas.add(new JugadaPar());
        jugadas.add(new JugadaCartaAlta());
    }

    public Jugada obtenerJugada(ArrayList<Poker> cartas) {
       for (Jugada jugada : jugadas) {
           ArrayList<Poker> cartasInvolucradas = jugada.formarseConCartas(cartas);
           if (!cartasInvolucradas.isEmpty()) {
               jugada.setCartas(cartasInvolucradas);
               return jugada;
           }
       }

       throw new JugadaNulaError("Hay que seleccionar por lo menos una carta");
    }
}
