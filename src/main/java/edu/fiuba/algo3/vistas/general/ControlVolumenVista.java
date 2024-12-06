package edu.fiuba.algo3.vistas.general;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ControlVolumenVista extends VBox {
    public ControlVolumenVista() {
        // Objetos de layout
        Slider sliderMusica = new Slider(0, 2, SonidoManager.getInstancia().getVolumenMusica());
        Label labelMusica = new Label("Música: ");
        Slider sliderEfectos = new Slider(0, 2, SonidoManager.getInstancia().getVolumenEfectos());
        Label labelEfectos = new Label("Efectos: ");

        HBox musica = new HBox(labelMusica, sliderMusica);
        HBox efectos = new HBox(labelEfectos, sliderEfectos);

        getChildren().addAll(musica, efectos);

        // Controladores
        sliderMusica.valueProperty().addListener((observable, oldValue, newValue) -> {
            SonidoManager.getInstancia().setVolumenMusica((double) newValue);
        });
        sliderEfectos.valueProperty().addListener((observable, oldValue, newValue) -> {
            SonidoManager.getInstancia().setVolumenEfectos((double) newValue);
        });

        // Estilo y posicionamiento
        labelMusica.setTextFill(Color.WHITE);
        labelEfectos.setTextFill(Color.WHITE);

        musica.setAlignment(Pos.CENTER_RIGHT);
        efectos.setAlignment(Pos.CENTER_RIGHT);

        setSpacing(20);
        setPadding(new Insets(20, 20, 20, 20));
        setMinSize(300, 100);
        setMaxSize(300, 100);
        setStyle("-fx-background-color: #2c2c2c;");
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);
        dropShadow.setRadius(10);
        setEffect(dropShadow);

        aplicarEstiloASlider(sliderEfectos);
        aplicarEstiloASlider(sliderMusica);
    }

    private void aplicarEstiloASlider(Slider slider) {
        slider.getStylesheets().add(getClass().getResource("/estilo_slider.css").toExternalForm());
    }
}
