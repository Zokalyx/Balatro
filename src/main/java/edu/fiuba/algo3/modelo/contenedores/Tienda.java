package edu.fiuba.algo3.modelo.contenedores;

import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.tarot.Tarot;

import java.util.ArrayList;

public class Tienda {
    Mazo<Comodin> mazoComodines;
    Mazo<Tarot> mazoTarots;

    ArrayList<Comodin> comodinesDisponibles;
    ArrayList<Tarot> tarotsDisponibles;

    public Tienda(Mazo<Comodin> mazoComodines, Mazo<Tarot> mazoTarots) {
        this.mazoComodines = mazoComodines;
        this.mazoTarots = mazoTarots;

        comodinesDisponibles = new ArrayList<>();
        tarotsDisponibles = new ArrayList<>();
    }

    public void refrescar() {
        this.comodinesDisponibles.clear();
        this.comodinesDisponibles.add(mazoComodines.tomarCarta());
        this.comodinesDisponibles.add(mazoComodines.tomarCarta());

        this.tarotsDisponibles.clear();
        this.tarotsDisponibles.add(mazoTarots.tomarCarta());
        this.tarotsDisponibles.add(mazoTarots.tomarCarta());
    }

    public ArrayList<Comodin> getComodinesDisponibles() {
        return comodinesDisponibles;
    }

    public ArrayList<Tarot> getTarotsDisponibles() {
        return tarotsDisponibles;
    }

    public Comodin comprarComodin(Comodin comodin) {
        if (!comodinesDisponibles.contains(comodin)) {
            throw new CartaNoDisponibleError("Esa carta no está disponible en la tienda");
        }

        comodinesDisponibles.remove(comodin);
        return comodin;
    }

    public Tarot comprarTarot(Tarot tarot) {
        if (!tarotsDisponibles.contains(tarot)) {
            throw new CartaNoDisponibleError("Esa carta no está disponible en la tienda");
        }

        tarotsDisponibles.remove(tarot);
        return tarot;
    }
}
