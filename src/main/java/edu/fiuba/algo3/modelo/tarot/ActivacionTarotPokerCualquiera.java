package edu.fiuba.algo3.modelo.tarot;

import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.jugada.JugadaManager;

public class ActivacionTarotPokerCualquiera implements ActivacionTarot {
    @Override
    public ModificablePorTarot encontrarObjetoASerModificado(Mano mano, JugadaManager jugadaManager) {
        return mano.getSeleccionUnica();
    }
}
