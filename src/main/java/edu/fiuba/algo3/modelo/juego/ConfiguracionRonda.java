package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.tarot.Tarot;

import java.util.ArrayList;

public class ConfiguracionRonda {
    int puntajeObjetivo;
    int turnoMaximo;
    int descartes;
    ArrayList<Comodin> comodines;
    ArrayList<Tarot> tarots;
    ArrayList<Poker> cartas;

    public ConfiguracionRonda(int puntajeObjetivo, int turnoMaximo, int descartes, ArrayList<Comodin> comodines, ArrayList<Tarot> tarots, ArrayList<Poker> cartas) {
        this.puntajeObjetivo = puntajeObjetivo;
        this.turnoMaximo = turnoMaximo;
        this.descartes = descartes;
        this.comodines = comodines;
        this.tarots = tarots;
        this.cartas = cartas;
    }

    public int getPuntajeObjetivo() {
        return puntajeObjetivo;
    }

    public int getTurnoMaximo() {
        return turnoMaximo;
    }

    public ArrayList<Comodin> getComodines() {
        return comodines;
    }

    public ArrayList<Tarot> getTarots() {
        return tarots;
    }

    public ArrayList<Poker> getPokers() {
        return cartas;
    }

    public int getDescartes() {
        return descartes;
    }
}
