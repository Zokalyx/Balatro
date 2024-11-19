package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.jugada.Jugada;

public class ComodinPuntaje extends Comodin {

    public ComodinPuntaje(String nombre, String descripcion, int puntos, int multiplicador) {
        super(nombre, descripcion, puntos, multiplicador);
    }

    @Override
    public void modificarPuntaje(Puntaje puntaje, Jugada jugada, int cantCartasDescartadas) {
        puntaje.sumarMultiplicador(multiplicador);
        puntaje.sumarValorBase(puntos);
    }
}
