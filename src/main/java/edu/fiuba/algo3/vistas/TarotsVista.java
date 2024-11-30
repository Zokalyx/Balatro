package edu.fiuba.algo3.vistas;


import edu.fiuba.algo3.modelo.tarot.Tarot;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class TarotsVista extends VBox {
    public TarotsVista() {
        ArrayList<TarotVista> cartas = new ArrayList<>();
        cartas.add(new TarotVista(Tarot.CrearTarot("T", "A", "carta", "a", 1, 2)));
        cartas.add(new TarotVista(Tarot.CrearTarot("T", "A", "carta", "a", 1, 2)));
        cartas.add(new TarotVista(Tarot.CrearTarot("T", "A", "carta", "a", 1, 2)));
        cartas.add(new TarotVista(Tarot.CrearTarot("T", "A", "carta", "a", 1, 2)));

        setSpacing(-80);
        setAlignment(Pos.CENTER);

        getChildren().addAll(cartas);
    }
}