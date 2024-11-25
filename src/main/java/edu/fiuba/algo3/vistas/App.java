package edu.fiuba.algo3.vistas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    Scene escenaMenu;
    Scene escenaJuego;
    Stage stage;

    public App() {
        escenaMenu = crearEscenaMenu();
        escenaJuego = crearEscenaJuego();
    }

    private Scene crearEscenaMenu() {
        Label titulo = new Label("Balatro");
        Button botonJugar = new Button("Jugar");
        botonJugar.setOnAction(e -> stage.setScene(escenaJuego));
        VBox layout = new VBox(titulo, botonJugar);
        return new Scene(layout, 640, 480);
    }

    private Scene crearEscenaJuego() {
        Button botonVolver = new Button("Menú");
        botonVolver.setOnAction(e -> stage.setScene(escenaMenu));
        VBox layout = new VBox(botonVolver);
        return new Scene(layout, 640, 480);
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setScene(escenaMenu);
        stage.setTitle("Balatro");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}