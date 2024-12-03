package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.contenedores.SeleccionInvalidaError;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ControladorPoker implements EventHandler<MouseEvent> {
    Mano mano;
    Poker poker;

    public ControladorPoker(Mano mano, Poker poker){

        this.mano = mano;
        this.poker = poker;

    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if(mano.getSeleccion().contains(poker)){
            mano.deseleccionarCarta(poker);
        }else{
            try{
                mano.seleccionarCarta(poker);
            } catch (SeleccionInvalidaError e) {

            }


        }
    }
}
