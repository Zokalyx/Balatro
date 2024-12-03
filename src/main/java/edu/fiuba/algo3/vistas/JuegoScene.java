package edu.fiuba.algo3.vistas;


import edu.fiuba.algo3.modelo.LectorJson;
import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.contenedores.Mazo;
import edu.fiuba.algo3.modelo.juego.Juego;
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
        AnchorPane root = new AnchorPane();

        cargarFuenteDeTexto(root);
        cargarFondo(root);

        // Objetos de modelo
        Mano mano = new Mano(new Mazo<>(new ArrayList<>()), new JugadaManager());
        Juego juego = new Juego(new LectorJson().leerConfiguracion());

        // Objetos de layout
        Button botonJugar = new Button("Jugar");
        Region espacioEntreBotonesIzquierda = new Region();
        ManoVista manoVista = new ManoVista(mano);
        Region espacioEntreBotonesDerecha = new Region();
        Button botonDescarte = new Button("Descartar");
        HBox panelInferior = new HBox(botonJugar, espacioEntreBotonesIzquierda, manoVista, espacioEntreBotonesDerecha, botonDescarte);

        PanelPuntajeVista panelPuntajeVista = new PanelPuntajeVista(juego);
        Region espacioCentralVertical = new Region();
        VBox contenedorCentral = new VBox(panelPuntajeVista, espacioCentralVertical, panelInferior);

        ComodinesVista comodines = new ComodinesVista();
        Region espacioDespuesComodines = new Region();
        Region espacioAntesTarots = new Region();
        TarotsVista tarots = new TarotsVista();
        HBox hboxMadre = new HBox(comodines, espacioDespuesComodines, contenedorCentral, espacioAntesTarots, tarots);

        Button botonSalir = new Button("X");

        root.getChildren().addAll(hboxMadre, botonSalir);

        // Estilo y posicionamiento
        botonJugar.setStyle("-fx-background-color: #4aba91; -fx-font-size: 16; -fx-padding: 10;");
        botonDescarte.setStyle("-fx-background-color: #4aba91; -fx-font-size: 16; -fx-padding: 10;");
        botonSalir.setStyle("-fx-background-color: #4aba91; -fx-font-size: 16; -fx-padding: 10;");
        AnchorPane.setTopAnchor(botonSalir, 10.0);
        AnchorPane.setRightAnchor(botonSalir, 10.0);

        panelInferior.setAlignment(Pos.CENTER);
        panelInferior.setSpacing(10);

        contenedorCentral.prefHeightProperty().bind(root.heightProperty());
        contenedorCentral.setMinWidth(600);

        VBox.setVgrow(espacioCentralVertical, Priority.ALWAYS);
        HBox.setHgrow(espacioEntreBotonesIzquierda, Priority.ALWAYS);
        HBox.setHgrow(espacioEntreBotonesDerecha, Priority.ALWAYS);
        HBox.setHgrow(espacioDespuesComodines, Priority.ALWAYS);
        HBox.setHgrow(espacioAntesTarots, Priority.ALWAYS);

        hboxMadre.prefWidthProperty().bind(root.widthProperty());

        // Controladores
        botonSalir.setOnAction(e -> new MenuScene().start(stage));

        cargarEscenaEnStage(stage, root);
    }

    private static void cargarEscenaEnStage(Stage stage, AnchorPane root) {
        Scene scene = new Scene(root, 1100, 700);
        stage.setTitle("Balatro - Partida");
        stage.setScene(scene);
        stage.show();
    }

    private void cargarFuenteDeTexto(AnchorPane root) {
        Font.loadFont(getClass().getResourceAsStream("/PressStart2P-Regular.ttf"), 20);
        root.setStyle("-fx-font-family: 'Press Start 2P';");
    }

    private static void cargarFondo(AnchorPane root) {
        try {
            Image image = new Image(new FileInputStream("src/main/resources/freepik_poker_table_background.jpg"));
            BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            root.setBackground(new Background(backgroundImage));
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró la imagen");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

