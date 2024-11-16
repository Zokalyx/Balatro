package edu.fiuba.algo3.modelo.tarot;

import java.util.ArrayList;

public abstract class Tarot {
    String nombre;
    String descripcion;
    int puntos;
    double multiplicador;
    String ejemplar;

    public Tarot(String nombre, String descripcion,String ejemplar,int puntos,double multiplicador) {
        this.puntos = puntos;
        this.multiplicador = multiplicador;
        this.ejemplar=ejemplar;
        this.nombre=nombre;
        this.descripcion=descripcion;
    }

    public static Tarot CrearTarot(String nombre, String descripcion,String sobre,String ejemplar,int puntos,double multiplicador){
        if(sobre.equals("carta")){
            return new TarotPoker(nombre, descripcion,ejemplar,puntos,multiplicador);
        } else if (sobre.equals("mano")) {
           return new TarotJugada(nombre, descripcion,ejemplar,puntos,multiplicador);
        }
        throw new SobreNoEncontradoError("Sobre no encontrado");
    }

    public abstract void modificar(ArrayList<? extends ModificablePorTarot> modificablesPorTarot);
}
