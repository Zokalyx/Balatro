package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.jugada.Jugada;

public interface Activacion {
    public abstract int activaciones(Jugada jugada, int cartasDescartadas);
}
