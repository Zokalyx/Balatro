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

public class ControladorRepartir implements EventHandler<MouseEvent> {
    Mano mano;

    public ControladorRepartir(Mano mano) {
        this.mano = mano;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        mano.repartir();
    }
}
