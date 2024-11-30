import edu.fiuba.algo3.modelo.LectorJson;
import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.jugada.JugadaCartaAlta;
import edu.fiuba.algo3.modelo.palo.Trebol;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class LectorJsonTest {
    @Test
    public void test01SePuedeLeerCartasDelMazo() {
        LectorJson lectorJson = new LectorJson();
        ArrayList<Poker> cartas = lectorJson.leerMazo();

        assertEquals(52, cartas.size());
    }

    @Test
    public void test02SePuedeLeerComodines() {
        LectorJson lectorJson = new LectorJson();
        ArrayList<Comodin> cartas = lectorJson.leerComodines();

        assertEquals(33, cartas.size());
    }

    @Test
    public void test03SePuedeLeerTarots() {
        LectorJson lectorJson = new LectorJson();
        ArrayList<Tarot> cartas = lectorJson.leerTarots();

        assertEquals(15, cartas.size());
    }

    @Test
    public void test04LosPokersSeLeenCorrectamente() {
        LectorJson lectorJson = new LectorJson();
        ArrayList<Poker> cartas = lectorJson.leerMazo();

        Poker carta = cartas.get(0);
        Poker cartaComparacion = new Poker("As", new Trebol(), 10, 1);
        Puntaje puntaje = new Puntaje(0, 0);
        carta.modificarPuntaje(puntaje);

        assertTrue(carta.esMismoPaloQue(cartaComparacion));
        assertTrue(carta.esMismoSimboloQue(cartaComparacion));
        assertEquals(10, puntaje.calcularTotal());
    }

    @Test
    public void test05LosComodinesSeLeenCorrectamente() {
        LectorJson lectorJson = new LectorJson();
        ArrayList<Comodin> cartas = lectorJson.leerComodines();

        Comodin carta = cartas.get(0);  // Comodín descarte 30 x 1
        Puntaje puntaje = new Puntaje(0, 0);
        carta.modificarPuntaje(puntaje, new JugadaCartaAlta(), 2);  // 2 descartes

        assertEquals(120, puntaje.calcularTotal());
    }

    @Test
    public void test06LosTarotSeLeenCorrectamente() {
        LectorJson lectorJson = new LectorJson();
        ArrayList<Tarot> cartas = lectorJson.leerTarots();

        Tarot tarot = cartas.get(0);  // Tarot sobre carta: agrega 10 x 2
        Poker poker = new Poker("As", new Trebol(), 10, 1);

        tarot.modificar(poker);

        Puntaje puntaje = new Puntaje(0, 0);
        poker.modificarPuntaje(puntaje);
        assertEquals(20, puntaje.calcularTotal());
    }
}
