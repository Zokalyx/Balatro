package edu.fiuba.algo3.vistas;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class App extends Application {
    Stage stage;
    MediaPlayer mediaPlayer;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setScene(new MenuScene(this).getScene());
        stage.setTitle("Balatro");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/balatro.png")));
        stage.setMaximized(true);
        stage.show();

        mediaPlayer = new MediaPlayer(new Media(getClass().getResource("/Balatro Main Theme - Funk Fusion Cover.mp3").toExternalForm()));
        mediaPlayer.setVolume(0.2);
        mediaPlayer.play();
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
