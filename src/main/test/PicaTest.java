import edu.fiuba.algo3.modelo.palo.Corazon;
import edu.fiuba.algo3.modelo.palo.Pica;
import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.modelo.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PicaTest {
    @Test
    public void test01DadaUnaCartaDePicaEsDelMismoPalo(){
        Pica pica = new Pica();
        Poker carta = new Poker(1,"Q",10, pica);
        assert(pica.sonDelMismoPalo(carta));
    }

    @Test
    public void test02DadaUnaCartaDeCorazonEsDeOtroPalo(){
        Corazon corazon = new Corazon();
        Pica pica = new Pica();
        Poker carta = new Poker(1,"Q",10, corazon);
        assertFalse(pica.sonDelMismoPalo(carta));
    }
}
