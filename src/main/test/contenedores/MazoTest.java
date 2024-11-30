package contenedores;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.contenedores.Mazo;
import edu.fiuba.algo3.modelo.contenedores.SinCartasError;
import edu.fiuba.algo3.modelo.palo.Trebol;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MazoTest {

    @Test
    public void test01MazoSePuedeCargarConJson() {
        LectorJson lector = new LectorJson();
        ArrayList<Poker> cartas = lector.leerMazo();
        Mazo<Poker> mazo = new Mazo<>(cartas);
        for (int i = 0; i < 52; i++) {
            mazo.tomarCarta();
        }

        // No debería tener más cartas
        assertThrows(SinCartasError.class, mazo::tomarCarta);
    }

    @Test
    public void test02SePuedenAgregarCartasAlMazoLuegoDeSuCreacion() {
        Mazo<Poker> mazo = new Mazo<>(new ArrayList<>());
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker("As", new Trebol(), 0, 1));
        mazo.agregar(cartas);

        Poker carta = mazo.tomarCarta();

        assertEquals(carta, cartas.get(0));
    }
}
