package edu.fiuba.algo3.vistas.menu;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class TituloVista extends HBox {
    public TituloVista(String titulo) {
        // Vistas
        char[] letras = titulo.toCharArray();
        for (int i = 0; i < letras.length; i++) {
            char letra = letras[i];
            Text label = new Text(Character.toString(letra));
            label.setStroke(Color.BLACK);
            label.setFill(Color.AQUA);
            label.setStrokeWidth(2);
            label.setStyle("-fx-font-size: 80;");
            getChildren().add(label);

            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(label.translateYProperty(), 0, Interpolator.EASE_BOTH)),
                    new KeyFrame(Duration.seconds(1), new KeyValue(label.translateYProperty(), -20, Interpolator.EASE_BOTH)),
                    new KeyFrame(Duration.seconds(2), new KeyValue(label.translateYProperty(), 0, Interpolator.EASE_BOTH))
            );
            timeline.setDelay(Duration.seconds((double) i / 12));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.setAutoReverse(true);
            timeline.play();
        }

        // Estilo y posicionamiento
        setAlignment(Pos.CENTER);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);
        dropShadow.setRadius(10);
        setEffect(dropShadow);
    }

}
