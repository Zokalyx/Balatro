package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.jugada.Jugada;

public class ActivacionJugada implements Activacion {
    String jugada;

    public ActivacionJugada(String jugada){
        this.jugada = jugada;
    }

    @Override
    public int activaciones(Jugada jugada, int cartasDescartadas) {
        if (jugada.esEjemplar(this.jugada)) {
            return 1;
        }

        return 0;
    }
}

