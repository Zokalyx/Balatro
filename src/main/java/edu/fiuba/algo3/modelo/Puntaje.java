package edu.fiuba.algo3.modelo;

public class Puntaje {
    int valorBase;
    double multiplicador;

    public Puntaje (int valorBase, int multiplicador) {
        this.valorBase = valorBase;
        this.multiplicador = multiplicador;
    }

    public void multiplicarMultiplicador(double multiplicador) {
        this.multiplicador *= multiplicador;
    }

    public void sumarValorBase(int valorBase) {
        this.valorBase += valorBase;
    }

    public int calcularTotal() {
        return (int) Math.round(valorBase * multiplicador);
    }
}
