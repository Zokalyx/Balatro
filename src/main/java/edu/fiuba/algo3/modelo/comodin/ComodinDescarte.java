package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.jugada.Jugada;

public class ComodinDescarte extends Comodin{
    public ComodinDescarte(String nombre, String descripcion, int puntos, int multiplicador) {
        super(nombre, descripcion, puntos, multiplicador);
    }

    public void modificarPuntaje(Puntaje puntaje, Jugada jugada, int cantCartasDescartadas) {
        for (int i = 1; i <= cantCartasDescartadas; i++) {
            puntaje.sumarMultiplicador(multiplicador);
            puntaje.sumarValorBase(puntos);
        }
    }
}
