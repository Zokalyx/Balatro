package edu.fiuba.algo3.vistas;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public abstract class CartaVista extends StackPane {
    public CartaVista() {
        setStyle("-fx-background-color: #fff; -fx-background-radius: 12; -fx-padding: 10; -fx-font-size: 12");
        setMinSize(100, 160);
        setMaxSize(100, 160);
        agregarAnimacion();
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.BLACK);
        shadow.setRadius(5);
        setEffect(shadow);
    }

    private void agregarAnimacion() {
        TranslateTransition enterTransition = new TranslateTransition(Duration.millis(100), this);
        enterTransition.setToY(-5);
        enterTransition.setInterpolator(Interpolator.EASE_OUT);

        TranslateTransition exitTransition = new TranslateTransition(Duration.millis(100), this);
        exitTransition.setToY(0);
        enterTransition.setInterpolator(Interpolator.EASE_OUT);

        this.setOnMouseEntered(event -> {
            enterTransition.stop();
            enterTransition.play();
        });

        this.setOnMouseExited(event -> {
            exitTransition.stop();
            exitTransition.play();
        });
    }
}