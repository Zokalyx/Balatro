package edu.fiuba.algo3.vistas.juego;

import edu.fiuba.algo3.controllers.ControladorPoker;
import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.vistas.general.PokerVista;
import edu.fiuba.algo3.vistas.general.SonidoManager;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ManoVista extends HBox implements Observer {
    ArrayList<PokerVista> vistas;
    JuegoScene juegoVista;

    public ManoVista(Mano mano, JuegoScene juegoVista) {
        this.juegoVista = juegoVista;

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

        // Agregar y seleccionar
        for (Poker poker : mano.getCartas()) {
            PokerVista vista = obtenerPokerVista(poker);
            if (vista == null) {
                vista = new PokerVista(poker);
                vista.setAnimacion(20, 40);
                vista.setOnMouseClicked(new ControladorPoker(mano, poker, juegoVista));
                vistas.add(vista);
                getChildren().add(vista);

            } else if (mano.getSeleccion().contains(poker)) {
                vista.setSeleccionado(true);
                vista.resaltar();

            } else {
                vista.setSeleccionado(false);
                vista.desresaltar();
            }
        }

        // Eliminar viejas
        ArrayList<PokerVista> vistasAEliminar = new ArrayList<>();
        for (PokerVista vista : vistas) {
            if (!mano.getCartas().contains(vista.getPoker())) {
                vistasAEliminar.add(vista);
            }
        }
        for (PokerVista vista : vistasAEliminar) {
            vistas.remove(vista);
            getChildren().remove(vista);
        }
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
