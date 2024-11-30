package edu.fiuba.algo3.vistas;


import edu.fiuba.algo3.modelo.comodin.ActivacionComodinSiempre;
import edu.fiuba.algo3.modelo.comodin.ComodinIndividual;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class ComodinesVista extends VBox {
    public ComodinesVista() {
        ArrayList<ComodinVista> cartas = new ArrayList<>();
        cartas.add(new ComodinVista(new ComodinIndividual("a", "b", 1, 2, new ActivacionComodinSiempre())));
        cartas.add(new ComodinVista(new ComodinIndividual("a", "b", 1, 2, new ActivacionComodinSiempre())));
        cartas.add(new ComodinVista(new ComodinIndividual("a", "b", 1, 2, new ActivacionComodinSiempre())));
        cartas.add(new ComodinVista(new ComodinIndividual("a", "b", 1, 2, new ActivacionComodinSiempre())));
        cartas.add(new ComodinVista(new ComodinIndividual("a", "b", 1, 2, new ActivacionComodinSiempre())));

        setSpacing(-80);
        setAlignment(Pos.CENTER);

        getChildren().addAll(cartas);
    }
}