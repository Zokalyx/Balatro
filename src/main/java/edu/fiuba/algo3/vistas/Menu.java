package edu.fiuba.algo3.vistas;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Menu extends Application {
    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();

        Text title = new Text("BALATRO");
        title.setFont(Font.font("Arial Black", 36));
        title.setFill(Color.BLACK);
        title.setX(300);
        title.setY(100);

        Button btnReglas = new Button("Reglas");
        btnReglas.setLayoutX(150);
        btnReglas.setLayoutY(200);
        btnReglas.setStyle("-fx-background-color: #E0E0E0; -fx-font-size: 16;");

        Button btnJugar = new Button("Jugar");
        btnJugar.setLayoutX(150);
        btnJugar.setLayoutY(250);
        btnJugar.setStyle("-fx-background-color: #E0E0E0; -fx-font-size: 16;");

        Button btnSalir = new Button("Salir");
        btnSalir.setLayoutX(150);
        btnSalir.setLayoutY(300);
        btnSalir.setStyle("-fx-background-color: #E0E0E0; -fx-font-size: 16;");

        HBox buttonBox = new HBox(50);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setLayoutX(240);
        buttonBox.setLayoutY(200);
        buttonBox.getChildren().addAll(btnReglas, btnJugar, btnSalir);

        btnReglas.setOnAction(e -> System.out.println("Mostrar las reglas del juego."));
        btnJugar.setOnAction(e -> System.out.println("Iniciar el juego."));
        btnSalir.setOnAction(e -> primaryStage.close());

        root.getChildren().addAll(title, buttonBox);

        Scene scene = new Scene(root, 800, 400);
        primaryStage.setTitle("Balatro - Menú Principal");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

