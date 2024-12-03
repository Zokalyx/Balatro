package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.palo.Corazon;
import edu.fiuba.algo3.modelo.palo.Diamante;
import edu.fiuba.algo3.modelo.palo.Pica;
import edu.fiuba.algo3.modelo.palo.Trebol;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class ManoVista extends HBox {
    public ManoVista(Mano mano) {
        ArrayList<Poker> cartas = mano.getCartas();
        ArrayList<PokerVista> vistas = new ArrayList<>();
        for (Poker poker : cartas) {
            vistas.add(new PokerVista(poker));
        }

        // SACAR
        vistas.add(new PokerVista(new Poker("As", new Diamante(), 10, 5)));
        vistas.add(new PokerVista(new Poker("Rey", new Trebol(), 2, 1)));
        vistas.add(new PokerVista(new Poker("7", new Pica(), 3, 1)));
        vistas.add(new PokerVista(new Poker("7", new Corazon(), 3, 1)));

        setSpacing(-50);
        setAlignment(Pos.CENTER);

        getChildren().addAll(vistas);
    }
}
