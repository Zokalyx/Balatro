package edu.fiuba.algo3.vistas;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class PanelPuntajeVista extends HBox {
    public PanelPuntajeVista() {
        Label puntaje = new Label("123");
        Label ronda = new Label("Ronda 4");

        setStyle("-fx-background-color: #4c4c4c; -fx-min-width: 400; -fx-min-height: 100;");

        getChildren().addAll(puntaje, ronda);
    }
}
