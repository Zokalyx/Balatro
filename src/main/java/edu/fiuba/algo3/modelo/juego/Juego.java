package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.contenedores.DescartesInsuficientesError;

import java.util.Observable;

public class Juego extends Observable {
    int rondaActual;
    int rondaMaxima;
    int puntajeActual;
    int puntajeObjetivo;
    int turnosDisponibles;
    int descartesDisponibles;
    ConfiguracionJuego configuracion;

    boolean gano;
    boolean perdio;

    public Juego(ConfiguracionJuego configuracion) {
        this.configuracion = configuracion;
        rondaMaxima = configuracion.getRondaMaxima();
        cargarRonda(0);
    }

    public boolean jugarTurno(int puntajeObtenido) {
        if (gano() || perdio()) {
            throw new JuegoYaTerminadoError("Ya se terminó el juego");
        }

        boolean pasoDeRonda=false;
        turnosDisponibles--;
        puntajeActual += puntajeObtenido;

        if (puntajeActual >= puntajeObjetivo) {
            rondaActual++;
            pasoDeRonda=true;
            if (rondaActual < rondaMaxima) {
                cargarRonda(rondaActual);
            }
        }

        gano = this.rondaActual >= this.rondaMaxima;
        if (gano) {
            // Evitar pasar a una ronda que no existe
            rondaActual--;
        }

        perdio = this.turnosDisponibles == 0;


        setChanged();
        notifyObservers();
        return pasoDeRonda;
    }

    public void utilizarDescarte() {
        if (descartesDisponibles == 0) {
            throw new DescartesInsuficientesError("No quedan más descartes disponibles.");
        }

        descartesDisponibles--;
        setChanged();
        notifyObservers();
    }

    public boolean gano() {
        return gano;
    }

    public boolean perdio() {
        return perdio;
    }

    public int getRondaActual() {
        return rondaActual;
    }

    private void cargarRonda(int ronda) {
        rondaActual = ronda;
        puntajeObjetivo = configuracion.getPuntajeObjetivo(ronda);
        turnosDisponibles = configuracion.getTurnoMaximo(ronda);
        descartesDisponibles = configuracion.getDescartes(ronda);
        puntajeActual = 0;
    }

    public int getPuntajeActual() {
        return puntajeActual;
    }

    public int getPuntajeObjetivo() {
        return puntajeObjetivo;
    }

    public int getRondaObjetivo() {
        return rondaMaxima;
    }

    public int getTurnosDisponibles() {
        return turnosDisponibles;
    }

    public int getDescartesDisponibles() {
        return descartesDisponibles;
    }
}
