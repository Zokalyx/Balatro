package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.contenedores.Comodines;
import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.contenedores.Mazo;
import edu.fiuba.algo3.modelo.jugada.Jugada;

import java.util.ArrayList;

public class Jugador {
    Mazo mazo;
    Mano mano;
    Comodines comodines;
    Puntaje puntaje;

    public Jugador(Mazo mazo) {
        this.mazo = mazo;
        mano = new Mano();
        ArrayList<Comodin> comodinesCartas = new ArrayList<>();
        comodines = new Comodines(comodinesCartas);
        puntaje = new Puntaje(0, 0);
    }

    public void repartirMano()  {
        mano.repartirUsando(mazo);
    }

    public int jugarMano() {
        Jugada jugada = mano.jugar();
        jugada.modificarPuntaje(puntaje);
        mano.modificarPuntaje(puntaje);
        comodines.modificarPuntaje(puntaje);
        int puntajeFinal = puntaje.calcularTotal();
        puntaje = new Puntaje(0, 0);
        return puntajeFinal;
    }

    public void seleccionarCarta(Poker carta) {
        mano.seleccionarCarta(carta);
    }

    public void agregarComodin(Comodin comodin) {
        comodines.agregar(comodin);
    }
}
