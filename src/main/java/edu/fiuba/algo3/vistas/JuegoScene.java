package edu.fiuba.algo3.vistas;


import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.contenedores.Mazo;
import edu.fiuba.algo3.modelo.jugada.JugadaManager;
import javafx.application.Application;

import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class JuegoScene extends Application {
    @Override
    public void start(Stage stage) {
        AnchorPane root = new AnchorPane(); // Changed to AnchorPane

        Font.loadFont(getClass().getResourceAsStream("/PressStart2P-Regular.ttf"), 20);
        root.setStyle("-fx-font-family: 'Press Start 2P';");

        try {
            Image image = new Image(new FileInputStream("src/main/resources/freepik_poker_table_background.jpg"));
            BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            root.setBackground(new Background(backgroundImage));
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró la imagen");
        }

        Button botonJugar = new Button("Jugar");
        botonJugar.setStyle("-fx-background-color: #4aba91; -fx-font-size: 16; -fx-padding: 10;");
        Button botonDescarte = new Button("Descartar");
        botonDescarte.setStyle("-fx-background-color: #4aba91; -fx-font-size: 16; -fx-padding: 10;");
        Region espacioEntreBotonesIzquierda = new Region();
        HBox.setHgrow(espacioEntreBotonesIzquierda, Priority.ALWAYS);
        Region espacioEntreBotonesDerecha = new Region();
        HBox.setHgrow(espacioEntreBotonesDerecha, Priority.ALWAYS);

        HBox panelInferior = new HBox(botonJugar, espacioEntreBotonesIzquierda, new ManoVista(new Mano(new Mazo<>(new ArrayList<>()), new JugadaManager())), espacioEntreBotonesDerecha, botonDescarte);
        panelInferior.setAlignment(Pos.CENTER);
        panelInferior.setSpacing(10);

        ComodinesVista comodines = new ComodinesVista();
        Region espacioCentralVertical = new Region();
        VBox.setVgrow(espacioCentralVertical, Priority.ALWAYS);
        VBox contenedorCentral = new VBox(new PanelPuntajeVista(null), espacioCentralVertical, panelInferior);
        contenedorCentral.prefHeightProperty().bind(root.heightProperty());
        contenedorCentral.setMinWidth(600);

        TarotsVista tarots = new TarotsVista();

        Region espacioDespuesComodines = new Region();
        HBox.setHgrow(espacioDespuesComodines, Priority.ALWAYS);
        Region espacioAntesTarots = new Region();
        HBox.setHgrow(espacioAntesTarots, Priority.ALWAYS);

        HBox hboxMadre = new HBox(comodines, espacioDespuesComodines, contenedorCentral, espacioAntesTarots, tarots);
        hboxMadre.prefWidthProperty().bind(root.widthProperty());

        Button botonSalir = new Button("X");
        botonSalir.setOnAction(e -> new MenuScene().start(stage));
        AnchorPane.setTopAnchor(botonSalir, 10.0);
        AnchorPane.setRightAnchor(botonSalir, 10.0);
        botonSalir.setStyle("-fx-background-color: #4aba91; -fx-font-size: 16; -fx-padding: 10;");

        root.getChildren().addAll(hboxMadre, botonSalir);

        Scene scene = new Scene(root, 1100, 700);
        stage.setTitle("Balatro - Partida");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

