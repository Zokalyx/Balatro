package edu.fiuba.algo3.modelo.palo;

public abstract class Palo {
    @Override
    public boolean equals(Object obj) {
        return this.getClass().equals(obj.getClass());
    }
}
