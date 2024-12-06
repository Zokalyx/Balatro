package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.contenedores.Tarots;
import edu.fiuba.algo3.modelo.jugada.JugadaManager;
import edu.fiuba.algo3.modelo.tarot.SeleccionParaTarotInvalidaError;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import edu.fiuba.algo3.vistas.general.SonidoManager;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class ControladorTarot implements EventHandler<MouseEvent> {
    Tarots tarots;
    Tarot tarot;
    Mano mano;
    JugadaManager jugadaManager;

    public ControladorTarot(Tarots tarots, Tarot tarot, Mano mano, JugadaManager jugadaManager) {
        this.tarots = tarots;
        this.tarot = tarot;
        this.mano = mano;
        this.jugadaManager = jugadaManager;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        try {
            tarot.encontrarObjetivoYUtilizar(mano, jugadaManager);
            tarots.consumir(tarot);
            SonidoManager.getInstancia().play("fuego");
        } catch (SeleccionParaTarotInvalidaError e) {
            // Nada (capaz un sonido)
        }
    }
}
