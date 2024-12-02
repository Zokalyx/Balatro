package edu.fiuba.algo3.modelo.tarot;

public class Tarot {
    String nombre;
    String descripcion;
    int puntos;
    double multiplicador;
    ActivacionTarot activacion;

    public Tarot(String nombre, String descripcion, int puntos, double multiplicador, ActivacionTarot activacion) {
        this.puntos = puntos;
        this.multiplicador = multiplicador;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.activacion = activacion;
    }

    public void modificar(ModificablePorTarot modificablePorTarot) {
        if (!activacion.sePuedeActivarSobre(modificablePorTarot)) {
            throw new TarotNoAplicableError("No se puede aplicar el tarot sobre este objeto");
        }

        modificablePorTarot.modificarse(puntos, multiplicador);
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
