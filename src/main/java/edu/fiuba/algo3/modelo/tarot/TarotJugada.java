package edu.fiuba.algo3.modelo.tarot;


import edu.fiuba.algo3.modelo.jugada.Jugada;

import java.util.ArrayList;

public class TarotJugada extends Tarot {
    public TarotJugada(String nombre, String descripcion,String ejemplar,int puntos,double multiplicador){
        super(nombre, descripcion,ejemplar,puntos,multiplicador);
    }

    @Override
    public void modificar(ArrayList<? extends ModificablePorTarot> modificablesPorTarot){
        ModificablePorTarot encontrado=null;
        for(ModificablePorTarot actual:modificablesPorTarot){
            if(actual.esEjemplar(ejemplar)){
                encontrado=actual;
            }
        }
        if(encontrado == null){
            throw new JugadaNoEncontradaError("La jugada no se encontro");
        }
        encontrado.modificarse(puntos,multiplicador);
    }

}
