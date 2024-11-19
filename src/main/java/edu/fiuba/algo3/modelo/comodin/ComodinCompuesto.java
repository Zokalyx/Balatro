package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.jugada.Jugada;

import java.util.ArrayList;

public class ComodinCompuesto extends Comodin {
    ArrayList<Comodin> comodines;

    public ComodinCompuesto(String nombre, String descripcion, ArrayList<Comodin> comodines) {
        super(nombre, descripcion);
        this.comodines = comodines;
    }

    @Override
    public void modificarPuntaje(Puntaje puntaje, Jugada jugada, int cartasDescartadas) {
        for (Comodin comodin : comodines) {
            comodin.modificarPuntaje(puntaje, jugada, cartasDescartadas);
        }
    }
}
