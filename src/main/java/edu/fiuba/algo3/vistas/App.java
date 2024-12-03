package edu.fiuba.algo3.vistas;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    Stage stage;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setScene(new MenuScene(this).getScene());
        stage.setMaximized(true);
        stage.show();
    }

    public void iniciarJuego() {
        stage.setScene(new JuegoScene(this).getScene());
    }

    public void cerrarVentana() {
        stage.close();
    }

    public void volverAMenu() {
        stage.setScene(new MenuScene(this).getScene());
    }
}
