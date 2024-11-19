package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.jugada.Jugada;

public class Comodin {
    String nombre;
    String descripcion;
    int puntos;
    int multiplicador;
    Activacion activacion;

    public Comodin(String nombre, String descripcion, int puntos, int multiplicador, Activacion activacion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.puntos = puntos;
        this.multiplicador = multiplicador;
        this.activacion = activacion;
    }

    public void modificarPuntaje(Puntaje puntaje, Jugada jugada, int cartasDescartadas) {
        int activaciones = activacion.activaciones(jugada, cartasDescartadas);
        puntaje.sumarValorBase(puntos * activaciones);
        puntaje.sumarMultiplicador(multiplicador * activaciones);
    }
}
