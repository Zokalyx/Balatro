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
        Label puntajeActual = new Label("200");
        Label barraPuntaje = new Label("/");
        Label puntajeObjetivo = new Label("1000");
        HBox conjuntoPuntaje = new HBox(puntajeActual, barraPuntaje, puntajeObjetivo);
        conjuntoPuntaje.setAlignment(Pos.CENTER);

        Label rondaActual = new Label("3");
        Label barraRonda = new Label("/");
        Label rondaObjetivo = new Label("7");
        HBox conjuntoRonda =  new HBox(rondaActual, barraRonda, rondaObjetivo);
        conjuntoRonda.setAlignment(Pos.CENTER);

        Label fichasPuntaje = new Label("fichas");
        Label puntajeSimboloMultiplicacion = new Label("x");
        Label multiplicadorPuntaje = new Label("mult");

        VBox conjuntoRondaPuntaje = new VBox(conjuntoPuntaje, conjuntoRonda);
        conjuntoRondaPuntaje.setSpacing(15);
        conjuntoRondaPuntaje.setAlignment(Pos.CENTER);

        Region espacioIntermedio = new Region();
        HBox.setHgrow(espacioIntermedio, Priority.ALWAYS);

        setSpacing(10);
        getChildren().addAll(fichasPuntaje, puntajeSimboloMultiplicacion, multiplicadorPuntaje, espacioIntermedio, conjuntoRondaPuntaje);

        puntajeActual.setStyle("-fx-text-fill: white;");
        barraPuntaje.setStyle("-fx-text-fill: white;");
        puntajeObjetivo.setStyle("-fx-text-fill: white;");
        barraRonda.setStyle("-fx-text-fill: white;");
        rondaActual.setStyle("-fx-text-fill: white;");
        rondaObjetivo.setStyle("-fx-text-fill: white;");
        fichasPuntaje.setStyle("-fx-text-fill: white; -fx-background-color: #4747ea; -fx-padding: 10; -fx-background-radius: 5;");
        puntajeSimboloMultiplicacion.setStyle("-fx-text-fill: white;");
        multiplicadorPuntaje.setStyle("-fx-text-fill: white; -fx-background-color: #f14646; -fx-padding: 10; -fx-background-radius: 5;");

        setStyle("-fx-background-color: #2c2c2c; -fx-min-width: 400; -fx-min-height: 100; -fx-max-height: 100; -fx-background-radius: 0 0 20 20; -fx-padding: 10 30 10 80; -fx-font-size: 20;");
        setAlignment(Pos.CENTER);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);
        dropShadow.setRadius(5);
        setEffect(dropShadow);

        fichasPuntaje.setEffect(dropShadow);
        multiplicadorPuntaje.setEffect(dropShadow);
    }
}
