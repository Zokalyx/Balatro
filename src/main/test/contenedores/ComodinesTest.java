package contenedores;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.comodin.ActivacionComodinSiempre;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.comodin.ComodinIndividual;
import edu.fiuba.algo3.modelo.contenedores.Comodines;
import edu.fiuba.algo3.modelo.contenedores.ComodinesLlenoError;
import edu.fiuba.algo3.modelo.jugada.JugadaCartaAlta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ComodinesTest {
    @Test
    public void test01SePuedenAgregarYUsarComodinesIndividuales() {
        Comodines comodines = new Comodines();
        comodines.agregar(new ComodinIndividual("A", "B", 1, 2, new ActivacionComodinSiempre()));
        comodines.agregar(new ComodinIndividual("C", "D", 3, 4, new ActivacionComodinSiempre()));
        Puntaje puntaje = new Puntaje(0, 1);
        comodines.modificarPuntaje(puntaje, new JugadaCartaAlta(), 0);


        // Comodin 1 x 2
        // Comodin 3 x 4
        // Total 4 x 7
        assertEquals(4 * 7, puntaje.calcularTotal());
    }

    @Test
    public void test02NoSePuedenAgregarMasDeCincoComodines() {
        Comodines comodines = new Comodines();
        Comodin comodin = new ComodinIndividual("A", "B", 1, 8, new ActivacionComodinSiempre());
        for (int i = 0; i < 5; i++) {
            comodines.agregar(comodin);
        }

        assertThrows(ComodinesLlenoError.class, () -> comodines.agregar(comodin));
    }
}
