package contenedores;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.comodin.ActivacionSiempre;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.comodin.ComodinIndividual;
import edu.fiuba.algo3.modelo.contenedores.Comodines;
import edu.fiuba.algo3.modelo.contenedores.ComodinesLlenoError;
import edu.fiuba.algo3.modelo.jugada.JugadaNula;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ComodinesTest {
    @Test
    public void test01SePuedenAgregarYUsarComodinesIndividuales() {
        Comodines comodines = new Comodines();
        comodines.agregar(new ComodinIndividual("A", "B", 1, 2, new ActivacionSiempre()));
        comodines.agregar(new ComodinIndividual("C", "D", 3, 4, new ActivacionSiempre()));
        Puntaje puntaje = new Puntaje(0, 0);
        comodines.modificarPuntaje(puntaje, new JugadaNula(new ArrayList<>()), 0);


        assertEquals(4 * 6, puntaje.calcularTotal());
    }

    @Test
    public void test02NoSePuedenAgregarMasDeCincoComodines() {
        Comodines comodines = new Comodines();
        Comodin comodin = new ComodinIndividual("A", "B", 1, 8, new ActivacionSiempre());
        for (int i = 0; i < 5; i++) {
            comodines.agregar(comodin);
        }

        assertThrows(ComodinesLlenoError.class, () -> comodines.agregar(comodin));
    }
}
