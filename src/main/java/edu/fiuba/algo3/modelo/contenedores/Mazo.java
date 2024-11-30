package edu.fiuba.algo3.modelo.contenedores;

import java.util.ArrayList;
import java.util.Collections;

public class Mazo<T> {
    ArrayList<T> cartas;

    public Mazo(ArrayList<T> cartas) {
        this.cartas = cartas;
        Collections.shuffle(this.cartas);
    }

    public T tomarCarta() {
        if (cartas.isEmpty()) {
            throw new SinCartasError("Mazo está vacío");
        }
        return cartas.remove(0);
    }

    public void agregar(ArrayList<T> cartas) {
        this.cartas.addAll(cartas);
    }
}
