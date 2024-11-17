package edu.fiuba.algo3.modelo;


import edu.fiuba.algo3.modelo.palo.Palo;
import edu.fiuba.algo3.modelo.tarot.ModificablePorTarot;

public class Poker implements Carta, ModificablePorTarot, Comparable<Poker> {
    String simbolo;
    Palo palo;
    int valorNumerico;
    double multiplicador;

    public Poker(String simbolo, Palo palo, int valorNumerico, int multiplicador) {
        this.multiplicador = multiplicador;
        this.simbolo = simbolo;
        this.valorNumerico = valorNumerico;
        this.palo = palo;
    }

    public int usar() {
        return (int) Math.round(valorNumerico * multiplicador);
    }

    public boolean esMismoPaloQue(Poker carta) {
        return palo.equals(carta.palo);
    }

    public boolean esMismoSimboloQue(Poker carta) {
        return simbolo.equals(carta.simbolo);
    }

    public void modificarPuntaje(Puntaje puntaje) {
        puntaje.sumarValorBase(valorNumerico);
        puntaje.sumarMultiplicador(multiplicador);
    }

    public boolean esSimboloSiguienteA(Poker carta) {
        int valorPropio = getValorDeCarta();
        int valorCarta = carta.getValorDeCarta();
        return (valorPropio - valorCarta == 1) || (simbolo.equals("As") && carta.simbolo.equals("Rey"));
    }

    public boolean esSimboloAnteriorA(Poker carta) {
        int valorPropio = getValorDeCarta();
        int valorCarta = carta.getValorDeCarta();
        return (valorCarta - valorPropio == 1) || (simbolo.equals("Rey") && carta.simbolo.equals("As"));
    }

    private int getValorDeCarta() {
        switch (simbolo) {
            case "As":
                return 1;
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "10":
                return 10;
            case "Jota":
                return 11;
            case "Reina":
                return 12;
            case "Rey":
                return 13;
            default:
                throw new IllegalArgumentException("Número de carta inválido: " + simbolo);
        }
    }

    @Override
    public void modificarse(int puntos, double multiplicador) {
        if(puntos!=1){
            valorNumerico = puntos;
        }
        this.multiplicador = multiplicador;
    }

    @Override
    public boolean esEjemplar(String ejemplar) {
        return true;
    }

    @Override
    public int compareTo(Poker carta) {
        return this.getValorDeCarta() - carta.getValorDeCarta();
    }

    public boolean esAs() {
        return this.simbolo.equals("As");
    }
}

