package edu.fiuba.algo3.modelo.jugada;

import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.tarot.ModificablePorTarot;

import java.util.ArrayList;

public abstract class Jugada implements ModificablePorTarot {
    ArrayList<Poker> cartas;
    double multiplicadorBase;
    int valorBase;
    String nombre;

    public static boolean verificar(ArrayList<Poker> cartas) {
        return false;
    }

    public Jugada(ArrayList<Poker> cartas) {
        this.cartas = cartas;
    }

    public void modificarPuntaje(Puntaje puntaje) {
        puntaje.sumarMultiplicador((int)multiplicadorBase);
        puntaje.sumarValorBase(valorBase);

        for (Poker carta : cartas) {
            carta.modificarPuntaje(puntaje);
        }
    }
    @Override
    public boolean esEjemplar(String nombre) {
        return this.nombre.equals(nombre);
    }
    @Override
    public void modificarse(int puntos, double multiplicador){
        valorBase += puntos;
        multiplicadorBase += multiplicador;
    }
}
