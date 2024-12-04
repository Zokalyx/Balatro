package edu.fiuba.algo3.vistas;


import edu.fiuba.algo3.modelo.comodin.ActivacionComodinSiempre;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.comodin.ComodinIndividual;
import edu.fiuba.algo3.modelo.contenedores.Comodines;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ComodinesVista extends VBox implements Observer {
    ArrayList<ComodinVista> vistas;

    public ComodinesVista(Comodines comodines) {
        vistas = new ArrayList<>();

        for (Comodin comodin : comodines.getArray()) {
            ComodinVista vista = new ComodinVista(comodin);
            vista.setAnimacion(40, 20);
            vistas.add(vista);
        }

        setSpacing(-80);
        setAlignment(Pos.CENTER);
        setMinWidth(120);

        comodines.addObserver(this);

        getChildren().addAll(vistas);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Comodines) {
            Comodines comodines = (Comodines) o;

            vistas.clear();
            for (Comodin comodin : comodines.getArray()) {
                ComodinVista vista = new ComodinVista(comodin);
                vista.setAnimacion(-40, 20);
                vistas.add(vista);
            }

            getChildren().clear();
            getChildren().addAll(vistas);
        }
    }
}