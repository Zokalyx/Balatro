package edu.fiuba.algo3.modelo.jugada;

import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.tarot.ModificablePorTarot;

import java.util.ArrayList;

public abstract class Jugada implements ModificablePorTarot {
    ArrayList<Poker> cartas;
    double multiplicadorBase;
    int valorBase;

    public Jugada() {
        this.cartas = new ArrayList<>();
    }

    public void modificarPuntaje(Puntaje puntaje) {
        for (Poker carta : cartas) {
            carta.modificarPuntaje(puntaje);
        }

        puntaje.multiplicarMultiplicador((int)multiplicadorBase);
        puntaje.sumarValorBase(valorBase);
    }

    @Override
    public void modificarse(int puntos, double multiplicador){
        valorBase += puntos;
        multiplicadorBase *= multiplicador;
    }

    public abstract ArrayList<Poker> formarseConCartas(ArrayList<Poker> cartas);

    public void setCartas(ArrayList<Poker> cartasInvolucradas) {
        this.cartas = cartasInvolucradas;
    }
}
