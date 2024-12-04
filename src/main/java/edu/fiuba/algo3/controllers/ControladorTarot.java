package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.contenedores.Tarots;
import edu.fiuba.algo3.modelo.jugada.Jugada;
import edu.fiuba.algo3.modelo.jugada.JugadaEscalera;
import edu.fiuba.algo3.modelo.jugada.JugadaManager;
import edu.fiuba.algo3.modelo.tarot.ModificablePorTarot;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ControladorTarot implements EventHandler<MouseEvent> {
    Tarots tarots;
    Tarot tarot;

    public ControladorTarot(Tarots tarots, Tarot tarot) {
        this.tarots = tarots;
        this.tarot = tarot;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        ModificablePorTarot modificable;

        // Cómo hacemos esto???
        // No sabemos sobre qué se aplica el tarot, o sea que debemos obtener como modificablePorTarot

        // tarot.modificar(modificable);

        tarots.consumir(tarot);
    }
}
