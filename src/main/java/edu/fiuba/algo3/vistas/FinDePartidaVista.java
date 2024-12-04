package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.juego.Juego;
import javafx.animation.*;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.Observable;
import java.util.Observer;

public class FinDePartidaVista extends StackPane implements Observer {
    Label resultado;
    ScaleTransition transicionEscala;

    public FinDePartidaVista(Juego juego) {
        resultado = new Label();
        VBox contenedor = new VBox();

        StackPane.setAlignment(resultado, Pos.CENTER);
        StackPane.setAlignment(contenedor, Pos.CENTER);

        getChildren().addAll(contenedor, resultado);

        transicionEscala = new ScaleTransition(Duration.millis(300), this);
        transicionEscala.setInterpolator(Interpolator.EASE_OUT);

        actualizar(juego);

        contenedor.setStyle("-fx-min-width: 300; -fx-min-height: 300; -fx-max-width: 300; -fx-max-height: 300; -fx-background-color: #2c2c2c; -fx-background-radius: 20;");

        setAlignment(Pos.CENTER);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(10);
        dropShadow.setColor(Color.BLACK);
        contenedor.setEffect(dropShadow);

        resultado.setStyle("-fx-font-size: 30;");

        juego.addObserver(this);

        Rotate rotacion = new Rotate();
        rotacion.setPivotX(150);
        rotacion.setPivotY(150);

        contenedor.getTransforms().add(rotacion);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rotacion.angleProperty(), 0, Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(3), new KeyValue(rotacion.angleProperty(), 180, Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(6), new KeyValue(rotacion.angleProperty(), 360, Interpolator.LINEAR))
            );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Juego) {
            Juego juego = (Juego) o;
            actualizar(juego);
        }
    }

    private void actualizar(Juego juego) {
        boolean habilitado = juego.gano() || juego.perdio();

        setVisible(habilitado);
        setManaged(habilitado);

        if (habilitado) {
            agrandar();
        }

        if (juego.gano()) {
            resultado.setText("Ganaste!");
            resultado.setTextFill(Color.AQUA);
        } else if (juego.perdio()) {
            resultado.setText("Perdiste!");
            resultado.setTextFill(Color.RED);
        }
    }

    public void agrandar(){
        setScaleX(0);
        setScaleY(0);
        transicionEscala.setToX(1);
        transicionEscala.setToY(1);
        transicionEscala.stop();
        transicionEscala.play();
    }
}
