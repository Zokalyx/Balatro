package edu.fiuba.algo3.vistas;


import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.juego.ConfiguracionJuego;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugada.JugadaManager;
import javafx.application.Application;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class JuegoScene implements Observer {
    Button botonDescarte;
    Button botonJugar;
    Scene scene;


    public JuegoScene(App app) {
        AnchorPane root = new AnchorPane();

        cargarFuenteDeTexto(root);
        cargarFondo(root);

        // Objetos de modelo
        LectorJson lectorJson = new LectorJson();
        ConfiguracionJuego configuracion = lectorJson.leerConfiguracion();
        Mano mano = new Mano(configuracion.getMazo(), new JugadaManager());
        mano.repartir();
        Juego juego = new Juego(lectorJson.leerConfiguracion());

        // Objetos de layout
        Label labelJugar = new Label("Jugar");
        Label turnosDisponibles = new Label("(" + juego.getTurnosDisponibles() + ")");
        VBox contenidoBotonJugar = new VBox(labelJugar, turnosDisponibles);
        botonJugar = new Button();
        botonJugar.setGraphic(contenidoBotonJugar);

        Region espacioEntreBotonesIzquierda = new Region();

        ManoVista manoVista = new ManoVista(mano);

        Region espacioEntreBotonesDerecha = new Region();

        Label labelDescarte = new Label("Descartar");
        Label descartesDisponibles = new Label("(" + juego.getDescartesDisponibles() + ")");
        VBox contenidoBotonDescarte = new VBox(labelDescarte, descartesDisponibles);
        botonDescarte = new Button();
        botonDescarte.setGraphic(contenidoBotonDescarte);

        HBox panelInferior = new HBox(botonJugar, espacioEntreBotonesIzquierda, manoVista, espacioEntreBotonesDerecha, botonDescarte);

        PanelPuntajeVista panelPuntajeVista = new PanelPuntajeVista(juego);
        Region espacioCentralVertical = new Region();
        VBox contenedorCentral = new VBox(panelPuntajeVista, espacioCentralVertical, panelInferior);

        ComodinesVista comodines = new ComodinesVista();
        Region espacioDespuesComodines = new Region();
        Region espacioAntesTarots = new Region();
        TarotsVista tarots = new TarotsVista();
        HBox hboxMadre = new HBox(comodines, espacioDespuesComodines, contenedorCentral, espacioAntesTarots, tarots);

        Button botonSalir = new Button("X");

        root.getChildren().addAll(hboxMadre, botonSalir);

        // Estilo y posicionamiento
        contenidoBotonDescarte.setAlignment(Pos.CENTER);
        contenidoBotonJugar.setAlignment(Pos.CENTER);
        contenidoBotonDescarte.setSpacing(10);
        contenidoBotonJugar.setSpacing(10);

        botonJugar.setStyle("-fx-background-color: #4aba91; -fx-font-size: 16; -fx-padding: 10;");
        botonDescarte.setStyle("-fx-background-color: #4aba91; -fx-font-size: 16; -fx-padding: 10;");
        botonSalir.setStyle("-fx-background-color: #4aba91; -fx-font-size: 16; -fx-padding: 10;");
        AnchorPane.setTopAnchor(botonSalir, 10.0);
        AnchorPane.setRightAnchor(botonSalir, 10.0);

        panelInferior.setAlignment(Pos.CENTER);
        panelInferior.setSpacing(10);

        contenedorCentral.prefHeightProperty().bind(root.heightProperty());
        contenedorCentral.setMinWidth(600);

        VBox.setVgrow(espacioCentralVertical, Priority.ALWAYS);
        HBox.setHgrow(espacioEntreBotonesIzquierda, Priority.ALWAYS);
        HBox.setHgrow(espacioEntreBotonesDerecha, Priority.ALWAYS);
        HBox.setHgrow(espacioDespuesComodines, Priority.ALWAYS);
        HBox.setHgrow(espacioAntesTarots, Priority.ALWAYS);

        hboxMadre.prefWidthProperty().bind(root.widthProperty());

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);
        dropShadow.setRadius(5);

        botonJugar.setEffect(dropShadow);
        botonDescarte.setEffect(dropShadow);
        botonSalir.setEffect(dropShadow);

        // Controladores
        botonSalir.setOnAction(e -> app.volverAMenu());
        botonJugar.setDisable(true);
        botonDescarte.setDisable(true);

        mano.addObserver(this);

        scene = new Scene(root);
    }

    private void cargarFuenteDeTexto(AnchorPane root) {
        Font.loadFont(getClass().getResourceAsStream("/PressStart2P-Regular.ttf"), 20);
        root.setStyle("-fx-font-family: 'Press Start 2P';");
    }

    private static void cargarFondo(AnchorPane root) {
        try {
            Image image = new Image(new FileInputStream("src/main/resources/freepik_poker_table_background.jpg"));
            BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            root.setBackground(new Background(backgroundImage));
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró la imagen");
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Mano) {
            Mano mano = (Mano) o;

            ArrayList<Poker> seleccionadas = mano.getSeleccion();
            boolean deshabilitarBotones = seleccionadas.isEmpty();
            botonDescarte.setDisable(deshabilitarBotones);
            botonJugar.setDisable(deshabilitarBotones);

        }
    }

    public Scene getScene() {
        return scene;
    }
}

