package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.contenedores.Comodines;
import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.contenedores.Mazo;
import edu.fiuba.algo3.modelo.jugada.Jugada;

public class Jugador {
    Mano mano;
    Comodines comodines;
    Puntaje puntaje;
    int cantCartasDescartadas;

    public Jugador(Mazo mazo) {
        mano = new Mano(mazo);
        comodines = new Comodines();
        puntaje = new Puntaje(0, 0);
    }

    public void repartirMano()  {
        mano.repartir();
    }

    public int jugarMano() {
        Jugada jugada = mano.jugar();
        jugada.modificarPuntaje(puntaje);
        mano.modificarPuntaje(puntaje);
        comodines.modificarPuntaje(puntaje, jugada, cantCartasDescartadas);
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

    public void descartarCartas() {
        cantCartasDescartadas = mano.descartar();
    }
}
