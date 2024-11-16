package entrega2;

import edu.fiuba.algo3.modelo.LectorJson;
import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.comodin.ComodinPuntaje;
import edu.fiuba.algo3.modelo.contenedores.Comodines;
import edu.fiuba.algo3.modelo.contenedores.Mazo;
import edu.fiuba.algo3.modelo.contenedores.SinCartasError;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Entrega2Test {
    @Test
    public void test01UsarComodinPuntajeAfectaPuntaje() {
        Comodin comodin = new ComodinPuntaje("A", "B", 0, 8);
        Puntaje puntaje = new Puntaje(1, 3);

        comodin.modificarPuntaje(puntaje);

        assertEquals(27, puntaje.calcularTotal());
    }

    @Test
    public void test02SePuedenCargarComodinesConJSON() {
        ArrayList<Comodin> cartas = new LectorJson().leerComodines();
        assert(cartas.size() > 10);

        Comodines comodines = new Comodines(cartas);
    }

    @Test
    public void test03MazoSePuedeCargarConJson() {
        LectorJson lector = new LectorJson();
        ArrayList<Poker> cartas = lector.leerCartasDeMazo();
        Mazo mazo = new Mazo(cartas);
        for (int i = 0; i < 52; i++) {
            mazo.tomarCarta();
        }

        // No debería tener más cartas
        assertThrows(SinCartasError.class, mazo::tomarCarta);
    }
}
