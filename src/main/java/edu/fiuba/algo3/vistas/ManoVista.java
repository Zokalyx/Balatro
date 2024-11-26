package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.palo.Diamante;
import edu.fiuba.algo3.modelo.palo.Trebol;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class ManoVista extends HBox {
    public ManoVista() {
        ArrayList<PokerVista> cartas = new ArrayList<>();
        cartas.add(new PokerVista("Q", new Trebol()));
        cartas.add(new PokerVista("K", new Diamante()));
        cartas.add(new PokerVista("A", new Trebol()));
        cartas.add(new PokerVista("2", new Diamante()));
        cartas.add(new PokerVista("3", new Trebol()));
        cartas.add(new PokerVista("5", new Diamante()));
        cartas.add(new PokerVista("8", new Trebol()));
        cartas.add(new PokerVista("2", new Diamante()));

        setSpacing(-50);

        getChildren().addAll(cartas);
    }
}
