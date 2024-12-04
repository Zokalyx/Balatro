package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.contenedores.Comodines;
import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.contenedores.Tienda;
import edu.fiuba.algo3.modelo.juego.ConfiguracionJuego;
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
    Tienda tienda;
    ConfiguracionJuego configuracion;


    public ControladorJugar(Mano mano, Juego juego, Comodines comodines, Puntaje puntaje, Tienda tienda, ConfiguracionJuego configuracion){
        this.mano = mano;
        this.juego = juego;
        this.comodines=comodines;
        this.puntaje=puntaje;
        this.tienda = tienda;
        this.configuracion = configuracion;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        Jugada jugada = mano.jugar();
        PauseTransition pausa = new PauseTransition(Duration.seconds(0.5));
        pausa.setOnFinished(e -> {

            PauseTransition pausa2 = new PauseTransition(Duration.seconds(0.5));
            jugada.modificarPuntaje(puntaje);
            pausa2.setOnFinished(e2 -> {

                comodines.modificarPuntaje(puntaje, jugada, juego.getDescartesDisponibles());
                PauseTransition pausa3 = new PauseTransition(Duration.seconds(0.5));
                pausa3.setOnFinished(e3 -> {

                    boolean pasoDeRonda = juego.jugarTurno(puntaje.calcularTotal());
                    if (pasoDeRonda) {
                        mano.retornarCartasAMazo();
                        int rondaActual = juego.getRondaActual();
                        tienda.abrir(configuracion.getComodines(rondaActual), configuracion.getTarots(rondaActual), configuracion.getPokers(rondaActual));
                    } else {
                        mano.repartir();
                    }
                    puntaje.reiniciar();

                });
                pausa3.play();

            });
            pausa2.play();

        });
        pausa.play();
    }
}
