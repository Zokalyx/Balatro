package edu.fiuba.algo3.modelo.tarot;

import edu.fiuba.algo3.modelo.Poker;

public class ActivacionTarotPokerCualquiera implements ActivacionTarot {
    @Override
    public boolean sePuedeActivarSobre(ModificablePorTarot modificablePorTarot) {
        return modificablePorTarot.getClass().equals(Poker.class);
    }
}
