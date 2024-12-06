package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.vistas.general.BotonVista;
import edu.fiuba.algo3.vistas.general.EscenaGeneral;
import edu.fiuba.algo3.vistas.menu.MenuScene;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class InformacionScene extends EscenaGeneral {
    BotonVista botonSalir;
    Label tituloReglas;
    Label reglas;
    Label tituloInformacion;
    Label informacion;
    VBox vboxMadre;
    HBox hboxMadre;
    Region regionIntermedia;

    Region margenIzquierda;
    Region margenDerecha;

    public InformacionScene(Stage stage) {
        super(stage);
    }

    protected void crearControladores(Stage stage) {
        botonSalir.setOnMouseClicked(e -> stage.setScene(new MenuScene(stage).getScene()));
    }

    protected Pane crearLayout() {
        AnchorPane root = new AnchorPane();

        tituloReglas = new Label("Reglas");
        reglas = new Label("Cada partida se gana superando una cantidad de rondas. Cada ronda tiene una cantidad de fichas a obtener. Si no se llega a dicho puntaje en la cantidad de manos asignada, se pierde. En cada mano, se pueden descartar hasta 5 cartas y jugar 5 cartas. Las cartas que conformen una mano válida de póker sumarán puntos. Además, la jugada misma y los comodines agregan puntos propios. Los tarots se encuentran a la derecha (opuestos a los comodines) y son consumibles. Antes de empezar cada ronda, está la oportunidad de comprar una carta.\n\nBuena suerte! Recordá: la casa siempre gana...");

        regionIntermedia = new Region();

        tituloInformacion = new Label("Información");
        informacion = new Label("Esta recreación de Balatro es el trabajo práctico N°2 de la materia Paradigmas de Programación (ex Algoritmos III). La vista está hecha con JavaFX.\n\nCréditos: Atuel Fullana, Salvador Mendoza, Brian Conde, Francisco Russo.");

        margenIzquierda = new Region();
        vboxMadre = new VBox(tituloReglas, reglas, regionIntermedia, tituloInformacion, informacion);
        margenDerecha = new Region();

        hboxMadre = new HBox(margenIzquierda, vboxMadre, margenDerecha);

        botonSalir = new BotonVista("X");

        root.getChildren().addAll(hboxMadre, botonSalir);

        return root;
    }

    protected void crearEstilos() {
        AnchorPane.setTopAnchor(botonSalir, 10.0);
        AnchorPane.setRightAnchor(botonSalir, 10.0);

        AnchorPane.setTopAnchor(hboxMadre, 0.0);
        AnchorPane.setRightAnchor(hboxMadre, 0.0);
        AnchorPane.setBottomAnchor(hboxMadre, 0.0);
        AnchorPane.setLeftAnchor(hboxMadre, 0.0);
        HBox.setHgrow(margenIzquierda, Priority.ALWAYS);
        HBox.setHgrow(margenDerecha, Priority.ALWAYS);

        regionIntermedia.setMinHeight(30);
        regionIntermedia.setMaxHeight(30);

        tituloInformacion.setStyle("-fx-text-fill: cyan; -fx-font-size: 25");
        tituloReglas.setStyle("-fx-text-fill: cyan; -fx-font-size: 25");

        informacion.setWrapText(true);
        reglas.setWrapText(true);
        informacion.setStyle("-fx-text-fill: white; -fx-font-size: 15;");
        reglas.setStyle("-fx-text-fill: white; -fx-font-size: 15;");
        informacion.setLineSpacing(7);
        reglas.setLineSpacing(7);

        vboxMadre.setSpacing(30);

        botonSalir.setStyle("-fx-background-color: #4aba91; -fx-font-size: 16; -fx-padding: 10;");

        vboxMadre.setMaxWidth(800);
        vboxMadre.setAlignment(Pos.CENTER);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);
        dropShadow.setRadius(5);

        tituloInformacion.setEffect(dropShadow);
        tituloReglas.setEffect(dropShadow);
    }
}

