package edu.fiuba.algo3.modelo.tarot;

import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.jugada.JugadaManager;

public interface ActivacionTarot {
    ModificablePorTarot encontrarObjetoASerModificado(Mano mano, JugadaManager jugadaManager);
}
