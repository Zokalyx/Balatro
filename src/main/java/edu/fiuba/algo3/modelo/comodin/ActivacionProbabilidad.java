package edu.fiuba.algo3.modelo.comodin;

import edu.fiuba.algo3.modelo.jugada.Jugada;

import java.util.Random;

public class ActivacionProbabilidad implements Activacion {
    int probabilidad;

    public ActivacionProbabilidad(int probabilidad){
        this.probabilidad = probabilidad;
    }

    @Override
    public int activaciones(Jugada jugada, int cartasDescartadas) {
        Random random = new Random();

        int randomInt = random.nextInt(probabilidad);
        if (randomInt == 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
