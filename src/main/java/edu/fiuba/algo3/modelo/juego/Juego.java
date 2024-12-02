package edu.fiuba.algo3.modelo.juego;

public class Juego {
    int rondaActual;
    int rondaMaxima;
    int puntajeActual;
    int puntajeObjetivo;
    int turnoActual;
    int turnoMaximo;
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

        turnoActual++;
        puntajeActual += puntajeObtenido;

        if (puntajeActual >= puntajeObjetivo) {
            rondaActual++;
            if (rondaActual < rondaMaxima) {
                cargarRonda(rondaActual);
            }
        }
    }

    public boolean gano() {
        return this.rondaActual >= this.rondaMaxima;
    }

    public boolean perdio() {
        return this.turnoActual >= this.turnoMaximo;
    }

    public int getRondaActual() {
        return rondaActual;
    }

    private void cargarRonda(int ronda) {
        rondaActual = ronda;
        puntajeObjetivo = configuracion.getPuntajeObjetivo(ronda);
        turnoMaximo = configuracion.getTurnoMaximo(ronda);
        puntajeActual = 0;
        turnoActual = 0;
    }
}
