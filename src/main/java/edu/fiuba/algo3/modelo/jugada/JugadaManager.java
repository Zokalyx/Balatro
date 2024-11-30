package edu.fiuba.algo3.modelo.jugada;

import edu.fiuba.algo3.modelo.JugadaNoEncontradaError;
import edu.fiuba.algo3.modelo.Poker;

import java.util.ArrayList;

public class JugadaManager {
    ArrayList<Jugada> jugadas;

    public JugadaManager() {
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

    public Jugada calcularJugada(ArrayList<Poker> cartas) {
       for (Jugada jugada : jugadas) {
           ArrayList<Poker> cartasInvolucradas = jugada.formarseConCartas(cartas);
           if (!cartasInvolucradas.isEmpty()) {
               jugada.setCartas(cartasInvolucradas);
               return jugada;
           }
       }

       throw new JugadaNulaError("Hay que seleccionar por lo menos una carta");
    }

    public Jugada getJugada(Jugada jugadaAEncontrar) {
        for (Jugada jugada : jugadas) {
            if (jugada.getClass().equals(jugadaAEncontrar.getClass())) {
                return jugada;
            }
        }

        throw new JugadaNoEncontradaError("No se encontró la jugada");
    }
}
