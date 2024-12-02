package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.palo.*;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.util.Observable;
import java.util.Observer;

public class PokerVista extends CartaVista implements Observer {
    public PokerVista(Poker poker) {
        String simbolo = simboloVisible(poker.getSimbolo());
        String palo = paloVisible(poker.getPalo());
        String color = colorPalo(poker.getPalo());

        Pane conjuntoSuperior = crearConjuntoSimboloPalo(simbolo, palo, color);
        Pane conjuntoInferior = crearConjuntoSimboloPalo(simbolo, palo, color);
        conjuntoInferior.setRotate(180);

        getChildren().addAll(conjuntoSuperior, conjuntoInferior);
    }

    private String colorPalo(Palo palo) {
        if (palo.equals(new Pica()) || palo.equals(new Trebol())) {
            return "black";
        } else {
            return "red";
        }
    }

    private Pane crearConjuntoSimboloPalo(String simbolo, String palo, String color) {
        Label nombreLabel = new Label(simbolo);
        Label paloLabel = new Label(palo);

        nombreLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: " + color + "; -fx-font-size: 14;");
        paloLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: " + color + "; -fx-font-size: 14;");

        return new VBox(nombreLabel, paloLabel);
    }

    private String simboloVisible(String simbolo) {
        switch (simbolo) {
            case "As":
                return "A";
            case "Rey":
                return "K";
            case "Reina":
                return "Q";
            case "Jota":
                return "J";
            default:
                return simbolo;
        }
    }

    private String paloVisible(Palo palo) {
        if (palo.equals(new Diamante())) {
            return "♢";
        } else if (palo.equals(new Trebol())) {
            return "♧";
        } else if (palo.equals(new Corazon())) {
            return "♡";
        } else if (palo.equals(new Pica())) {
            return "♤";
        } else {
            throw new RuntimeException("Palo inexistente");
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
