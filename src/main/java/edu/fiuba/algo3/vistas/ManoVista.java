package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ControladorPoker;
import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.contenedores.Mano;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ManoVista extends HBox implements Observer {
    ArrayList<PokerVista> vistas;

    public ManoVista(Mano mano) {
        ArrayList<Poker> cartas = mano.getCartas();
        vistas = new ArrayList<>();
        for (Poker poker : cartas) {
            PokerVista vista = new PokerVista(poker);
            vista.setOnMouseClicked(new ControladorPoker(mano,poker));
            vistas.add(vista);

        }

        mano.addObserver(this);
        setSpacing(-25);
        setAlignment(Pos.CENTER);

        getChildren().addAll(vistas);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Mano) {
            Mano mano = (Mano) o;

            ArrayList<Poker> seleccionadas = mano.getSeleccion();
            ArrayList<Poker> cartas = mano.getCartas();
            for(Poker carta:cartas){
                PokerVista vista = obtenerPokerVista(carta);
                if(seleccionadas.contains(carta)){
                    vista.resaltar();
                    vista.setSeleccionado(true);
                }
                else{
                    vista.desresaltar();
                    vista.setSeleccionado(false);
                }
            }
        }
    }

    private PokerVista obtenerPokerVista(Poker poker){
        for(PokerVista vista:vistas){
            if(vista.esPokerVista(poker)){
                return vista;
            }
        }
        throw new RuntimeException("No se encontro poker vista");
    }
}
