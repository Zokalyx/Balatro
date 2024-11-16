package edu.fiuba.algo3.modelo;

public class Puntaje {
    double multiplicador;
    int valorBase;

    public Puntaje (int multiplicador, int valorBase) {
        this.multiplicador = multiplicador;
        this.valorBase = valorBase;
    }

    public void sumarMultiplicador(double multiplicador) {
        this.multiplicador += multiplicador;
    }

    public void sumarValorBase(int valorBase) {
        this.valorBase += valorBase;
    }

    public int calcularTotal() {
        return (int) Math.round(valorBase * multiplicador);
    }
}
