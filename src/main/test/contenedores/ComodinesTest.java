package contenedores;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.comodin.ComodinPuntaje;
import edu.fiuba.algo3.modelo.contenedores.Comodines;
import edu.fiuba.algo3.modelo.contenedores.ComodinesLlenoError;
import edu.fiuba.algo3.modelo.jugada.Jugada;
import edu.fiuba.algo3.modelo.jugada.JugadaEscalera;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ComodinesTest {
    @Test
    public void test01SePuedenAgregarYUsarComodinesIndividuales() {
        Comodines comodines = new Comodines();
        comodines.agregar(new ComodinPuntaje("A", "B", 1, 8));
        comodines.agregar(new ComodinPuntaje("C", "D", 2, 10));
        Puntaje puntaje = new Puntaje(0, 1);
        ArrayList<Poker> cartas = new ArrayList<>();
        Jugada jugada = new JugadaEscalera(cartas);
        comodines.modificarPuntaje(puntaje, jugada, 0);

        // 3 * 19
        assertEquals(57, puntaje.calcularTotal());
    }

    @Test
    public void test02NoSePuedenAgregarMasDeCincoComodines() {
        Comodines comodines = new Comodines();
        Comodin comodin = new ComodinPuntaje("A", "B", 1, 8);
        for (int i = 0; i < 5; i++) {
            comodines.agregar(comodin);
        }

        assertThrows(ComodinesLlenoError.class, () -> comodines.agregar(comodin));
    }
}
