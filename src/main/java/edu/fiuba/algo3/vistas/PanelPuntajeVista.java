package edu.fiuba.algo3.vistas;

import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class PanelPuntajeVista extends HBox {
    public PanelPuntajeVista() {
        Label puntaje = new Label("123");
        Label ronda = new Label("Ronda 4");

        puntaje.setStyle("-fx-text-fill: white;");
        ronda.setStyle("-fx-text-fill: white;");
        setStyle("-fx-background-color: #2c2c2c; -fx-min-width: 400; -fx-min-height: 100; -fx-background-radius: 0 0 20 20; -fx-padding: 10; -fx-font-size: 20");

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);
        dropShadow.setRadius(5);
        setEffect(dropShadow);

        getChildren().addAll(puntaje, ronda);
    }
}
