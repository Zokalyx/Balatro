package edu.fiuba.algo3.modelo.contenedores;

import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.comodin.Comodin;

import java.util.ArrayList;

public class Comodines {
    ArrayList<Comodin> cartas;

    public Comodines(ArrayList<Comodin> cartas) {
        this.cartas = cartas;
    }

    public void modificarPuntaje(Puntaje puntaje) {
        if(!cartas.isEmpty()){
            for (Comodin comodin : cartas) {
                comodin.modificarPuntaje(puntaje);
            }
        }
    }

    public void agregar(Comodin comodin) {
        // Agregar límite (creo que 5)
        cartas.add(comodin);
    }
}
