package edu.fiuba.algo3.modelo.tarot;

import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.jugada.Jugada;
import edu.fiuba.algo3.modelo.jugada.JugadaManager;

public class ActivacionTarotJugadaParticular implements ActivacionTarot {
    Jugada jugada;

    public ActivacionTarotJugadaParticular(Jugada jugada) {
        this.jugada = jugada;
    }

    @Override
    public ModificablePorTarot encontrarObjetoASerModificado(Mano mano, JugadaManager jugadaManager) {
        return jugadaManager.getJugada(jugada);
    }
}
