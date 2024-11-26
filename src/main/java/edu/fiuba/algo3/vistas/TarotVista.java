package edu.fiuba.algo3.vistas;

import javafx.scene.control.Label;

public class TarotVista extends CartaVista {
    public TarotVista() {
        Label label = new Label("Tarot");

        getChildren().addAll(label);
    }
}
