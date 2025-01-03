package edu.fiuba.algo3.vistas.general;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class EscenaGeneral {
    Scene scene;
    protected Pane root;

    public EscenaGeneral(Stage stage) {
        root = crearLayout();
        crearControladores(stage);
        crearEstilos();
        cargarFuenteDeTexto(root);
        cargarImagenDeFondo(root);

        // https://stackoverflow.com/a/44852856
        Scene escenaVieja = stage.getScene();
        if (escenaVieja != null) {
            scene = new Scene(root, stage.getScene() .getWidth(), stage.getScene().getHeight());
        } else {
            scene = new Scene(root);
        }
    }

    protected abstract void crearEstilos();

    protected abstract void crearControladores(Stage stage);

    protected abstract Pane crearLayout();

    private void cargarFuenteDeTexto(Pane root) {
        Font.loadFont(getClass().getResourceAsStream("/fuentes/PressStart2P-Regular.ttf"), 20);
        root.setStyle("-fx-font-family: 'Press Start 2P';");
    }

    private void cargarImagenDeFondo(Pane root) {
        Image image = new Image(getClass().getResource("/images/freepik_poker_table_background.jpg").toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        root.setBackground(new Background(backgroundImage));
    }

    public Scene getScene() {
        return scene;
    }
}
