package edu.fiuba.algo3.modelo.palo;

import edu.fiuba.algo3.modelo.Poker;

public class Trebol implements Palo {

    public boolean sonDelMismoPalo(Poker carta){
        return carta.getPalo() instanceof Trebol;
    }
}
