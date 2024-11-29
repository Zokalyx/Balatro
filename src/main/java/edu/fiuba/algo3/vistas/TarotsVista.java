package edu.fiuba.algo3.vistas;


import javafx.geometry.Pos;
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
        setAlignment(Pos.CENTER);

        getChildren().addAll(cartas);
    }
}