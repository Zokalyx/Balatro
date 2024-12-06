package edu.fiuba.algo3.vistas.juego;

import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.contenedores.Tienda;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugada.Jugada;
import edu.fiuba.algo3.vistas.general.PokerVista;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class JugadaVista extends HBox implements Observer {
    private final Tienda tienda;
    private final List<PokerVista> vistas;
    Juego juego;

    public JugadaVista(Mano mano, Tienda tienda, Juego juego) {
        this.tienda = tienda;
        this.vistas = new ArrayList<>();
        this.juego = juego;

        reconstruirVista(mano.getJugada());

        setSpacing(20);
        setAlignment(Pos.CENTER);

        mano.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Mano) {
            Mano mano = (Mano) o;
            reconstruirVista(mano.getJugada());
        }
    }

    private void reconstruirVista(Jugada jugada) {
        List<PokerVista> nuevasVistas = new ArrayList<>();

        for (Poker poker : jugada.getCartas()) {
            PokerVista vista = obtenerPokerVista(poker);
            if (vista == null) {
                vista = new PokerVista(poker);
            }
            // Reutilizamos la vista si ya existe
            nuevasVistas.add(vista);
        }

        getChildren().clear();
        getChildren().addAll(nuevasVistas);

        vistas.clear();
        vistas.addAll(nuevasVistas);

        boolean habilitado = !tienda.getEstado() && !juego.gano() && !juego.perdio();

        setVisible(habilitado);
        setManaged(habilitado);
    }

    private PokerVista obtenerPokerVista(Poker poker) {
        for (PokerVista vista : vistas) {
            if (vista.esPokerVista(poker)) {
                return vista;
            }
        }
        return null;
    }
}
