package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.juego.Juego;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PanelPuntajeVista extends HBox {
    public PanelPuntajeVista(Juego juego) {
        // Objetos de modelo


        // Objetos de layout
        Label fichas = new Label("0");
        Label puntajeSimboloMultiplicacion = new Label("x");
        Label multiplicador = new Label("1");

        Region espacioIntermedio = new Region();

        Label puntajeActual = new Label("" + juego.getPuntajeActual());
        Label barraPuntaje = new Label("/");
        Label puntajeObjetivo = new Label("" + juego.getPuntajeObjetivo());
        HBox conjuntoPuntaje = new HBox(puntajeActual, barraPuntaje, puntajeObjetivo);

        Label rondaActual = new Label("" + juego.getRondaActual());
        Label barraRonda = new Label("/");
        Label rondaObjetivo = new Label("" + juego.getRondaObjetivo());
        HBox conjuntoRonda =  new HBox(rondaActual, barraRonda, rondaObjetivo);

        VBox conjuntoRondaPuntaje = new VBox(conjuntoPuntaje, conjuntoRonda);

        getChildren().addAll(fichas, puntajeSimboloMultiplicacion, multiplicador, espacioIntermedio, conjuntoRondaPuntaje);

        // Estilo y posicionamiento
        conjuntoRondaPuntaje.setSpacing(15);
        conjuntoRondaPuntaje.setAlignment(Pos.CENTER);
        conjuntoPuntaje.setAlignment(Pos.CENTER);
        conjuntoRonda.setAlignment(Pos.CENTER);

        HBox.setHgrow(espacioIntermedio, Priority.ALWAYS);

        puntajeActual.setStyle("-fx-text-fill: white;");
        barraPuntaje.setStyle("-fx-text-fill: white;");
        puntajeObjetivo.setStyle("-fx-text-fill: white;");
        barraRonda.setStyle("-fx-text-fill: white;");
        rondaActual.setStyle("-fx-text-fill: white;");
        rondaObjetivo.setStyle("-fx-text-fill: white;");
        fichas.setStyle("-fx-text-fill: white; -fx-background-color: #4747ea; -fx-padding: 10; -fx-background-radius: 5;");
        puntajeSimboloMultiplicacion.setStyle("-fx-text-fill: white;");
        multiplicador.setStyle("-fx-text-fill: white; -fx-background-color: #f14646; -fx-padding: 10; -fx-background-radius: 5;");

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);
        dropShadow.setRadius(5);

        setEffect(dropShadow);
        setSpacing(10);
        setStyle("-fx-background-color: #2c2c2c; -fx-min-width: 400; -fx-min-height: 100; -fx-max-height: 100; -fx-background-radius: 0 0 20 20; -fx-padding: 10 30 10 80; -fx-font-size: 20;");
        setAlignment(Pos.CENTER);

        fichas.setEffect(dropShadow);

        multiplicador.setEffect(dropShadow);

        // Controladores

    }
}
