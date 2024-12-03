package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.contenedores.DescartesInsuficientesError;

public class Juego {
    int rondaActual;
    int rondaMaxima;
    int puntajeActual;
    int puntajeObjetivo;
    int turnosDisponibles;
    int descartesDisponibles;
    ConfiguracionJuego configuracion;

    public Juego(ConfiguracionJuego configuracion) {
        this.configuracion = configuracion;
        rondaMaxima = configuracion.getRondaMaxima();
        cargarRonda(0);
    }

    public void jugarTurno(int puntajeObtenido) {
        if (gano() || perdio()) {
            throw new JuegoYaTerminadoError("Ya se terminó el juego");
        }

        turnosDisponibles--;
        puntajeActual += puntajeObtenido;

        if (puntajeActual >= puntajeObjetivo) {
            rondaActual++;
            if (rondaActual < rondaMaxima) {
                cargarRonda(rondaActual);
            }
        }
    }

    public void utilizarDescarte() {
        if (descartesDisponibles == 0) {
            throw new DescartesInsuficientesError("No quedan más descartes disponibles.");
        }

        descartesDisponibles--;
    }

    public boolean gano() {
        return this.rondaActual >= this.rondaMaxima;
    }

    public boolean perdio() {
        return this.turnosDisponibles == 0;
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
