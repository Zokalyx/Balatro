package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.jugada.Jugada;

public class ActivacionDescarte implements Activacion {
    @Override
    public int activaciones(Jugada jugada, int cartasDescartadas) {
        return cartasDescartadas;
    }
}
