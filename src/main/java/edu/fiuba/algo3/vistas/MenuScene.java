package edu.fiuba.algo3.vistas;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

        Text title = new Text("BALATRO");
        title.setStyle("-fx-font-size: 60;");
        title.setStroke(Color.BLACK);
        title.setStrokeWidth(2);
        title.setFill(Color.GOLD);

        Button btnReglas = new Button("Reglas");
        btnReglas.setStyle("-fx-background-color: #4aba91; -fx-font-size: 16; -fx-padding: 10;");

        Button btnJugar = new Button("Jugar");
        btnJugar.setStyle("-fx-background-color: #4aba91; -fx-font-size: 16; -fx-padding: 10;");

        Button btnSalir = new Button("Salir");
        btnSalir.setStyle("-fx-background-color: #4aba91; -fx-font-size: 16; -fx-padding: 10;");

        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(btnReglas, btnJugar, btnSalir);
        buttonBox.setSpacing(50);

        btnReglas.setOnAction(e -> System.out.println("Mostrar las reglas del juego."));
        btnJugar.setOnAction(e -> app.iniciarJuego());
        btnSalir.setOnAction(e -> app.cerrarVentana());

        Region margenIzquierda = new Region();
        Region margenDerecha = new Region();
        HBox.setHgrow(margenIzquierda, Priority.ALWAYS);
        HBox.setHgrow(margenDerecha, Priority.ALWAYS);

        VBox columnaCentral = new VBox();
        columnaCentral.setAlignment(Pos.CENTER);
        columnaCentral.getChildren().addAll(title, buttonBox);
        columnaCentral.prefHeightProperty().bind(root.heightProperty());
        columnaCentral.setMaxWidth(600);
        columnaCentral.setSpacing(100);

        HBox hboxMadre = new HBox(margenIzquierda, columnaCentral, margenDerecha);
        hboxMadre.prefWidthProperty().bind(root.widthProperty());

        VBox vboxMadre = new VBox(hboxMadre, new CartasMenuVista());
        vboxMadre.prefHeightProperty().bind(root.heightProperty());

        root.getChildren().addAll(vboxMadre);

        scene = new Scene(root);
    }

    public Scene getScene() {
        return scene;
    }
}

