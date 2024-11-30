import edu.fiuba.algo3.modelo.palo.Diamante;
import edu.fiuba.algo3.modelo.palo.Pica;
import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.modelo.*;
import static org.junit.jupiter.api.Assertions.*;

public class PokerTest {
    @Test
    public void test01DadaUnaCartaQPuntua10(){
        Pica palo = new Pica();
        Poker carta = new Poker("Reina", palo, 10, 1);
        Puntaje puntaje = new Puntaje(0, 1);
        carta.modificarPuntaje(puntaje);

        assertEquals(10, puntaje.calcularTotal());
    }

    @Test
    public void test02DosCartasPuedenSerDelMismoPalo() {
        Poker carta1 = new Poker("Reina", new Pica(), 1, 1);
        Poker carta2 = new Poker("Rey", new Pica(), 1, 1);

        assertTrue(carta1.esMismoPaloQue(carta2));
    }

    @Test
    public void test02DosCartasPuedenSerDeDistintoPalo() {
        Poker carta1 = new Poker("Reina", new Pica(), 1, 1);
        Poker carta2 = new Poker("Rey", new Diamante(), 1, 1);

        assertFalse(carta1.esMismoPaloQue(carta2));
    }

    @Test
    public void test03DosCartasPuedenTenerElMismoSimbolo() {
        Poker carta1 = new Poker("Reina", new Pica(), 1, 1);
        Poker carta2 = new Poker("Reina", new Diamante(), 1, 1);

        assertTrue(carta1.esMismoSimboloQue(carta2));
    }

    @Test
    public void test04DosCartasPuedenTenerSimbolosDistintos() {
        Poker carta1 = new Poker("Reina", new Pica(), 1, 1);
        Poker carta2 = new Poker("Rey", new Diamante(), 1, 1);

        assertFalse(carta1.esMismoSimboloQue(carta2));
    }

    @Test
    public void test05LaJotaEsSiguienteDel10() {
        Poker carta1 = new Poker("Jota", new Pica(), 1, 1);
        Poker carta2 = new Poker("10", new Diamante(), 1, 1);

        assertTrue(carta2.esSimboloAnteriorA(carta1));
    }

    @Test
    public void test06LaReinaEsSiguienteAJota() {
        Poker carta1 = new Poker("Reina", new Pica(), 1, 1);
        Poker carta2 = new Poker("Jota", new Diamante(), 1, 1);

        assertTrue(carta2.esSimboloAnteriorA(carta1));
    }

    @Test
    public void test07ElReyEsSiguienteALaReina() {
        Poker carta1 = new Poker("Rey", new Pica(), 1, 1);
        Poker carta2 = new Poker("Reina", new Diamante(), 1, 1);

        assertTrue(carta2.esSimboloAnteriorA(carta1));
    }

    @Test
    public void test07ElAsEsSiguienteAlRey() {
        Poker carta1 = new Poker("As", new Pica(), 1, 1);
        Poker carta2 = new Poker("Rey", new Diamante(), 1, 1);

        assertTrue(carta2.esSimboloAnteriorA(carta1));
    }

    @Test
    public void test08ElDosEsSiguienteAlAs() {
        Poker carta1 = new Poker("2", new Pica(), 1, 1);
        Poker carta2 = new Poker("As", new Diamante(), 1, 1);

        assertTrue(carta2.esSimboloAnteriorA(carta1));
    }
}