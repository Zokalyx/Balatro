package edu.fiuba.algo3.vistas;


import javafx.geometry.Pos;
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
        setAlignment(Pos.CENTER);

        getChildren().addAll(cartas);
    }
}