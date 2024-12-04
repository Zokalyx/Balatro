package edu.fiuba.algo3.vistas;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public abstract class CartaVista extends StackPane {

    int offsetX;
    int offsetY;
    TranslateTransition transicionOffset;
    ScaleTransition transicionEscala;
    RotateTransition transicionRotacion;
    private boolean seleccionado;

    public CartaVista() {
        setStyle("-fx-background-color: #fff; -fx-background-radius: 12; -fx-padding: 10; -fx-font-size: 10");
        setMinSize(120, 180);
        setMaxSize(120, 180);
        setScaleX(0);
        setScaleY(0);
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.BLACK);
        shadow.setRadius(5);
        setEffect(shadow);
        this.transicionOffset = new TranslateTransition(Duration.millis(100), this);
        transicionOffset.setInterpolator(Interpolator.EASE_OUT);
        this.transicionEscala = new ScaleTransition(Duration.millis(300), this);
        transicionEscala.setInterpolator(Interpolator.EASE_OUT);
        this.transicionRotacion = new RotateTransition(Duration.millis(100), this);
        transicionRotacion.setInterpolator(Interpolator.EASE_OUT);
        agrandar();
        this.seleccionado=false;
    }

    public void setAnimacionRotacion() {
        this.setOnMouseEntered(e -> {
            transicionRotacion.setToAngle(10);
            transicionRotacion.stop();
            transicionRotacion.play();
        });

        this.setOnMouseExited(e -> {
            transicionRotacion.setToAngle(0);
            transicionRotacion.stop();
            transicionRotacion.play();
        });
    }

    public void setAnimacion(int offsetX, int offsetY) {
        this.offsetY = offsetY;
        this.offsetX = offsetX;
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

    public void resaltar(){

        transicionOffset.setToX(-offsetX);
        transicionOffset.setToY(-offsetY);
        transicionOffset.stop();
        transicionOffset.play();
    }

    public void desresaltar(){

        transicionOffset.setToX(0);
        transicionOffset.setToY(0);
        transicionOffset.stop();
        transicionOffset.play();
    }

    public void setSeleccionado(boolean seleccionado){
        this.seleccionado=seleccionado;
    }

    public void agrandar(){
        transicionEscala.setToX(1);
        transicionEscala.setToY(1);
        transicionEscala.stop();
        transicionEscala.play();
    }

    public void achicar(){
        transicionEscala.setToX(0);
        transicionEscala.setToY(0);
        transicionEscala.stop();
        transicionEscala.play();
    }
}
