package edu.fiuba.algo3.vistas;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public abstract class CartaVista extends StackPane {

    int offsetX;
    int offsetY;
    TranslateTransition transicion;
    private boolean seleccionado;

    public CartaVista() {
        setStyle("-fx-background-color: #fff; -fx-background-radius: 12; -fx-padding: 10; -fx-font-size: 10");
        setMinSize(120, 180);
        setMaxSize(120, 180);
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.BLACK);
        shadow.setRadius(5);
        setEffect(shadow);
        this.transicion = new TranslateTransition(Duration.millis(100), this);
        transicion.setInterpolator(Interpolator.EASE_OUT);
        this.seleccionado=false;

        this.setOnMouseEntered(event -> {
            if(!seleccionado){
                resaltar();
            }
        });
        this.setOnMouseExited(event -> {
            if(!seleccionado){
                desresaltar();
            }
        });

    }

    public void setOffset(int offsetX, int offsetY) {
        this.offsetY = offsetY;
        this.offsetX = offsetX;
    }

    public void resaltar(){

        transicion.setToX(-offsetX);
        transicion.setToY(-offsetY);
        transicion.stop();
        transicion.play();
    }

    public void desresaltar(){

        transicion.setToX(0);
        transicion.setToY(0);
        transicion.stop();
        transicion.play();
    }

    public void setSeleccionado(boolean seleccionado){
        this.seleccionado=seleccionado;
    }
}