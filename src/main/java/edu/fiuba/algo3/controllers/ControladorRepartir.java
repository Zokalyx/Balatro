package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.contenedores.Comodines;
import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.contenedores.Tarots;
import edu.fiuba.algo3.modelo.contenedores.Tienda;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class ControladorRepartir implements EventHandler<MouseEvent> {
    Mano mano;
    MediaPlayer mediaPlayer;

    public ControladorRepartir(Mano mano) {
        this.mano = mano;
        mediaPlayer = new MediaPlayer(new Media(getClass().getResource("/sonido_cartas.mp3").toExternalForm()));
        mediaPlayer.setVolume(0.6);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        mano.repartir();
        mediaPlayer.seek(Duration.ZERO);
        mediaPlayer.play();
    }
}
