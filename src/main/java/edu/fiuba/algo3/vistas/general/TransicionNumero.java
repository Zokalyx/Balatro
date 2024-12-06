package edu.fiuba.algo3.vistas.general;

import javafx.animation.Transition;
import javafx.scene.control.Label;
import javafx.util.Duration;

class TransicionNumero extends Transition {
    double valorInicial;
    double valorFinal;
    double valorActual;
    Label label;
    boolean redondear;

    public TransicionNumero(Label label, double valorInicial, boolean redondear, Duration duracion) {
        this.label = label;
        this.valorInicial = valorInicial;
        this.valorActual = valorInicial;
        setCycleDuration(duracion);
        setCycleCount(1);
        this.redondear = redondear;
    }

    public void setValorFinal(int valorFinal) {
        this.valorInicial = valorActual;
        this.valorFinal = valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorInicial = valorActual;
        this.valorFinal = valorFinal;
    }

    @Override
    protected void interpolate(double frac) {
        valorActual = valorInicial + frac * (valorFinal - valorInicial);
        if (redondear) {
            label.setText(String.valueOf(Math.round(valorActual)));
        } else {
            label.setText(String.format("%.1f", valorActual));
        }

    }
}
