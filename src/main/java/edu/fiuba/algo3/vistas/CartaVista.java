package edu.fiuba.algo3.vistas;

import javafx.scene.layout.VBox;

public abstract class CartaVista extends VBox {
    public CartaVista() {
        setStyle("-fx-background-color: #fff; -fx-background-radius: 10; -fx-border-color: black; -fx-border-width: 2");
        setMinSize(100, 160);
    }
}