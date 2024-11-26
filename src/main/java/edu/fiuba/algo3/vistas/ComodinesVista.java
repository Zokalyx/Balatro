package edu.fiuba.algo3.vistas;


import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class ComodinesVista extends VBox {
    public ComodinesVista() {
        ArrayList<ComodinVista> cartas = new ArrayList<>();
        cartas.add(new ComodinVista());
        cartas.add(new ComodinVista());
        cartas.add(new ComodinVista());
        cartas.add(new ComodinVista());
        cartas.add(new ComodinVista());

        setSpacing(-80);

        getChildren().addAll(cartas);
    }
}