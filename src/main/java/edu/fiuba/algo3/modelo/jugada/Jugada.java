package edu.fiuba.algo3.modelo.jugada;

import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.tarot.ModificablePorTarot;

import java.util.ArrayList;
import java.util.Observable;

public abstract class Jugada extends Observable implements ModificablePorTarot {
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

        setChanged();
        notifyObservers();
    }

    public abstract ArrayList<Poker> formarseConCartas(ArrayList<Poker> cartas);

    public void setCartas(ArrayList<Poker> cartasInvolucradas) {
        this.cartas = cartasInvolucradas;
    }

    public ArrayList<Poker> getCartas() {
        return cartas;
    }

    public int getValor() {
        return valorBase;
    }

    public double getMultiplicador() {
        return multiplicadorBase;
    }
}
