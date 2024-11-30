package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.comodin.Comodin;
import javafx.scene.control.Label;

public class ComodinVista extends CartaVista {
    public ComodinVista(Comodin comodin) {
        Label label = new Label("Comodín");

        getChildren().addAll(label);
    }
}
