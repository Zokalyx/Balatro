package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.contenedores.SeleccionInvalidaError;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class ControladorPoker implements EventHandler<MouseEvent> {
    Mano mano;
    Poker poker;
    MediaPlayer mediaPlayerSeleccion;
    MediaPlayer mediaPlayerDeseleccion;

    public ControladorPoker(Mano mano, Poker poker){
        this.mano = mano;
        this.poker = poker;

        mediaPlayerSeleccion = new MediaPlayer(new Media(getClass().getResource("/seleccion_carta.mp3").toExternalForm()));
        mediaPlayerSeleccion.setVolume(0.4);

        mediaPlayerDeseleccion = new MediaPlayer(new Media(getClass().getResource("/deseleccion_carta.mp3").toExternalForm()));
        mediaPlayerDeseleccion.setVolume(0.4);

    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if(mano.getSeleccion().contains(poker)){
            mano.deseleccionarCarta(poker);
            mediaPlayerDeseleccion.seek(Duration.ZERO);
            mediaPlayerDeseleccion.play();
        }else{
            try{
                mano.seleccionarCarta(poker);
                mediaPlayerSeleccion.seek(Duration.ZERO);
                mediaPlayerSeleccion.play();
            } catch (SeleccionInvalidaError e) {

            }


        }
    }
}
