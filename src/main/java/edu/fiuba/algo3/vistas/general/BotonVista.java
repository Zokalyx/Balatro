package edu.fiuba.algo3.vistas.general;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class BotonVista extends Button {
    ScaleTransition transicion;

    public BotonVista() {
        initialize();
    }

    public BotonVista(String contenido) {
        super(contenido);
        initialize();
    }

    private void initialize() {
        // Controladores
        setOnAction(e -> {
            SonidoManager.getInstancia().play("click");
        });

        transicion = new ScaleTransition(Duration.millis(70), this);
        transicion.setInterpolator(Interpolator.EASE_OUT);
        setOnMouseEntered(e -> {
            transicion.setToX(1.1);
            transicion.setToY(1.1);
            transicion.stop();
            transicion.play();
        });
        setOnMouseExited(e -> {
            transicion.setToX(1);
            transicion.setToY(1);
            transicion.stop();
            transicion.play();
        });

        // Estilo y posicionamiento
        setStyle("-fx-background-color: #4aba91; -fx-font-size: 20; -fx-padding: 20;");
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);
        dropShadow.setRadius(10);
        setEffect(dropShadow);
    }
}
