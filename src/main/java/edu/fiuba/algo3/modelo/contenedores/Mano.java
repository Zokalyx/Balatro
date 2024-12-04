package edu.fiuba.algo3.modelo.contenedores;

import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.jugada.Jugada;
import edu.fiuba.algo3.modelo.jugada.JugadaManager;
import edu.fiuba.algo3.modelo.jugada.JugadaNula;

import java.util.ArrayList;
import java.util.Observable;

public class Mano extends Observable {
    ArrayList<Poker> cartas;
    ArrayList<Poker> cartasSeleccionadas;
    ArrayList<Poker> cartasDescartadas;
    int maximoCartas = 8;
    int maximoCartasSeleccionadas = 5;
    Mazo<Poker> mazo;
    JugadaManager jugadaManager;
    Jugada jugadaActiva;

    public Mano(Mazo<Poker> mazo, JugadaManager jugadaManager) {
        cartas = new ArrayList<>();
        cartasSeleccionadas = new ArrayList<>();
        cartasDescartadas = new ArrayList<>();
        this.mazo = mazo;
        this.jugadaManager = jugadaManager;
        jugadaActiva = new JugadaNula();
    }

    public void repartir() {
        while (cartas.size() < maximoCartas) {
            cartas.add(mazo.tomarCarta());
        }
        setChanged();
        notifyObservers();
    }

    public void seleccionarCarta(Poker carta) {
        if (cartasSeleccionadas.size() + 1 > maximoCartasSeleccionadas) {
            throw new SeleccionInvalidaError("Máximo de cartas ya seleccionadas");
        }

        cartasSeleccionadas.add(carta);
        jugadaActiva=jugadaManager.calcularJugada(cartasSeleccionadas);

        setChanged();
        notifyObservers();
    }

    public void deseleccionarCarta(Poker carta) {
        cartasSeleccionadas.remove(carta);

        jugadaActiva=jugadaManager.calcularJugada(cartasSeleccionadas);
        setChanged();
        notifyObservers();
    }

    public Jugada jugar() {
        for (Poker cartaSeleccionada : cartasSeleccionadas) {
            cartas.remove(cartaSeleccionada);
        }
        cartasDescartadas.addAll(cartasSeleccionadas);
        cartasSeleccionadas.clear();
        setChanged();
        notifyObservers();
        Jugada jugadaARetornar = jugadaActiva;
        jugadaActiva=new JugadaNula();
        return jugadaARetornar;
    }

    public void descartar() {
        for (Poker cartaSeleccionada : cartasSeleccionadas) {
            cartas.remove(cartaSeleccionada);
        }
        cartasDescartadas.addAll(cartasSeleccionadas);
        cartasSeleccionadas.clear();
        repartir();
        setChanged();
        notifyObservers();
    }

    public void retornarCartasAMazo() {
        mazo.agregar(cartas);
        cartas.clear();
        mazo.agregar(cartasDescartadas);
        cartasDescartadas.clear();
        setChanged();
        notifyObservers();
    }

    public void agregarCartaExterna(Poker carta) {
        cartas.add(carta);
        setChanged();
        notifyObservers();
    }

    public ArrayList<Poker> getCartas() {
        return cartas;
    }

    public ArrayList<Poker> getSeleccion() {
        return cartasSeleccionadas;
    }

    public ArrayList<Poker> getDescartadas() {
        return cartasDescartadas;
    }

    public Jugada getJugada(){
        return jugadaActiva;
    }

    public boolean estaLlena() {
        return cartas.size() == maximoCartas;
    }
}
