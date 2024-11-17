package edu.fiuba.algo3.modelo.contenedores;

import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.comodin.Comodin;

import java.util.ArrayList;

public class Comodines {
    ArrayList<Comodin> cartas;
    int capacidad;

    public Comodines() {
        this.cartas = new ArrayList<>();
        this.capacidad = 5;
    }

    public void modificarPuntaje(Puntaje puntaje) {
        if(!cartas.isEmpty()){
            for (Comodin comodin : cartas) {
                comodin.modificarPuntaje(puntaje);
            }
        }
    }

    public void agregar(Comodin comodin) {
        if (cartas.size() >= capacidad) {
            throw new ComodinesLlenoError("No entran más comodines.");
        }
        cartas.add(comodin);
    }
}
