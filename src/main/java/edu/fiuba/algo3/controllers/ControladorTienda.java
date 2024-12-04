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

public class ControladorTienda implements EventHandler<MouseEvent> {
    Object carta;
    Tienda tienda;
    Mano mano;
    Comodines comodines;
    Tarots tarots;

    public ControladorTienda(Object carta, Tienda tienda, Mano mano, Comodines comodines, Tarots tarots) {
        this.carta = carta;
        this.tienda = tienda;
        this.mano = mano;
        this.comodines = comodines;
        this.tarots = tarots;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if (carta instanceof Poker) {
            Poker poker = (Poker) carta;
            tienda.comprarPoker(poker);
            mano.agregarCartaExterna(poker);
            tienda.cerrar();

        } else if (carta instanceof Tarot) {
            Tarot tarot = (Tarot) carta;
            tienda.comprarTarot(tarot);
            tarots.agregar(tarot);
            tienda.cerrar();

        } else if (carta instanceof Comodin) {
            Comodin comodin = (Comodin) carta;
            tienda.comprarComodin(comodin);
            comodines.agregar(comodin);
            tienda.cerrar();
        }
    }
}
