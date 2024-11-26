package edu.fiuba.algo3.vistas;


import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class TarotsVista extends VBox {
    public TarotsVista() {
        ArrayList<TarotVista> cartas = new ArrayList<>();
        cartas.add(new TarotVista());
        cartas.add(new TarotVista());
        cartas.add(new TarotVista());
        cartas.add(new TarotVista());

        setSpacing(-80);

        getChildren().addAll(cartas);
    }
}