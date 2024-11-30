package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.jugada.Jugada;

public class ComodinIndividual extends Comodin {
    int puntos;
    int multiplicador;
    ActivacionComodin activacionComodin;

    public ComodinIndividual(String nombre, String descripcion, int puntos, int multiplicador, ActivacionComodin activacionComodin) {
        super(nombre, descripcion);
        this.puntos = puntos;
        this.multiplicador = multiplicador;
        this.activacionComodin = activacionComodin;
    }

    @Override
    public void modificarPuntaje(Puntaje puntaje, Jugada jugada, int cartasDescartadas) {
        int activaciones = activacionComodin.activaciones(jugada, cartasDescartadas);
        for (int i = 0; i < activaciones; i++) {
            puntaje.sumarValorBase(puntos);
            puntaje.multiplicarMultiplicador(multiplicador);
        }
    }
}
