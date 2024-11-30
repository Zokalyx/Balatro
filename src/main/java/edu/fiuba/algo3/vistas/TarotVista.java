package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.tarot.Tarot;
import javafx.scene.control.Label;

public class TarotVista extends CartaVista {
    public TarotVista(Tarot tarot) {
        Label label = new Label("Tarot");

        getChildren().addAll(label);
    }
}
