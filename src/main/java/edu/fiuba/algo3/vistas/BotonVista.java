package edu.fiuba.algo3.vistas;

import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class BotonVista extends Button {
    public BotonVista() {
        initialize();
    }

    public BotonVista(String contenido) {
        super(contenido);
        initialize();
    }

    private void initialize() {
        setStyle("-fx-background-color: #4aba91; -fx-font-size: 20; -fx-padding: 20;");

        MediaPlayer mediaPlayer = new MediaPlayer(new Media(getClass().getResource("/sonido_click.wav").toExternalForm()));
        setOnAction(e -> {
            mediaPlayer.setVolume(0.3);
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
        });
    }
}
