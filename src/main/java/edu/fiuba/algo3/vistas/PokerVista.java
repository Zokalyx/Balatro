package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.palo.*;
import javafx.scene.control.Label;

public class PokerVista extends CartaVista {
    public PokerVista(Poker poker) {
        Label nombreLabel = new Label(simboloVisible(poker.getSimbolo()));
        Label paloLabel = new Label(paloVisible(poker.getPalo()));

        getChildren().addAll(nombreLabel, paloLabel);
    }

    private String simboloVisible(String simbolo) {
        switch (simbolo) {
            case "As":
                return "A";
            case "Rey":
                return "K";
            case "Reina":
                return "Q";
            case "Jota":
                return "J";
            default:
                return simbolo;
        }
    }

    private String paloVisible(Palo palo) {
        if (palo.equals(new Diamante())) {
            return "♢";
        } else if (palo.equals(new Trebol())) {
            return "♧";
        } else if (palo.equals(new Corazon())) {
            return "♡";
        } else if (palo.equals(new Pica())) {
            return "♤";
        } else {
            throw new RuntimeException("Palo inexistente");
        }
    }
}
