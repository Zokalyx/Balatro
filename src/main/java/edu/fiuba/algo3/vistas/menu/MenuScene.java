package edu.fiuba.algo3.vistas.menu;

import edu.fiuba.algo3.vistas.general.BotonVista;
import edu.fiuba.algo3.vistas.general.ControlVolumenVista;
import edu.fiuba.algo3.vistas.InformacionScene;
import edu.fiuba.algo3.vistas.general.EscenaGeneral;
import edu.fiuba.algo3.vistas.juego.JuegoScene;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MenuScene extends EscenaGeneral {
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

    public MenuScene(Stage stage) {
        super(stage);
    }

    protected void crearControladores(Stage stage) {
        btnJugar.setOnMouseClicked(e -> stage.setScene(new JuegoScene(stage).getScene()));
        btnSalir.setOnMouseClicked(e -> stage.close());
        btnReglas.setOnMouseClicked(e -> stage.setScene(new InformacionScene(stage).getScene()));
    }

    protected Pane crearLayout() {
        root = new AnchorPane();

        controlVolumen = new ControlVolumenVista();

        titulo = new TituloVista("BALATRO");

        btnReglas = new BotonVista("Info");
        btnJugar = new BotonVista("Jugar");
        btnSalir = new BotonVista("Salir");
        contenedorBotones = new HBox(btnReglas, btnJugar, btnSalir);

        columnaCentral = new VBox(titulo, contenedorBotones);

        margenIzquierda = new Region();
        margenDerecha = new Region();
        hboxMadre = new HBox(margenIzquierda, columnaCentral, margenDerecha);

        vboxMadre = new VBox(hboxMadre, new CartasMenuVista());

        root.getChildren().addAll(vboxMadre, controlVolumen);

        return root;
    }

    protected void crearEstilos() {
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
    }
}

