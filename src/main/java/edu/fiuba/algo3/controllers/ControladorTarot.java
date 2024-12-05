package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.contenedores.Tarots;
import edu.fiuba.algo3.modelo.jugada.JugadaManager;
import edu.fiuba.algo3.modelo.tarot.SeleccionParaTarotInvalidaError;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class ControladorTarot implements EventHandler<MouseEvent> {
    Tarots tarots;
    Tarot tarot;
    Mano mano;
    JugadaManager jugadaManager;

    MediaPlayer mediaPlayer;

    public ControladorTarot(Tarots tarots, Tarot tarot, Mano mano, JugadaManager jugadaManager) {
        this.tarots = tarots;
        this.tarot = tarot;
        this.mano = mano;
        this.jugadaManager = jugadaManager;

        mediaPlayer = new MediaPlayer(new Media(getClass().getResource("/fuego.wav").toExternalForm()));
        mediaPlayer.setVolume(0.8);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        try {
            tarot.encontrarObjetivoYUtilizar(mano, jugadaManager);
            tarots.consumir(tarot);
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
        } catch (SeleccionParaTarotInvalidaError e) {
            // Nada.
        }
    }
}
