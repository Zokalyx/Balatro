import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.comodin.ComodinPuntaje;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ComodinTest {
    @Test
    public void test01UsarComodinPuntajeAfectaPuntaje() {
        Comodin comodin = new ComodinPuntaje("A", "B", 0, 8);
        Puntaje puntaje = new Puntaje(1, 3);

        comodin.modificarPuntaje(puntaje);

        assertEquals(27, puntaje.calcularTotal());
    }
}
