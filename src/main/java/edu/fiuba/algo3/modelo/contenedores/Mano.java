package edu.fiuba.algo3.modelo.contenedores;

import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.jugada.Jugada;
import edu.fiuba.algo3.modelo.jugada.JugadaManager;

import java.util.ArrayList;

public class Mano {
    ArrayList<Poker> cartas;
    ArrayList<Poker> cartasSeleccionadas;
    ArrayList<Poker> cartasDescartadas;
    int maximoCartas = 8;
    int maximoCartasSeleccionadas = 5;
    Mazo<Poker> mazo;
    JugadaManager jugadaManager;
    int descartesDisponibles;

    public Mano(Mazo<Poker> mazo, JugadaManager jugadaManager) {
        cartas = new ArrayList<>();
        cartasSeleccionadas = new ArrayList<>();
        cartasDescartadas = new ArrayList<>();
        this.mazo = mazo;
        this.jugadaManager = jugadaManager;
        descartesDisponibles = 5;
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
        Jugada jugada = jugadaManager.calcularJugada(cartasSeleccionadas);
        for (Poker cartaSeleccionada : cartasSeleccionadas) {
            cartas.remove(cartaSeleccionada);
        }
        cartasDescartadas.addAll(cartasSeleccionadas);
        cartasSeleccionadas.clear();
        return jugada;
    }

    public int descartar() {
        int cantidadDescartada = cartasSeleccionadas.size();
        if (cantidadDescartada > descartesDisponibles) {
            throw new DescartesInsuficientesError("No quedan descartes");
        }
        descartesDisponibles -= cantidadDescartada;

        for (Poker cartaSeleccionada : cartasSeleccionadas) {
            cartas.remove(cartaSeleccionada);
        }
        cartasDescartadas.addAll(cartasSeleccionadas);
        cartasSeleccionadas.clear();
        repartir();
        return cantidadDescartada;
    }

    public void setDescartesDisponibles(int descartesDisponibles) {
        this.descartesDisponibles = descartesDisponibles;
    }

    public void retornarDescarteAMazo() {
        mazo.agregar(cartasDescartadas);
        cartasDescartadas.clear();
    }

    public ArrayList<Poker> getCartas() {
        return cartas;
    }
}
