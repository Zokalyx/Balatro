package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.jugada.Jugada;

public class ActivacionComodinDescarte implements ActivacionComodin {
    @Override
    public int activaciones(Jugada jugada, int descartesDisponibles) {
        return descartesDisponibles;
    }
}
