package edu.fiuba.algo3.modelo.contenedores;

import edu.fiuba.algo3.modelo.Poker;
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
    Mazo mazo;

    public Mano(Mazo mazo) {
        cartas = new ArrayList<>();
        cartasSeleccionadas = new ArrayList<>();
        jugada = new JugadaNula(new ArrayList<>());
        this.mazo = mazo;
    }

    public void repartir() {
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
        cartasSeleccionadas.clear();

        return jugada;
    }

    public int descartar(){
        int cartasDescartadas = 0;
        for (Poker cartaSeleccionada : cartasSeleccionadas) {
            cartas.remove(cartaSeleccionada);
            cartasDescartadas++;
        }
        cartasSeleccionadas.clear();
        repartir();
        jugada = new JugadaNula(new ArrayList<>());
        return cartasDescartadas;
    }
}
