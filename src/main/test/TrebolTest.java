import edu.fiuba.algo3.modelo.palo.Pica;
import edu.fiuba.algo3.modelo.palo.Trebol;
import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.modelo.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TrebolTest {
    @Test
    public void test01DadaUnaCartaDeTrebolEsDelMismoPalo(){
        Trebol trebol = new Trebol();
        Poker carta = new Poker(1,"Q",10, trebol);
        assert(trebol.sonDelMismoPalo(carta));
    }
    @Test
    public void test02DadaUnaCartaDePicaEsDeOtroPalo(){
        Pica pica = new Pica();
        Trebol trebol = new Trebol();
        Poker carta = new Poker(1,"Q",10, pica);
        assertFalse(trebol.sonDelMismoPalo(carta));
    }
}
