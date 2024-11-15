package edu.fiuba.algo3.modelo;


import edu.fiuba.algo3.modelo.palo.Palo;
import edu.fiuba.algo3.modelo.tarot.ModificablePorTarot;

public class Poker implements Carta, ModificablePorTarot {
    String simbolo;
    double multiplicador;
    int valorNumerico;
    Palo palo;

    public Poker(int multiplicador, String simbolo, int valorNumerico, Palo palo) {
        this.multiplicador = multiplicador;
        this.simbolo = simbolo;
        this.valorNumerico = valorNumerico;
        this.palo = palo;

    }
    public int usar() {
        return (int) Math.round(valorNumerico * multiplicador);
    }


    public boolean esMismoPaloQue(Poker carta) {
        return palo.sonDelMismoPalo(carta);
    }

    public boolean esMismoSimboloQue(Poker carta) {
        return simbolo.equals(carta.simbolo);
    }

    public Palo getPalo() {
        return this.palo;
    }


    public void modificarPuntaje(Puntaje puntaje) {
        puntaje.sumarValorBase(valorNumerico);
        puntaje.sumarMultiplicador(multiplicador);
    }

    public int getValorDeCarta() {
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

    public String getSimbolo() {
        return simbolo;
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
}

