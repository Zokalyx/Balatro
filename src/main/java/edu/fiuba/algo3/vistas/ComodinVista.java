package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.comodin.Comodin;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ComodinVista extends CartaVista {
    public ComodinVista(Comodin comodin) {
        agregarAnimacion(-20, 40);

        Label titulo = new Label(comodin.getNombre());
        Label descripcion = new Label(comodin.getDescripcion());
        VBox vbox = new VBox(titulo, descripcion);
        vbox.setSpacing(5);

        descripcion.setStyle("-fx-font-size: 8;");
        titulo.setWrapText(true);
        descripcion.setWrapText(true);


        getChildren().addAll(vbox);
    }
}
