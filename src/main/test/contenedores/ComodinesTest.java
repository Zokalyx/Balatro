package contenedores;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.comodin.ComodinPuntaje;
import edu.fiuba.algo3.modelo.contenedores.Comodines;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ComodinesTest {
    @Test
    public void test01SePuedenCargarComodines() {
        ArrayList<Comodin> cartas = new LectorJson().leerComodines();
        assert(cartas.size() > 10);

        Comodines comodines = new Comodines(cartas);
    }

    @Test
    public void test02SePuedenAgregarComodinesIndividuales() {
        Comodines comodines = new Comodines(new ArrayList<>());
        comodines.agregar(new ComodinPuntaje("A", "B", 1, 8));
        comodines.agregar(new ComodinPuntaje("C", "D", 2, 10));
        Puntaje puntaje = new Puntaje(0, 1);

        comodines.modificarPuntaje(puntaje);

        // 3 * 19
        assertEquals(57, puntaje.calcularTotal());
    }
}
