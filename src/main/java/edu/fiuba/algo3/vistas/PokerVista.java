package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.palo.Palo;
import javafx.scene.control.Label;

public class PokerVista extends CartaVista {
    public PokerVista(String nombre, Palo palo) {
        Label nombreLabel = new Label(nombre);
        Label paloLabel = new Label("☘");

        getChildren().addAll(nombreLabel, paloLabel);
    }
}
