package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.vistas.general.SonidoManager;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ControladorRepartir implements EventHandler<MouseEvent> {
    Mano mano;

    public ControladorRepartir(Mano mano) {
        this.mano = mano;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        mano.repartir();
        SonidoManager.getInstancia().play("cartas");
    }
}
