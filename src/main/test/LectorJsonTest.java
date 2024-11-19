import edu.fiuba.algo3.modelo.LectorJson;
import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.comodin.Comodin;
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
}
