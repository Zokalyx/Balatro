package palo;

import edu.fiuba.algo3.modelo.palo.Diamante;
import edu.fiuba.algo3.modelo.palo.Pica;
import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.modelo.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DiamanteTest {
    @Test
    public void test01DadaUnaCartaDeDiamanteEsDelMismoPalo(){
        Diamante diamante = new Diamante();
        Poker carta = new Poker("Q", diamante, 10, 1);
        assert(diamante.sonDelMismoPalo(carta));
    }

    @Test
    public void test02DadaUnaCartaDePicaEsDeOtroPalo(){
        Pica pica = new Pica();
        Diamante diamante = new Diamante();
        Poker carta = new Poker("Q", pica, 10, 1);
        assertFalse(diamante.sonDelMismoPalo(carta));
    }
}

