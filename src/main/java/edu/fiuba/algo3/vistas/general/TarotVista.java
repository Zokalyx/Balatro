package edu.fiuba.algo3.vistas.general;

import edu.fiuba.algo3.modelo.tarot.Tarot;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TarotVista extends CartaVista {
    Tarot tarot;

    public TarotVista(Tarot tarot) {
        this.tarot = tarot;

        offsetX=20;
        offsetY=40;

        Label titulo = new Label(tarot.getNombre());
        Label descripcion = new Label(tarot.getDescripcion());
        VBox vbox = new VBox(titulo, descripcion);
        vbox.setSpacing(5);

        descripcion.setStyle("-fx-font-size: 8;");
        titulo.setWrapText(true);
        descripcion.setWrapText(true);


        getChildren().addAll(vbox);
    }

    public boolean esTarotVista(Tarot tarot) {
        return this.tarot.equals(tarot);
    }
}
