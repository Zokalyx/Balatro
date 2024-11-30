package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.jugada.Jugada;

public class ActivacionComodinJugada implements ActivacionComodin {
    Jugada jugada;

    public ActivacionComodinJugada(Jugada jugada){
        this.jugada = jugada;
    }

    @Override
    public int activaciones(Jugada jugada, int cartasDescartadas) {
        if (jugada.getClass().equals(this.jugada.getClass())) {
            return 1;
        }

        return 0;
    }
}

