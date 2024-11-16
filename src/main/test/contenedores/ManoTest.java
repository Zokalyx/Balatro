package contenedores;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.contenedores.Mazo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class ManoTest {
    @Test
    public void test01UnaManoVaciaSePuedeRepartirUsandoUnMazoYRecibe8Cartas() {
        Mano mano = new Mano();
        Mazo mockMazo = Mockito.mock(Mazo.class);
        when(mockMazo.tomarCarta()).thenReturn(Mockito.mock(Poker.class));

        mano.repartirUsando(mockMazo);

        verify(mockMazo, times(8)).tomarCarta();
    }
}
