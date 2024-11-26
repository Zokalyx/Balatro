package edu.fiuba.algo3.vistas;


import javafx.application.Application;

import javafx.scene.Scene;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;


public class JuegoScene extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        root.setStyle("-fx-background-color: #3b6c34;");

        ComodinesVista comodines = new ComodinesVista();
        VBox contenedorCentral = new VBox(new PanelPuntajeVista(), new ManoVista());
        contenedorCentral.setSpacing(200);

        TarotsVista tarots = new TarotsVista();

        HBox contenedores = new HBox(comodines, contenedorCentral, tarots);
        contenedores.setSpacing(50);

        root.getChildren().addAll(contenedores);

        Scene scene = new Scene(root, 800, 400);
        stage.setTitle("Balatro - Partida");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
