package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.Puntaje;

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

    public void modificarPuntaje(Puntaje puntaje) {
        puntaje.sumarMultiplicador(multiplicador);
        puntaje.sumarValorBase(puntos);
    }
}
