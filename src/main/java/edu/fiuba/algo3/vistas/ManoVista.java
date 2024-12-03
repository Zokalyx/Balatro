package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ControladorPoker;
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
            PokerVista vista = new PokerVista(poker);
            vista.setOnMouseClicked(new ControladorPoker());
            vistas.add(vista);
        }

        setSpacing(-50);
        setAlignment(Pos.CENTER);

        getChildren().addAll(vistas);
    }
}
