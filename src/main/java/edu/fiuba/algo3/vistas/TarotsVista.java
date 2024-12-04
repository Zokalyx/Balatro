package edu.fiuba.algo3.vistas;



import edu.fiuba.algo3.controllers.ControladorTarot;
import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.jugada.Jugada;
import edu.fiuba.algo3.modelo.jugada.JugadaManager;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import edu.fiuba.algo3.modelo.contenedores.Tarots;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class TarotsVista extends VBox implements Observer {
    ArrayList<TarotVista> vistas;
    Mano mano;
    JugadaManager jugadaManager;

    public TarotsVista(Tarots tarots, Mano mano, JugadaManager jugadaManager) {
        vistas = new ArrayList<>();

        this.mano = mano;
        this.jugadaManager = jugadaManager;

        actualizarVista(tarots);

        setSpacing(-80);
        setAlignment(Pos.CENTER);
        setMinWidth(120);

        tarots.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Tarots) {
            Tarots tarots = (Tarots) o;

            actualizarVista(tarots);
        }
    }

    private void actualizarVista(Tarots tarots) {
        List<TarotVista> nuevasVistas = new ArrayList<>();

        for (Tarot tarot : tarots.getArray()) {
            TarotVista vista = obtenerTarotVista(tarot);
            if (vista == null) {
                vista = new TarotVista(tarot);
                vista.setOnMouseClicked(new ControladorTarot(tarots, tarot, mano, jugadaManager));
                vista.setAnimacion(40, 20);
            }
            // Reutilizamos la vista si ya existe
            nuevasVistas.add(vista);
        }

        getChildren().clear();
        getChildren().addAll(nuevasVistas);

        vistas.clear();
        vistas.addAll(nuevasVistas);
    }

    private TarotVista obtenerTarotVista(Tarot tarot) {
        for (TarotVista vista : vistas) {
            if (vista.esTarotVista(tarot)) {
                return vista;
            }
        }
        return null;
    }
}