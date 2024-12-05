package edu.fiuba.algo3.vistas;

import javafx.animation.Transition;
import javafx.scene.control.Label;
import javafx.util.Duration;

class TransicionNumero extends Transition {
    double valorInicial;
    double valorFinal;
    Label label;
    boolean redondear;

    public TransicionNumero(Label label, double valorInicial, boolean redondear) {
        this.label = label;
        this.valorInicial = valorInicial;
        setCycleDuration(Duration.seconds(0.5));
        setCycleCount(1);
        this.redondear = redondear;
    }

    public void setValorFinal(int valorFinal) {
        this.valorInicial = this.valorFinal;
        this.valorFinal = valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorInicial = this.valorFinal;
        this.valorFinal = valorFinal;
    }

    @Override
    protected void interpolate(double frac) {
        double valorActual = valorInicial + frac * (valorFinal - valorInicial);
        if (redondear) {
            label.setText(String.valueOf(Math.round(valorActual)));
        } else {
            label.setText(String.format("%.1f", valorActual));
        }

    }
}
