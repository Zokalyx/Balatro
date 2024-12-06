package edu.fiuba.algo3.vistas.menu;

import edu.fiuba.algo3.vistas.App;
import edu.fiuba.algo3.vistas.general.BotonVista;
import edu.fiuba.algo3.vistas.general.ControlVolumenVista;
import edu.fiuba.algo3.vistas.general.SonidoManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MenuScene {
    Scene scene;
    Pane root;
    HBox titulo;
    Button btnSalir;
    Button btnJugar;
    Button btnReglas;
    HBox contenedorBotones;
    VBox columnaCentral;
    Region margenIzquierda;
    Region margenDerecha;
    HBox hboxMadre;
    VBox vboxMadre;
    ControlVolumenVista controlVolumen;

    public MenuScene(App app) {
        crearLayout();
        crearControladores(app);
        crearEstilo();
    }

    private void crearControladores(App app) {
        btnJugar.setOnMouseClicked(e -> app.iniciarJuego());
        btnSalir.setOnMouseClicked(e -> app.cerrarVentana());
    }

    private void crearLayout() {
        root = new AnchorPane();

        controlVolumen = new ControlVolumenVista();

        titulo = new TituloVista("BALATRO");

        btnReglas = new BotonVista("Reglas");
        btnJugar = new BotonVista("Jugar");
        btnSalir = new BotonVista("Salir");
        contenedorBotones = new HBox(btnReglas, btnJugar, btnSalir);

        columnaCentral = new VBox(titulo, contenedorBotones);

        margenIzquierda = new Region();
        margenDerecha = new Region();
        hboxMadre = new HBox(margenIzquierda, columnaCentral, margenDerecha);

        vboxMadre = new VBox(hboxMadre, new CartasMenuVista());

        root.getChildren().addAll(vboxMadre, controlVolumen);

        scene = new Scene(root);
    }

    private void crearEstilo() {
        AnchorPane.setTopAnchor(controlVolumen, 20.0);
        AnchorPane.setLeftAnchor(controlVolumen, 20.0);

        contenedorBotones.setAlignment(Pos.CENTER);
        contenedorBotones.setSpacing(50);

        columnaCentral.setAlignment(Pos.CENTER);
        columnaCentral.getChildren().addAll();
        columnaCentral.prefHeightProperty().bind(root.heightProperty());
        columnaCentral.setMaxWidth(600);
        columnaCentral.setSpacing(100);

        hboxMadre.prefWidthProperty().bind(root.widthProperty());
        vboxMadre.prefHeightProperty().bind(root.heightProperty());

        HBox.setHgrow(margenIzquierda, Priority.ALWAYS);
        HBox.setHgrow(margenDerecha, Priority.ALWAYS);

        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.BLACK);
        shadow.setRadius(5);
        root.setEffect(shadow);

        cargarFuenteDeTexto(root);
        cargarFondo(root);
    }

    private void cargarFuenteDeTexto(Pane root) {
        Font.loadFont(getClass().getResourceAsStream("/PressStart2P-Regular.ttf"), 20);
        root.setStyle("-fx-font-family: 'Press Start 2P';");
    }

    private static void cargarFondo(Pane root) {
        try {
            Image image = new Image(new FileInputStream("src/main/resources/freepik_poker_table_background.jpg"));
            BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            root.setBackground(new Background(backgroundImage));
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el imagen");
        }
    }

    public Scene getScene() {
        return scene;
    }
}

