package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.contenedores.Mazo;
import edu.fiuba.algo3.modelo.tarot.Tarot;

import java.util.ArrayList;

public class ConfiguracionJuego {
    ArrayList<ConfiguracionRonda> rondas;
    Mazo<Poker> mazo;

    public ConfiguracionJuego(ArrayList<ConfiguracionRonda> rondas, Mazo<Poker> mazo) {
        this.rondas = rondas;
        this.mazo = mazo;
    }

    public Mazo<Poker> getMazo() {
        return mazo;
    }

    public int getPuntajeObjetivo(int ronda) {
        return rondas.get(ronda).getPuntajeObjetivo();
    }

    public int getTurnoMaximo(int ronda) {
        return rondas.get(ronda).getTurnoMaximo();
    }

    public int getRondaMaxima() {
        return rondas.size();
    }

    public ArrayList<Comodin> getComodines(int ronda) {
        return rondas.get(ronda).getComodines();
    }

    public ArrayList<Tarot> getTarots(int ronda) {
        return rondas.get(ronda).getTarots();
    }

    public ArrayList<Poker> getPokers(int ronda) {
        return rondas.get(ronda).getPokers();
    }

    public int getDescartes(int ronda) {
        return rondas.get(ronda).getDescartes();
    }
}
