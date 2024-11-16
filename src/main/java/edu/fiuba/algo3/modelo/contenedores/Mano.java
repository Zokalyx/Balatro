package edu.fiuba.algo3.modelo.contenedores;

import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.jugada.Jugada;
import edu.fiuba.algo3.modelo.jugada.JugadaFactory;
import edu.fiuba.algo3.modelo.jugada.JugadaNula;

import java.util.ArrayList;

public class Mano {
    ArrayList<Poker> cartas;
    ArrayList<Poker> cartasSeleccionadas;
    int maximoCartas = 8;
    int maximoCartasSeleccionadas = 5;
    Jugada jugada;

    public Mano() {
        cartas = new ArrayList<>();
        cartasSeleccionadas = new ArrayList<>();
        jugada = new JugadaNula(new ArrayList<>());
    }

    public void repartirUsando(Mazo mazo) {
        while (cartas.size() < maximoCartas) {
            cartas.add(mazo.tomarCarta());
        }
    }

    public void seleccionarCarta(Poker carta) {
        if (cartasSeleccionadas.size() + 1 > maximoCartasSeleccionadas) {
            throw new SeleccionInvalidaError("Máximo de cartas ya seleccionadas");
        }

        cartasSeleccionadas.add(carta);
        jugada = new JugadaFactory().obtenerJugada(cartasSeleccionadas);
    }

    public void deseleccionarCarta(Poker carta) {
        cartasSeleccionadas.remove(carta);
        jugada = new JugadaFactory().obtenerJugada(cartasSeleccionadas);
    }

    public Jugada jugar() {
        for (Poker cartaSeleccionada : cartasSeleccionadas) {
            cartas.remove(cartaSeleccionada);
        }

        return jugada;
    }

    public void modificarPuntaje(Puntaje puntaje) {
        // Según las cartas en la mano modifica el puntaje
    }
}
