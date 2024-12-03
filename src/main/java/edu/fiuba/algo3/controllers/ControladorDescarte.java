package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.juego.Juego;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public class ControladorDescarte implements EventHandler<MouseEvent>  {


    private  Juego juego;
    private  Mano mano;

    public ControladorDescarte(Mano mano, Juego juego){
        this.mano=mano;
        this.juego=juego;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        juego.utilizarDescarte();
        mano.descartar();
    }
}
