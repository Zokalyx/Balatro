package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.vistas.general.SonidoManager;
import edu.fiuba.algo3.vistas.menu.MenuScene;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
    Stage stage;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setScene(new MenuScene(stage).getScene());
        stage.setTitle("Balatro");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/balatro.png")));
        stage.setMaximized(true);
        stage.show();

        SonidoManager.getInstancia().play("musica");
    }
}
