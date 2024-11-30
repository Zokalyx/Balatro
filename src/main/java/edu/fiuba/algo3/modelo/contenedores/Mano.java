package edu.fiuba.algo3.modelo.contenedores;

import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.jugada.Jugada;
import edu.fiuba.algo3.modelo.jugada.JugadaFactory;

import java.util.ArrayList;

public class Mano {
    ArrayList<Poker> cartas;
    ArrayList<Poker> cartasSeleccionadas;
    int maximoCartas = 8;
    int maximoCartasSeleccionadas = 5;
    Mazo<Poker> mazo;
    JugadaFactory jugadaFactory;

    public Mano(Mazo<Poker> mazo) {
        cartas = new ArrayList<>();
        cartasSeleccionadas = new ArrayList<>();
        this.mazo = mazo;
        jugadaFactory = new JugadaFactory();
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
    }

    public void deseleccionarCarta(Poker carta) {
        cartasSeleccionadas.remove(carta);
    }

    public Jugada jugar() {
        Jugada jugada = jugadaFactory.calcularJugada(cartasSeleccionadas);
        cartasSeleccionadas.clear();
        return jugada;
    }

    public int descartar() {
        int cartasDescartadas = 0;
        for (Poker cartaSeleccionada : cartasSeleccionadas) {
            cartas.remove(cartaSeleccionada);
            cartasDescartadas++;
        }
        cartasSeleccionadas.clear();
        repartir();
        return cartasDescartadas;
    }

    public ArrayList<Poker> getCartas() {
        return cartas;
    }
}
