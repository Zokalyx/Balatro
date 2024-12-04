package edu.fiuba.algo3.vistas;


import edu.fiuba.algo3.controllers.ControladorDescarte;
import edu.fiuba.algo3.controllers.ControladorJugar;
import edu.fiuba.algo3.controllers.ControladorRepartir;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.contenedores.Comodines;
import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.contenedores.Tarots;
import edu.fiuba.algo3.modelo.contenedores.Tienda;
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

    Label turnosDisponibles;
    Button botonDescarte;
    Button botonRepartir;
    Button botonJugar;
    Scene scene;
    Label descartesDisponibles;
    Juego juego;
    Mano mano;
    Comodines comodines;
    Puntaje puntaje;
    TiendaVista tiendaVista;
    Tienda tienda;

    public JuegoScene(App app) {
        AnchorPane root = new AnchorPane();

        cargarFuenteDeTexto(root);
        cargarFondo(root);

        // Objetos de modelo
        LectorJson lectorJson = new LectorJson();
        ConfiguracionJuego configuracion = lectorJson.leerConfiguracion();
        JugadaManager jugadaManager = new JugadaManager();
        this.mano = new Mano(configuracion.getMazo(), jugadaManager);
        this.juego = new Juego(lectorJson.leerConfiguracion());
        this.comodines = new Comodines();
        this.puntaje = new Puntaje(0,1);
        Tarots tarots = new Tarots();
        tienda = new Tienda();
        tienda.abrir(configuracion.getComodines(juego.getRondaActual()), configuracion.getTarots(juego.getRondaActual()), configuracion.getPokers(juego.getRondaActual()));

        // Objetos de layout
        tiendaVista = new TiendaVista(tienda, mano, comodines, tarots, juego);
        JugadaVista jugadaVista = new JugadaVista(mano, tienda, juego);
        FinDePartidaVista finDePartidaVista = new FinDePartidaVista(juego);

        Label labelJugar = new Label("Jugar");
        turnosDisponibles = new Label("(" + juego.getTurnosDisponibles() + ")");
        VBox contenidoBotonJugar = new VBox(labelJugar, turnosDisponibles);
        botonJugar = new Button();
        botonJugar.setGraphic(contenidoBotonJugar);

        Region espacioEntreBotonesIzquierda = new Region();

        ManoVista manoVista = new ManoVista(mano);

        Region espacioEntreBotonesDerecha = new Region();

        Label labelDescarte = new Label("Descartar");
        descartesDisponibles = new Label("(" + juego.getDescartesDisponibles() + ")");
        VBox contenidoBotonDescarte = new VBox(labelDescarte, descartesDisponibles);
        botonDescarte = new Button();
        botonDescarte.setGraphic(contenidoBotonDescarte);
        botonRepartir = new Button("Repartir");

        HBox panelInferior = new HBox(botonJugar, espacioEntreBotonesIzquierda, manoVista, espacioEntreBotonesDerecha, botonDescarte, botonRepartir);

        PanelPuntajeVista panelPuntajeVista = new PanelPuntajeVista(juego,mano,puntaje,jugadaManager);
        Region espacioCentralVerticalSuperior = new Region();
        Region espacioCentralVerticalInferior = new Region();
        VBox contenedorCentral = new VBox(panelPuntajeVista, espacioCentralVerticalSuperior, tiendaVista, jugadaVista, finDePartidaVista, espacioCentralVerticalInferior, panelInferior);

        ComodinesVista comodinesVista = new ComodinesVista(comodines);
        Region espacioDespuesComodines = new Region();
        Region espacioAntesTarots = new Region();
        TarotsVista tarotsVista = new TarotsVista(tarots, mano, jugadaManager);
        HBox hboxMadre = new HBox(comodinesVista, espacioDespuesComodines, contenedorCentral, espacioAntesTarots, tarotsVista);

        Button botonSalir = new Button("X");

        root.getChildren().addAll(hboxMadre, botonSalir);

        // Estilo y posicionamiento
        contenidoBotonDescarte.setAlignment(Pos.CENTER);
        contenidoBotonJugar.setAlignment(Pos.CENTER);
        contenidoBotonDescarte.setSpacing(10);
        contenidoBotonJugar.setSpacing(10);

        actualizarBotonDescarte();
        actualizarBotonRepartir();
        botonRepartir.setStyle("-fx-background-color: #4aba91; -fx-font-size: 16; -fx-padding: 10;");
        botonJugar.setStyle("-fx-background-color: #4aba91; -fx-font-size: 16; -fx-padding: 10;");
        botonDescarte.setStyle("-fx-background-color: #4aba91; -fx-font-size: 16; -fx-padding: 10;");
        botonSalir.setStyle("-fx-background-color: #4aba91; -fx-font-size: 16; -fx-padding: 10;");
        AnchorPane.setTopAnchor(botonSalir, 10.0);
        AnchorPane.setRightAnchor(botonSalir, 10.0);

        panelInferior.setAlignment(Pos.CENTER);
        panelInferior.setSpacing(40);

        contenedorCentral.prefHeightProperty().bind(root.heightProperty());
        contenedorCentral.setMinWidth(600);
        contenedorCentral.setAlignment(Pos.CENTER);

        VBox.setVgrow(espacioCentralVerticalSuperior, Priority.ALWAYS);
        VBox.setVgrow(espacioCentralVerticalInferior, Priority.ALWAYS);
        HBox.setHgrow(espacioEntreBotonesIzquierda, Priority.ALWAYS);
        HBox.setHgrow(espacioEntreBotonesDerecha, Priority.ALWAYS);
        HBox.setHgrow(espacioDespuesComodines, Priority.ALWAYS);
        HBox.setHgrow(espacioAntesTarots, Priority.ALWAYS);

        hboxMadre.prefWidthProperty().bind(root.widthProperty());

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);
        dropShadow.setRadius(5);

        botonRepartir.setEffect(dropShadow);
        botonJugar.setEffect(dropShadow);
        botonDescarte.setEffect(dropShadow);
        botonSalir.setEffect(dropShadow);

        // Controladores
        botonSalir.setOnAction(e -> app.volverAMenu());
        botonJugar.setDisable(true);
        botonDescarte.setDisable(true);

        botonRepartir.setOnMouseClicked(new ControladorRepartir(mano));
        botonDescarte.setOnMouseClicked(new ControladorDescarte(mano,juego));
        botonJugar.setOnMouseClicked(new ControladorJugar(mano,juego,comodines,puntaje, tienda, configuracion));

        mano.addObserver(this);
        juego.addObserver(this);
        tienda.addObserver(this);

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

            actualizarBotonRepartir();
            actualizarBotonJuego();
            actualizarBotonDescarte();
        }
        else if (o instanceof Juego){
            Juego juego = (Juego) o;
            actualizarBotonDescarte();
        }
        else if (o instanceof Tienda) {
            Tienda tienda = (Tienda) o;

            actualizarBotonRepartir();
        }
    }

    private void actualizarBotonRepartir() {
        if (mano.estaLlena()) {
            botonRepartir.setManaged(false);
            botonRepartir.setVisible(false);
        } else {
            botonRepartir.setManaged(true);
            botonRepartir.setVisible(true);
            botonRepartir.setDisable(tienda.getEstado());
        }
    }

    private void actualizarBotonDescarte() {

        if (!mano.estaLlena()) {
            botonDescarte.setManaged(false);
            botonDescarte.setVisible(false);
            return;
        }

        botonDescarte.setManaged(true);
        botonDescarte.setVisible(true);

        ArrayList<Poker> seleccionadas = mano.getSeleccion();

        if(juego.getDescartesDisponibles()!=0 && !seleccionadas.isEmpty()){
            botonDescarte.setDisable(false);
        }else{
            botonDescarte.setDisable(true);
        }
        descartesDisponibles.setText("(" + juego.getDescartesDisponibles() + ")");
    }

    private void actualizarBotonJuego() {

        ArrayList<Poker> seleccionadas = mano.getSeleccion();

        if(juego.getTurnosDisponibles()!=0 && !seleccionadas.isEmpty() && mano.estaLlena()){
            botonJugar.setDisable(false);
        }else{
            botonJugar.setDisable(true);
        }
        turnosDisponibles.setText("(" + juego.getTurnosDisponibles() + ")");
    }

    public Scene getScene() {
        return scene;
    }
}

