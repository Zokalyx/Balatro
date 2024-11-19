package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.jugada.Jugada;

public abstract class Comodin {
    String nombre;
    String descripcion;
    int puntos;
    int multiplicador;

    public Comodin(String nombre, String descripcion, int puntos, int multiplicador) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.puntos = puntos;
        this.multiplicador = multiplicador;
    }

    public abstract void modificarPuntaje(Puntaje puntaje, Jugada jugada, int cantCartasDescartadas);
}
