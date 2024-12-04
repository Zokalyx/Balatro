package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugada.*;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class PanelPuntajeVista extends HBox implements Observer {
    Label fichas;
    Label multiplicador;
    Label puntajeActual;
    Label puntajeObjetivo;
    Label rondaActual;
    Label rondaObjetivo;
    Label nombreJugada;
    public PanelPuntajeVista(Juego juego, Mano mano, Puntaje puntaje) {
        // Objetos de modelo


        // Objetos de layout
        fichas = new Label("0");
        Label puntajeSimboloMultiplicacion = new Label("x");
        multiplicador = new Label("1.0");

        nombreJugada = new Label("");

        Region espacioIntermedio = new Region();

        Label textoPuntaje = new Label("Fichas: ");
        puntajeActual = new Label("" + juego.getPuntajeActual());
        Label barraPuntaje = new Label("/");
        puntajeObjetivo = new Label("" + juego.getPuntajeObjetivo());
        HBox conjuntoPuntaje = new HBox(textoPuntaje, puntajeActual, barraPuntaje, puntajeObjetivo);

        Label textoRonda = new Label("Ronda: ");
        rondaActual = new Label("" + (juego.getRondaActual() + 1));
        Label barraRonda = new Label("/");
        rondaObjetivo = new Label("" + juego.getRondaObjetivo());
        HBox conjuntoRonda =  new HBox(textoRonda, rondaActual, barraRonda, rondaObjetivo);

        VBox conjuntoRondaPuntaje = new VBox(conjuntoPuntaje, conjuntoRonda);

        getChildren().addAll(fichas, puntajeSimboloMultiplicacion, multiplicador,nombreJugada ,espacioIntermedio, conjuntoRondaPuntaje);

        // Estilo y posicionamiento
        conjuntoRondaPuntaje.setSpacing(15);
        conjuntoRondaPuntaje.setAlignment(Pos.CENTER);
        conjuntoPuntaje.setAlignment(Pos.CENTER);
        conjuntoRonda.setAlignment(Pos.CENTER);

        HBox.setHgrow(espacioIntermedio, Priority.ALWAYS);

        textoRonda.setStyle("-fx-text-fill: white;");
        textoPuntaje.setStyle("-fx-text-fill: white;");
        puntajeActual.setStyle("-fx-text-fill: white;");
        barraPuntaje.setStyle("-fx-text-fill: white;");
        puntajeObjetivo.setStyle("-fx-text-fill: white;");
        nombreJugada.setStyle("-fx-text-fill: white;");
        barraRonda.setStyle("-fx-text-fill: white;");
        rondaActual.setStyle("-fx-text-fill: white;");
        rondaObjetivo.setStyle("-fx-text-fill: white;");
        fichas.setStyle("-fx-text-fill: white; -fx-background-color: #4747ea; -fx-padding: 15 11 10 14; -fx-background-radius: 5;-fx-font-size: 30;");
        puntajeSimboloMultiplicacion.setStyle("-fx-text-fill: white;");
        multiplicador.setStyle("-fx-text-fill: white; -fx-background-color: #f14646; -fx-padding: 15 11 10 14; -fx-background-radius: 5;-fx-font-size: 30;");
        fichas.setAlignment(Pos.BASELINE_CENTER);
        multiplicador.setAlignment(Pos.BASELINE_CENTER);


        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);
        dropShadow.setRadius(5);

        setEffect(dropShadow);
        setSpacing(10);
        setStyle("-fx-background-color: #2c2c2c; -fx-min-width: 1000;-fx-max-width: 1000; -fx-min-height: 140; -fx-max-height: 140; -fx-background-radius: 0 0 20 20; -fx-padding: 10 30 10 80; -fx-font-size: 20;");
        setAlignment(Pos.CENTER);

        fichas.setEffect(dropShadow);

        multiplicador.setEffect(dropShadow);

        // Controladores
        juego.addObserver(this);
        mano.addObserver(this);
        puntaje.addObserver(this);

    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Juego){
            Juego juego = (Juego) o;
            puntajeActual.setText("" + juego.getPuntajeActual());
            puntajeObjetivo.setText("" + juego.getPuntajeObjetivo());
            rondaActual.setText("" + (juego.getRondaActual() + 1));
            rondaObjetivo.setText("" + juego.getRondaObjetivo());
        }
        if(o instanceof Mano){
            Mano mano = (Mano) o;
            Jugada jugada =mano.getJugada();
            fichas.setText("" + jugada.getValor());
            multiplicador.setText("" + jugada.getMultiplicador());
            nombreJugada.setText(obtenerNombreJugada(jugada));

        }
        if(o instanceof Puntaje){
            Puntaje puntaje = (Puntaje) o;
            fichas.setText("" + puntaje.getValor());
            multiplicador.setText("" + puntaje.getMultiplicador());
        }
    }

    private String obtenerNombreJugada(Jugada jugada){
        if(jugada instanceof JugadaEscaleraReal){
            return "Escalera Real";
        }
        else if(jugada instanceof JugadaEscaleraColor){
            return "Escalera Color";
        }
        else if(jugada instanceof JugadaPoker){
            return "Poker";
        }
        else if(jugada instanceof JugadaFullHouse){
            return "Full House";
        }
        else if(jugada instanceof JugadaColor){
            return "Color";
        }
        else if(jugada instanceof JugadaEscalera){
            return "Escalera";
        }
        else if(jugada instanceof JugadaPierna){
            return "Trio";
        }
        else if(jugada instanceof JugadaDoblePar){
            return "Doble Par";
        }
        else if(jugada instanceof JugadaPar){
            return "Par";
        }
        else if(jugada instanceof JugadaCartaAlta){
            return "Carta Alta";
        }
        else{
            return "";
        }

    }

}
