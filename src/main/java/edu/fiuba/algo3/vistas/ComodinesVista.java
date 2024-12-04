package edu.fiuba.algo3.vistas;


import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.comodin.ActivacionComodinSiempre;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.comodin.ComodinIndividual;
import edu.fiuba.algo3.modelo.contenedores.Comodines;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ComodinesVista extends VBox implements Observer {
    ArrayList<ComodinVista> vistas;

    public ComodinesVista(Comodines comodines) {
        vistas = new ArrayList<>();

        actualizarVista(comodines);

        setSpacing(-80);
        setAlignment(Pos.CENTER);
        setMinWidth(120);

        comodines.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Comodines) {
            Comodines comodines = (Comodines) o;

            actualizarVista(comodines);
        }
    }

    private void actualizarVista(Comodines comodines) {
        List<ComodinVista> nuevasVistas = new ArrayList<>();

        for (Comodin comodin : comodines.getArray()) {
            ComodinVista vista = obtenerComodinVista(comodin);
            if (vista == null) {
                vista = new ComodinVista(comodin);
                vista.setAnimacion(-20, 40);
            }
            // Reutilizamos la vista si ya existe
            nuevasVistas.add(vista);
        }

        getChildren().clear();
        getChildren().addAll(nuevasVistas);

        vistas.clear();
        vistas.addAll(nuevasVistas);
    }

    private ComodinVista obtenerComodinVista(Comodin comodin) {
        for (ComodinVista vista : vistas) {
            if (vista.esComodinVista(comodin)) {
                return vista;
            }
        }
        return null;
    }
}