package edu.fiuba.algo3.modelo.contenedores;

import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.tarot.Tarot;

import java.util.ArrayList;

public class Tienda {
    ArrayList<Comodin> comodinesDisponibles;
    ArrayList<Tarot> tarotsDisponibles;
    ArrayList<Poker> pokerDisponibles;

    public Tienda(ArrayList<Comodin> comodines, ArrayList<Tarot> tarots, ArrayList<Poker> pokers) {
        this.comodinesDisponibles = comodines;
        this.tarotsDisponibles = tarots;
        this.pokerDisponibles = pokers;
    }

    public ArrayList<Comodin> getComodinesDisponibles() {
        return comodinesDisponibles;
    }

    public ArrayList<Tarot> getTarotsDisponibles() {
        return tarotsDisponibles;
    }

    public ArrayList<Poker> getCartasDisponibles() {
        return pokerDisponibles;
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

    public Poker comprarPoker(Poker poker) {
        if (!pokerDisponibles.contains(poker)) {
            throw new CartaNoDisponibleError("Esta carta no está disponible en la tienda");
        }

        pokerDisponibles.remove(poker);
        return poker;
    }
}
