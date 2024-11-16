package palo;

import edu.fiuba.algo3.modelo.palo.Corazon;
import edu.fiuba.algo3.modelo.palo.Pica;
import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.modelo.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CorazonTest {
    @Test
    public void test01DadaUnaCartaDeCorazonEsDelMismoPalo(){
        Corazon corazon = new Corazon();
        Poker carta = new Poker("Q", corazon, 10, 1);
        assert(corazon.sonDelMismoPalo(carta));
    }
    @Test
    public void test02DadaUnaCartaDePicaEsDeOtroPalo(){
        Pica pica = new Pica();
        Corazon corazon = new Corazon();
        Poker carta = new Poker("Q", pica, 10, 1);
        assertFalse(corazon.sonDelMismoPalo(carta));
    }
}
