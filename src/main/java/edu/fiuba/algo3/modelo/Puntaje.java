package edu.fiuba.algo3.modelo;

import java.util.Observable;

public class Puntaje extends Observable {
    int valorBase;
    double multiplicador;

    public Puntaje (int valorBase, int multiplicador) {
        this.valorBase = valorBase;
        this.multiplicador = multiplicador;
    }

    public void multiplicarMultiplicador(double multiplicador) {
        this.multiplicador *= multiplicador;
        setChanged();
        notifyObservers();
    }

    public void sumarMultiplicador(double multiplicador) {
        this.multiplicador += multiplicador;
        setChanged();
        notifyObservers();
    }

    public void sumarValorBase(int valorBase) {
        this.valorBase += valorBase;
        setChanged();
        notifyObservers();
    }

    public int calcularTotal() {
        return (int) Math.round(valorBase * multiplicador);
    }

    public int getValor() {
        return valorBase;
    }

    public double getMultiplicador() {
        return multiplicador;
    }

    public void reiniciar(){
        valorBase = 0;
        multiplicador = 1;
        setChanged();
        notifyObservers();
    }

}
