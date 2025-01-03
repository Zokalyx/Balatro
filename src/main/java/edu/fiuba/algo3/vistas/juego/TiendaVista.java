package edu.fiuba.algo3.vistas.juego;

import edu.fiuba.algo3.controllers.ControladorTienda;
import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.contenedores.Comodines;
import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.contenedores.Tienda;
import edu.fiuba.algo3.modelo.contenedores.Tarots;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import edu.fiuba.algo3.vistas.general.*;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class TiendaVista extends StackPane implements Observer {
    double anguloRotacion = 0;
    boolean pausarAnimacion = false;
    VBox textoCentral;
    Mano mano;
    Comodines comodines;
    Tarots tarots;
    ArrayList<CartaVista> vistas;
    Juego juego;
    ScaleTransition transicionEscala;

    public TiendaVista(Tienda tienda, Mano mano, Comodines comodines, Tarots tarots, Juego juego) {
        this.mano = mano;
        this.comodines = comodines;
        this.tarots = tarots;
        this.juego = juego;
        vistas = new ArrayList<>();

        setOnMouseEntered(e -> pausarAnimacion = true);
        setOnMouseExited(e -> pausarAnimacion = false);

        setMinSize(400, 400);
        setMaxSize(400, 400);
        setStyle("-fx-background-color: #2c2c2c; -fx-background-radius: 20;");
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.BLACK);
        shadow.setRadius(20);
        setEffect(shadow);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(16), event -> {
            if (!pausarAnimacion) {
                anguloRotacion += 0.5;
            } else {
                anguloRotacion += 0.1;
            }
            posicionarVistas();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Label tiendaLabel = new Label("Tienda");
        Label instruccionLabel = new Label("Elegir 1");
        tiendaLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20;");
        instruccionLabel.setStyle("-fx-text-fill: white; -fx-font-size: 12;");
        textoCentral = new VBox(tiendaLabel, instruccionLabel);
        textoCentral.setSpacing(10);
        textoCentral.setAlignment(Pos.CENTER);
        StackPane.setAlignment(textoCentral, Pos.CENTER);

        tienda.addObserver(this);

        transicionEscala = new ScaleTransition(Duration.millis(300), this);
        transicionEscala.setInterpolator(Interpolator.EASE_OUT);

        actualizarVista(tienda);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Tienda) {
            Tienda tienda = (Tienda) o;

            actualizarVista(tienda);
        }
    }

    private void actualizarVista(Tienda tienda) {
        vistas = new ArrayList<>();

        for (Comodin comodin : tienda.getComodinesDisponibles()) {
            CartaVista vista = new ComodinVista(comodin);
            vistas.add(vista);
            vista.setOnMouseClicked(new ControladorTienda(comodin, tienda, mano, comodines, tarots));
            vista.setAnimacionRotacion();
        }
        for (Tarot tarot : tienda.getTarotsDisponibles()) {
            CartaVista vista = new TarotVista(tarot);
            vistas.add(vista);
            vista.setOnMouseClicked(new ControladorTienda(tarot, tienda, mano, comodines, tarots));
            vista.setAnimacionRotacion();
        }
        for (Poker poker : tienda.getCartasDisponibles()) {
            CartaVista vista = new PokerVista(poker);
            vistas.add(vista);
            vista.setOnMouseClicked(new ControladorTienda(poker, tienda, mano, comodines, tarots));
            vista.setAnimacionRotacion();
        }

        posicionarVistas();

        getChildren().clear();
        getChildren().add(textoCentral);
        getChildren().addAll(vistas);
        StackPane.setAlignment(textoCentral, Pos.CENTER);

        boolean habilitado = tienda.getEstado() && !juego.gano() && !juego.perdio();

        setVisible(habilitado);
        setManaged(habilitado);

        if (habilitado) {
            agrandar();
        }
    }

    private void posicionarVistas() {
        int n = vistas.size();
        for (int i = 0; i < n; i++) {
            CartaVista vista = vistas.get(i);
            double angulo = (2 * Math.PI * i / n) + Math.toRadians(anguloRotacion);
            vista.setTranslateX(150 * Math.cos(angulo));
            vista.setTranslateY(150 * Math.sin(angulo));
            // Descomentar para efecto raro pero copado
            // vista.setRotate(-Math.toDegrees(angulo));
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
