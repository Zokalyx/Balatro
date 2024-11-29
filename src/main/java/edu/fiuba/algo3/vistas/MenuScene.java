package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.palo.Trebol;
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

public class MenuScene extends Application {
    @Override
    public void start(Stage stage) {
        HBox root = new HBox();

        try {
            Image image = new Image(new FileInputStream("src/main/resources/freepik_poker_table_background.jpg"));
            BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            root.setBackground(new Background(backgroundImage));
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el imagen");
        }

        Text title = new Text("BALATRO");
        title.setFont(Font.font("Arial Black", 36));
        title.setFill(Color.BLACK);

        Button btnReglas = new Button("Reglas");
        btnReglas.setStyle("-fx-background-color: #E0E0E0; -fx-font-size: 16;");

        Button btnJugar = new Button("Jugar");
        btnJugar.setStyle("-fx-background-color: #E0E0E0; -fx-font-size: 16;");

        Button btnSalir = new Button("Salir");
        btnSalir.setStyle("-fx-background-color: #E0E0E0; -fx-font-size: 16;");

        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(btnReglas, btnJugar, btnSalir);
        buttonBox.setSpacing(50);

        btnReglas.setOnAction(e -> System.out.println("Mostrar las reglas del juego."));
        btnJugar.setOnAction(e -> System.out.println("Iniciar el juego."));
        btnSalir.setOnAction(e -> stage.close());

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

        root.getChildren().addAll(margenIzquierda, columnaCentral, margenDerecha);

        Scene scene = new Scene(root, 800, 400);
        stage.setTitle("Balatro - Menú Principal");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

