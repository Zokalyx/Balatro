package edu.fiuba.algo3.modelo.tarot;

import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.Puntaje;

import java.util.ArrayList;

public class TarotPoker extends Tarot {

    TarotPoker(String nombre, String descripcion,String ejemplar,int puntos,double multiplicador){
        super(nombre, descripcion,ejemplar,puntos,multiplicador);
    }

    @Override
    public void modificar(ArrayList<? extends ModificablePorTarot> modificablesPorTarot){
        if(modificablesPorTarot.size()==1){
            ModificablePorTarot modificablePorTarotActual = modificablesPorTarot.get(0);
            modificablePorTarotActual.modificarse(puntos,multiplicador);
        }
        else {
            throw new DemasiadasCartasSeleccionadasError("demasiadas cartas seleccionadas");
        }
    }
}
