package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.contenedores.SeleccionInvalidaError;
import edu.fiuba.algo3.vistas.general.SonidoManager;
import edu.fiuba.algo3.vistas.juego.JuegoScene;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class ControladorPoker implements EventHandler<MouseEvent> {
    Mano mano;
    Poker poker;
    JuegoScene vista;

    public ControladorPoker(Mano mano, Poker poker, JuegoScene vista) {
        this.mano = mano;
        this.poker = poker;
        this.vista = vista;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (vista.getBloquearBotones()) {
            return;
        }

        if(mano.getSeleccion().contains(poker)){
            mano.deseleccionarCarta(poker);
            SonidoManager.getInstancia().play("deseleccion_carta");
        }else{
            try{
                mano.seleccionarCarta(poker);
                SonidoManager.getInstancia().play("seleccion_carta");
            } catch (SeleccionInvalidaError e) {
                SonidoManager.getInstancia().play("error");
            }
        }
    }
}
