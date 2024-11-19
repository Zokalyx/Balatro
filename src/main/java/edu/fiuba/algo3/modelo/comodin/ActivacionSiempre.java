package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.jugada.Jugada;

public class ActivacionSiempre implements Activacion {

    @Override
    public int activaciones(Jugada jugada, int cartasDescartadas) {
        return 1;
    }
}
