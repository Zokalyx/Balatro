package edu.fiuba.algo3.vistas.juego;


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
import edu.fiuba.algo3.vistas.App;
import edu.fiuba.algo3.vistas.general.BotonVista;

import edu.fiuba.algo3.vistas.general.EscenaGeneral;
import edu.fiuba.algo3.vistas.general.SonidoManager;
import edu.fiuba.algo3.vistas.menu.MenuScene;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.PopupControl;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class JuegoScene extends EscenaGeneral implements Observer {
    ConfiguracionJuego configuracion;
    VBox contenidoBotonJugar;
    VBox contenidoBotonDescarte;
    Label turnosDisponibles;
    BotonVista botonDescarte;
    BotonVista botonRepartir;
    BotonVista botonJugar;
    Label descartesDisponibles;
    Juego juego;
    Mano mano;
    Comodines comodines;
    Puntaje puntaje;
    TiendaVista tiendaVista;
    Tienda tienda;
    boolean botonesBloqueados;
    BotonVista botonSalir;
    HBox hboxMadre;
    HBox panelInferior;
    VBox contenedorCentral;
    Region espacioCentralVerticalSuperior;
    Region espacioCentralVerticalInferior;
    Region espacioEntreBotonesIzquierda;
    Region espacioEntreBotonesDerecha;
    Region espacioDespuesComodines;
    Region espacioAntesTarots;

    public JuegoScene(Stage stage) {
        super(stage);
    }

    protected Pane crearLayout() {
        AnchorPane root = new AnchorPane();

        // Objetos de modelo
        LectorJson lectorJson = new LectorJson();
        configuracion = lectorJson.leerConfiguracion();
        JugadaManager jugadaManager = new JugadaManager();
        this.mano = new Mano(configuracion.getMazo(), jugadaManager);
        this.juego = new Juego(lectorJson.leerConfiguracion());
        this.comodines = new Comodines();
        this.puntaje = new Puntaje(0, 1);
        Tarots tarots = new Tarots();
        tienda = new Tienda();
        tienda.abrir(configuracion.getComodines(juego.getRondaActual()), configuracion.getTarots(juego.getRondaActual()), configuracion.getPokers(juego.getRondaActual()));

        // Objetos de layout
        tiendaVista = new TiendaVista(tienda, mano, comodines, tarots, juego);
        JugadaVista jugadaVista = new JugadaVista(mano, tienda, juego);
        FinDePartidaVista finDePartidaVista = new FinDePartidaVista(juego);

        Label labelJugar = new Label("Jugar");
        turnosDisponibles = new Label("(" + juego.getTurnosDisponibles() + ")");
        contenidoBotonJugar = new VBox(labelJugar, turnosDisponibles);
        botonJugar = new BotonVista();
        botonJugar.setGraphic(contenidoBotonJugar);

        espacioEntreBotonesIzquierda = new Region();

        ManoVista manoVista = new ManoVista(mano);

        espacioEntreBotonesDerecha = new Region();

        Label labelDescarte = new Label("Descartar");
        descartesDisponibles = new Label("(" + juego.getDescartesDisponibles() + ")");
        contenidoBotonDescarte = new VBox(labelDescarte, descartesDisponibles);
        botonDescarte = new BotonVista();
        botonDescarte.setGraphic(contenidoBotonDescarte);
        botonRepartir = new BotonVista("Repartir");

        panelInferior = new HBox(botonJugar, espacioEntreBotonesIzquierda, manoVista, espacioEntreBotonesDerecha, botonDescarte, botonRepartir);

        PanelPuntajeVista panelPuntajeVista = new PanelPuntajeVista(juego, mano, puntaje, jugadaManager);
        espacioCentralVerticalSuperior = new Region();
        espacioCentralVerticalInferior = new Region();
        contenedorCentral = new VBox(panelPuntajeVista, espacioCentralVerticalSuperior, tiendaVista, jugadaVista, finDePartidaVista, espacioCentralVerticalInferior, panelInferior);

        ComodinesVista comodinesVista = new ComodinesVista(comodines);
        espacioDespuesComodines = new Region();
        espacioAntesTarots = new Region();
        TarotsVista tarotsVista = new TarotsVista(tarots, mano, jugadaManager);
        hboxMadre = new HBox(comodinesVista, espacioDespuesComodines, contenedorCentral, espacioAntesTarots, tarotsVista);

        botonSalir = new BotonVista("X");

        root.getChildren().addAll(hboxMadre, botonSalir);

        return root;
    }

    protected void crearEstilos() {
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
    }

    protected void crearControladores(Stage stage) {
        botonSalir.setOnMouseClicked(e -> stage.setScene(new MenuScene(stage).getScene()));
        botonJugar.setDisable(true);
        botonDescarte.setDisable(true);

        botonRepartir.setOnMouseClicked(new ControladorRepartir(mano));
        botonDescarte.setOnMouseClicked(new ControladorDescarte(mano, juego));
        botonJugar.setOnMouseClicked(new ControladorJugar(mano,juego,comodines,puntaje, tienda, configuracion, this));

        mano.addObserver(this);
        juego.addObserver(this);
        tienda.addObserver(this);
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
            actualizarBotonRepartir();
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
            botonRepartir.setDisable(tienda.getEstado() || juego.perdio() || juego.gano() || botonesBloqueados);
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

        if(juego.getDescartesDisponibles()!=0 && !seleccionadas.isEmpty() && !botonesBloqueados){
            botonDescarte.setDisable(false);
        }else{
            botonDescarte.setDisable(true);
        }
        descartesDisponibles.setText("(" + juego.getDescartesDisponibles() + ")");
    }

    private void actualizarBotonJuego() {
        if (juego.getTurnosDisponibles() !=0 && mano.estaLlena() && !botonesBloqueados){
            botonJugar.setDisable(false);
        }else{
            botonJugar.setDisable(true);
        }
        turnosDisponibles.setText("(" + juego.getTurnosDisponibles() + ")");
    }

    public void setEstadoBotones(boolean estado) {
        botonesBloqueados = !estado;
        actualizarBotonRepartir();
        actualizarBotonJuego();
        actualizarBotonDescarte();
    }
}

