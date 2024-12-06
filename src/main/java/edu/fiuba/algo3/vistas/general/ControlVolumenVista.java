package edu.fiuba.algo3.vistas.general;

import javafx.geometry.Insets;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ControlVolumenVista extends VBox {
    public ControlVolumenVista() {
        // Objetos de layout
        Slider sliderMusica = new Slider(0, 2, 1);
        Slider sliderEfectos = new Slider(0, 2, 1);
        getChildren().addAll(sliderMusica, sliderEfectos);

        // Controladores
        sliderMusica.valueProperty().addListener((observable, oldValue, newValue) -> {
            SonidoManager.getInstancia().setVolumenMusica((double) newValue);
        });
        sliderEfectos.valueProperty().addListener((observable, oldValue, newValue) -> {
            SonidoManager.getInstancia().setVolumenEfectos((double) newValue);
        });

        // Estilo y posicionamiento
        setSpacing(20);
        setPadding(new Insets(20, 20, 20, 20));
        setMinSize(200, 100);
        setMaxSize(200, 100);
        setStyle("-fx-background-color: #2c2c2c;");
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);
        dropShadow.setRadius(10);
        setEffect(dropShadow);
        aplicarEstiloASlider(sliderEfectos);
        aplicarEstiloASlider(sliderMusica);
    }

    private void aplicarEstiloASlider(Slider slider) {
        // todo
    }
}
