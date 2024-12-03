package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.contenedores.Comodines;
import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugada.Jugada;
import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class ControladorJugar  implements EventHandler<MouseEvent> {

    Comodines comodines;
    Juego juego;
    Mano mano;
    Puntaje puntaje;

    public ControladorJugar(Mano mano, Juego juego, Comodines comodines, Puntaje puntaje){
        this.mano = mano;
        this.juego = juego;
        this.comodines=comodines;
        this.puntaje=puntaje;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        Jugada jugada = mano.jugar();
        PauseTransition pausa = new PauseTransition(Duration.seconds(0.6));
        pausa.setOnFinished(e -> {
            PauseTransition pausa2 = new PauseTransition(Duration.seconds(0.4));
            jugada.modificarPuntaje(puntaje);
            pausa2.setOnFinished(e2 -> {
                comodines.modificarPuntaje(puntaje, jugada, juego.getDescartesDisponibles());
                boolean pasoDeRonda = juego.jugarTurno(puntaje.calcularTotal());
                mano.repartir();
                if (pasoDeRonda) {
                    mano.retornarCartasAMazo();
                }
                puntaje.reiniciar();
            });

            pausa2.play();
        });
        pausa.play();
    }
}
