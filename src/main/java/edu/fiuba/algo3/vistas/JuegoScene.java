package edu.fiuba.algo3.vistas;


import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.contenedores.Mazo;
import javafx.application.Application;

import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class JuegoScene extends Application {

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();

        try {
            Image image = new Image(new FileInputStream("src/main/resources/freepik_poker_table_background.jpg"));
            BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            root.setBackground(new Background(backgroundImage));
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el imagen");
        }

        ComodinesVista comodines = new ComodinesVista();
        Region espacioCentralVertical = new Region();
        VBox.setVgrow(espacioCentralVertical, Priority.ALWAYS);
        VBox contenedorCentral = new VBox(new PanelPuntajeVista(), espacioCentralVertical, new ManoVista(new Mano(new Mazo<>(new ArrayList<>()))));
        contenedorCentral.prefHeightProperty().bind(root.heightProperty());

        TarotsVista tarots = new TarotsVista();

        // Esto hace que se peguen las demás cosas a los bordes
        // Esto toma todo el espacio restante
        Region espacioDespuesComodines = new Region();
        HBox.setHgrow(espacioDespuesComodines, Priority.ALWAYS);
        Region espacioAntesTarots = new Region();
        HBox.setHgrow(espacioAntesTarots, Priority.ALWAYS);

        HBox hboxMadre = new HBox(comodines, espacioDespuesComodines, contenedorCentral, espacioAntesTarots, tarots);
        hboxMadre.prefWidthProperty().bind(root.widthProperty());

        Button botonSalir = new Button();
        botonSalir.setText("X");
        botonSalir.setOnAction(e -> new MenuScene().start(stage));

        root.getChildren().addAll(hboxMadre, botonSalir);

        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Balatro - Partida");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
