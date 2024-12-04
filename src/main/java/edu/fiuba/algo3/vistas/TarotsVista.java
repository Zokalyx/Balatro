package edu.fiuba.algo3.vistas;


import edu.fiuba.algo3.modelo.comodin.ActivacionComodinSiempre;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.comodin.ComodinIndividual;
import edu.fiuba.algo3.modelo.contenedores.Comodines;
import edu.fiuba.algo3.modelo.contenedores.Tarots;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class TarotsVista extends VBox implements Observer {
    ArrayList<TarotVista> vistas;

    public TarotsVista(Tarots tarots) {
        vistas = new ArrayList<>();

        for (Tarot tarot : tarots.getArray()) {
            TarotVista vista = new TarotVista(tarot);
            vista.setAnimacion(40, 20);
            vistas.add(vista);
        }

        setSpacing(-80);
        setAlignment(Pos.CENTER);
        setMinWidth(120);

        tarots.addObserver(this);

        getChildren().addAll(vistas);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Tarots) {
            Tarots tarots = (Tarots) o;

            vistas.clear();
            for (Tarot tarot : tarots.getArray()) {
                TarotVista vista = new TarotVista(tarot);
                vista.setAnimacion(40, 20);
                vistas.add(vista);
            }

            getChildren().clear();
            getChildren().addAll(vistas);
        }
    }
}