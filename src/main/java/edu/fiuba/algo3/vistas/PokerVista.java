package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.palo.*;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.Observable;
import java.util.Observer;

public class PokerVista extends CartaVista implements Observer {

    public PokerVista(Poker poker) {
        agregarAnimacion(40, 20);

        String simbolo = simboloVisible(poker.getSimbolo());
        Palo palo = poker.getPalo();

        Pane conjuntoSuperior = crearConjuntoSimboloPalo(simbolo, palo);
        Pane conjuntoInferior = crearConjuntoSimboloPalo(simbolo, palo);
        HBox panelCentral = new HBox();
        panelCentral.setAlignment(Pos.CENTER);
        Label puntaje = new Label("" + poker.getValorNumerico());
        Label multiplicacionSimbolo = new Label(" x ");
        Label multiplicador = new Label("" + poker.getMultiplicador());
        panelCentral.getChildren().addAll(puntaje, multiplicacionSimbolo, multiplicador);
        conjuntoInferior.setRotate(180);

        StackPane.setAlignment(conjuntoSuperior, Pos.TOP_LEFT);
        StackPane.setAlignment(conjuntoInferior, Pos.BOTTOM_RIGHT);

        getChildren().addAll(conjuntoSuperior, conjuntoInferior, panelCentral);
    }

    private ImageView paloVisible(Palo palo) {
        Image image;
        if (palo.equals(new Diamante())) {
            image = new Image(getClass().getResource("/diamante.png").toExternalForm());
        } else if (palo.equals(new Trebol())) {
            image = new Image(getClass().getResource("/trebol.png").toExternalForm());
        } else if (palo.equals(new Corazon())) {
            image = new Image(getClass().getResource("/corazon.png").toExternalForm());
        } else if (palo.equals(new Pica())) {
            image = new Image(getClass().getResource("/pica.png").toExternalForm());
        } else {
            throw new RuntimeException("Palo inexistente");
        }
        return new ImageView(image);
    }

    private String colorPalo(Palo palo) {
        if (palo.equals(new Diamante())) {
            return "orange";
        } else if (palo.equals(new Corazon())) {
            return "red";
        } else if (palo.equals(new Pica())) {
            return "black";
        } else if (palo.equals(new Trebol())) {
            return "blue";
        } else {
            return "black";
        }
    }

    private Pane crearConjuntoSimboloPalo(String simbolo, Palo palo) {
        String color = colorPalo(palo);
        Label nombreLabel = new Label(simbolo);
        nombreLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: " + color + "; -fx-font-size: 14;");

        VBox vbox = new VBox(nombreLabel, paloVisible(palo));
        vbox.setAlignment(Pos.CENTER);
        vbox.setMaxHeight(40);
        vbox.setMaxWidth(20);
        return vbox;
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

    @Override
    public void update(Observable o, Object arg) {
    }
}
