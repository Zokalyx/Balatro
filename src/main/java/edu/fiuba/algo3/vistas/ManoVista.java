package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ControladorPoker;
import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.jugada.Jugada;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ManoVista extends HBox implements Observer {
    ArrayList<PokerVista> vistas;

    public ManoVista(Mano mano) {
        vistas = new ArrayList<>();

        reconstruirVista(mano);

        mano.addObserver(this);
        setSpacing(-25);
        setAlignment(Pos.CENTER);
        setMinHeight(180);

        getChildren().addAll(vistas);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Mano) {
            Mano mano = (Mano) o;

            reconstruirVista(mano);
        }
    }

    private void reconstruirVista(Mano mano) {
        List<PokerVista> nuevasVistas = new ArrayList<>();

        for (Poker poker : mano.getCartas()) {
            PokerVista vista = obtenerPokerVista(poker);
            if (vista == null) {
                vista = new PokerVista(poker);
                vista.setAnimacion(20, 40);
                vista.setOnMouseClicked(new ControladorPoker(mano, poker));
            } else if (mano.getSeleccion().contains(poker)) {
                vista.resaltar();
                vista.setSeleccionado(true);
            } else {
                vista.desresaltar();
                vista.setSeleccionado(false);
                vista.fireEvent(new MouseEvent(MouseEvent.MOUSE_EXITED, 0, 0, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null));

            }
            // Reutilizamos la vista si ya existe
            nuevasVistas.add(vista);
        }

        getChildren().clear();
        getChildren().addAll(nuevasVistas);

        vistas.clear();
        vistas.addAll(nuevasVistas);
    }

    private PokerVista obtenerPokerVista(Poker poker){
        for(PokerVista vista:vistas){
            if(vista.esPokerVista(poker)){
                return vista;
            }
        }
        return null;
    }
}
