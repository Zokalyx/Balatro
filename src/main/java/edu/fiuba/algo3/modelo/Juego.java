package edu.fiuba.algo3.modelo;

public class Juego {
    int rondaActual;
    int puntajeActual;
    int puntajeObjetivo;
    int turnoActual;

    public Juego() {
        rondaActual = 1;
        turnoActual = 1;
        puntajeObjetivo = calcularPuntajeObjetivo(rondaActual);
        puntajeActual = 0;
    }

    public void jugarTurno(int puntajeObtenido) {
        puntajeActual += puntajeObtenido;
        turnoActual++;

        if (puntajeActual >= puntajeObjetivo) {
            avanzarRonda();
        }
    }

    public boolean gano() {
        return rondaActual > 8;
    }

    public boolean perdio() {
        return turnoActual > 5;
    }

    private void avanzarRonda() {
        turnoActual = 0;
        rondaActual++;
        puntajeActual = 0;
        puntajeObjetivo = calcularPuntajeObjetivo(rondaActual);
    }

    private int calcularPuntajeObjetivo(int ronda) {
        return ronda * 100;
    }
}
