package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.jugada.Jugada;

public class ComodinBonusPorManoJugada extends Comodin
{
    String activacion;
    public ComodinBonusPorManoJugada(String nombre, String descripcion, String activacion, int puntos, int multiplicador) {
        super(nombre, descripcion, puntos, multiplicador);
        this.activacion = activacion;
    }

    public void modificarPuntaje(Puntaje puntaje, Jugada jugada, int cantCartasDescartadas) {
        if(jugada.esEjemplar(activacion)) {
            puntaje.sumarMultiplicador(multiplicador);
            puntaje.sumarValorBase(puntos);
        }
    }

}
