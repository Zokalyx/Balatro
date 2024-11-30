package edu.fiuba.algo3.modelo.tarot;

import edu.fiuba.algo3.modelo.jugada.Jugada;

public class ActivacionTarotJugadaParticular implements ActivacionTarot {
    Jugada jugada;

    public ActivacionTarotJugadaParticular(Jugada jugada) {
        this.jugada = jugada;
    }

    @Override
    public boolean sePuedeActivarSobre(ModificablePorTarot modificablePorTarot) {
        return modificablePorTarot.getClass().equals(jugada.getClass());
    }
}
