package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.jugada.Jugada;

public abstract class Comodin {
    String nombre;
    String descripcion;

    Comodin(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // La jugada y las cartas descartadas son la "Acción" del jugador.
    // No necesariamente todos sus datos serán utilizados en el método.
    public abstract void modificarPuntaje(Puntaje puntaje, Jugada jugada, int descartesDisponibles);
}
