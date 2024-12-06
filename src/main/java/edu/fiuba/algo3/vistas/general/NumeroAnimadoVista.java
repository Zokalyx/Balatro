package edu.fiuba.algo3.vistas.general;

import javafx.scene.control.Label;
import javafx.util.Duration;

public class NumeroAnimadoVista extends Label {
    TransicionNumero transicion;

    public NumeroAnimadoVista(int valorInicial, Duration duracion) {
        setText(Integer.toString(valorInicial));
        transicion = new TransicionNumero(this, valorInicial, true, duracion);
    }

    public NumeroAnimadoVista(double valorInicial, Duration duracion) {
        setText(Double.toString(valorInicial));
        transicion = new TransicionNumero(this, valorInicial, false, duracion);
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
