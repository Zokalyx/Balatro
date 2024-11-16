import edu.fiuba.algo3.modelo.palo.Corazon;
import edu.fiuba.algo3.modelo.palo.Pica;
import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.modelo.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokerTest {
    @Test
    public void test01DadaUnaCartaQPuntua10(){
        Pica palo = new Pica();
        Poker carta = new Poker("Q", palo, 10, 1);
        int puntajeEsperado = 10;
        int puntajeObtenido = carta.usar();
        assertEquals(puntajeEsperado, puntajeObtenido);
    }
    @Test
    public void test02DadaUnaCartaDeDiamanteSabeIndicarSuPalo(){
        Corazon palo = new Corazon();
        Poker carta = new Poker("Q", palo, 0, 10);
        assert(palo.sonDelMismoPalo(carta));
    }
}