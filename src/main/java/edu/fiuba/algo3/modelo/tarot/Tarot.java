package edu.fiuba.algo3.modelo.tarot;

import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.jugada.JugadaManager;

public class Tarot {
    String nombre;
    String descripcion;
    int puntos;
    double multiplicador;
    ActivacionTarot activacion;

    public Tarot(String nombre, String descripcion, int puntos, double multiplicador, ActivacionTarot activacion) {
        this.puntos = puntos;
        this.multiplicador = multiplicador;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activacion = activacion;
    }

    public void encontrarObjetivoYUtilizar(Mano mano, JugadaManager jugadaManager) {
        ModificablePorTarot modificable = activacion.encontrarObjetoASerModificado(mano, jugadaManager);
        utilizarSobre(modificable);
    }

    public void utilizarSobre(ModificablePorTarot modificable) {
        modificable.modificarse(puntos, multiplicador);
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
