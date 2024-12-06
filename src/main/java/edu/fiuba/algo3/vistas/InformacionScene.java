package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.vistas.general.BotonVista;
import edu.fiuba.algo3.vistas.general.EscenaGeneral;
import edu.fiuba.algo3.vistas.menu.MenuScene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class InformacionScene extends EscenaGeneral {
    BotonVista botonSalir;
    Label creditos;
    VBox vboxMadre;

    public InformacionScene(Stage stage) {
        super(stage);
    }

    protected void crearControladores(Stage stage) {
        botonSalir.setOnMouseClicked(e -> stage.setScene(new MenuScene(stage).getScene()));
    }

    protected Pane crearLayout() {
        AnchorPane root = new AnchorPane();

        creditos = new Label("test");
        vboxMadre = new VBox(creditos);

        botonSalir = new BotonVista("X");

        root.getChildren().addAll(vboxMadre, botonSalir);

        return root;
    }

    protected void crearEstilos() {
        AnchorPane.setTopAnchor(botonSalir, 10.0);
        AnchorPane.setRightAnchor(botonSalir, 10.0);

        botonSalir.setStyle("-fx-background-color: #4aba91; -fx-font-size: 16; -fx-padding: 10;");

        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.BLACK);
        shadow.setRadius(5);
        root.setEffect(shadow);
    }
}

