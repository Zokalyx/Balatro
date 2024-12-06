package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.contenedores.*;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import edu.fiuba.algo3.vistas.general.SonidoManager;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

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
        try {
            if (carta instanceof Poker) {
                Poker poker = (Poker) carta;
                tienda.comprarPoker(poker);
                mano.agregarCartaExterna(poker);

            } else if (carta instanceof Tarot) {
                Tarot tarot = (Tarot) carta;
                tienda.comprarTarot(tarot);
                tarots.agregar(tarot);

            } else if (carta instanceof Comodin) {
                Comodin comodin = (Comodin) carta;
                tienda.comprarComodin(comodin);
                comodines.agregar(comodin);
            }

            tienda.cerrar();

            SonidoManager.getInstancia().play("moneda");

        } catch (ManoLlenaError | ComodinesLlenoError | TarotsLlenoError e) {
            // Nada (o un sonido)
        }
    }
}
