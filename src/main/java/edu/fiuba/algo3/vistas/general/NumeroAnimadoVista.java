package edu.fiuba.algo3.vistas.general;

import javafx.scene.control.Label;

public class NumeroAnimadoVista extends Label {
    TransicionNumero transicion;

    public NumeroAnimadoVista(int valorInicial) {
        setText(Integer.toString(valorInicial));
        transicion = new TransicionNumero(this, valorInicial, true);
    }

    public NumeroAnimadoVista(double valorInicial) {
        setText(Double.toString(valorInicial));
        transicion = new TransicionNumero(this, valorInicial, false);
    }

    public void setValorFinal(int valorFinal) {
        transicion.setValorFinal(valorFinal);
        transicion.stop();
        transicion.play();
    }

    public void setValorFinal(double valorFinal) {
        transicion.setValorFinal(valorFinal);
        transicion.stop();
        transicion.play();
    }
}
