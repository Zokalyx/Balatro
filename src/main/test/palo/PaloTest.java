package palo;

import edu.fiuba.algo3.modelo.palo.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaloTest {
    @Test
    public void test01UnaInstanciaDeUnPaloEspecificoEsIgualAOtraInstanciaDelMismo() {
        Palo palo1 = new Corazon();
        Palo palo2 = new Corazon();

        assertEquals(palo1, palo2);
    }

    @Test
    public void test02UnaInstanciaDeUnPaloEspecificoEsDistintoAOtroPalo() {
        Palo palo1 = new Corazon();
        Palo palo2 = new Diamante();

        assertNotEquals(palo1, palo2);
    }
}
