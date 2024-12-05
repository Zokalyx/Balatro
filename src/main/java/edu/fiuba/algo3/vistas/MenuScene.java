package edu.fiuba.algo3.vistas;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.media.MediaPlayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MenuScene {
    Scene scene;

    public MenuScene(App app) {
        Pane root = new Pane();

        Font.loadFont(getClass().getResourceAsStream("/PressStart2P-Regular.ttf"), 20);
        root.setStyle("-fx-font-family: 'Press Start 2P';");

        try {
            Image image = new Image(new FileInputStream("src/main/resources/freepik_poker_table_background.jpg"));
            BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            root.setBackground(new Background(backgroundImage));
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el imagen");
        }

        HBox titulo = new HBox();
        titulo.setAlignment(Pos.CENTER);
        for (int i = 0; i < "BALATRO".length(); i++) {
            char letra = "BALATRO".toCharArray()[i];
            Text label = new Text(Character.toString(letra));
            label.setStroke(Color.BLACK);
            label.setFill(Color.AQUA);
            label.setStrokeWidth(2);
            label.setStyle("-fx-font-size: 80;");
            titulo.getChildren().add(label);

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

        Button btnReglas = new BotonVista("Reglas");
        Button btnJugar = new BotonVista("Jugar");
        Button btnSalir = new BotonVista("Salir");

        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(btnReglas, btnJugar, btnSalir);
        buttonBox.setSpacing(50);

        btnJugar.setOnMouseClicked(e -> app.iniciarJuego());
        btnSalir.setOnMouseClicked(e -> app.cerrarVentana());

        Region margenIzquierda = new Region();
        Region margenDerecha = new Region();
        HBox.setHgrow(margenIzquierda, Priority.ALWAYS);
        HBox.setHgrow(margenDerecha, Priority.ALWAYS);

        VBox columnaCentral = new VBox();
        columnaCentral.setAlignment(Pos.CENTER);
        columnaCentral.getChildren().addAll(titulo, buttonBox);
        columnaCentral.prefHeightProperty().bind(root.heightProperty());
        columnaCentral.setMaxWidth(600);
        columnaCentral.setSpacing(100);

        HBox hboxMadre = new HBox(margenIzquierda, columnaCentral, margenDerecha);
        hboxMadre.prefWidthProperty().bind(root.widthProperty());

        VBox vboxMadre = new VBox(hboxMadre, new CartasMenuVista());
        vboxMadre.prefHeightProperty().bind(root.heightProperty());

        root.getChildren().addAll(vboxMadre);

        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.BLACK);
        shadow.setRadius(5);
        root.setEffect(shadow);
        btnJugar.setEffect(shadow);
        btnSalir.setEffect(shadow);
        btnReglas.setEffect(shadow);
        titulo.setEffect(shadow);

        scene = new Scene(root);
    }

    public Scene getScene() {
        return scene;
    }
}

