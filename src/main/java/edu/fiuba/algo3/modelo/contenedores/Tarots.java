package edu.fiuba.algo3.modelo.contenedores;

import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.jugada.Jugada;
import edu.fiuba.algo3.modelo.tarot.Tarot;

import java.util.ArrayList;
import java.util.Observable;

public class Tarots extends Observable {
    ArrayList<Tarot> cartas;
    int capacidad;

    public Tarots() {
        this.cartas = new ArrayList<>();
        this.capacidad = 3;
    }

    public void agregar(Tarot tarot) {
        if (cartas.size() >= capacidad) {
            throw new TarotsLlenoError("No entran más tarots");
        }
        cartas.add(tarot);
        setChanged();
        notifyObservers();
    }

    public ArrayList<Tarot> getArray() {
        return cartas;
    }
}
