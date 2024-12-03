package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.LectorJson;
import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.contenedores.Mazo;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class CartasMenuVista extends HBox {
    public CartasMenuVista() {
        ArrayList<CartaVista> vistas = new ArrayList<>();

        LectorJson lectorJson = new LectorJson();
        Mazo<Poker> pokers = new Mazo<>(lectorJson.leerMazo());
        Mazo<Tarot> tarots = new Mazo<>(lectorJson.leerTarots());
        Mazo<Comodin> comodines = new Mazo<>(lectorJson.leerComodines());

        int n = 6;
        for (int i = 0; i < n; i++) {
            CartaVista vista;
            if (i % 3 == 0) {
                vista = new PokerVista(pokers.tomarCarta());
            } else if (i % 3 == 1) {
                vista = new TarotVista(tarots.tomarCarta());
            } else {
                vista = new ComodinVista(comodines.tomarCarta());
            }
            vista.setRotate((i + 0.5 - (double) n / 2) * 10);

            vista.setOffset(0,40);
            vistas.add(vista);
        }

        setTranslateY(110);
        setSpacing(50);
        setAlignment(Pos.CENTER);

        getChildren().addAll(vistas);
    }
}
