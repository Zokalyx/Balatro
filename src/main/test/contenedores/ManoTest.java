package contenedores;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.contenedores.DescartesInsuficientesError;
import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.contenedores.Mazo;
import edu.fiuba.algo3.modelo.contenedores.SeleccionInvalidaError;
import edu.fiuba.algo3.modelo.jugada.JugadaManager;
import edu.fiuba.algo3.modelo.palo.Corazon;
import edu.fiuba.algo3.modelo.palo.Diamante;
import edu.fiuba.algo3.modelo.palo.Pica;
import edu.fiuba.algo3.modelo.palo.Trebol;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ManoTest {
    @Test
    public void test01UnaManoVaciaSePuedeRepartirUnMazoYRecibe8Cartas() {
        Mazo mockMazo = Mockito.mock(Mazo.class);
        when(mockMazo.tomarCarta()).thenReturn(Mockito.mock(Poker.class));
        Mano mano = new Mano(mockMazo, new JugadaManager());
        mano.repartir();

        verify(mockMazo, times(8)).tomarCarta();
    }

    @Test
    public void test02NoSePuedenSeleccionarMasDe5Cartas() {
        List<Poker> cartas = Arrays.asList(
                new Poker("As", new Pica(), 10, 1),
                new Poker("As", new Diamante(), 10, 1),
                new Poker("As", new Trebol(), 10, 1),
                new Poker("As", new Corazon(), 10, 1),
                new Poker("7", new Pica(), 7, 1),
                new Poker("7", new Diamante(), 7, 1),
                new Poker("7", new Trebol(), 7, 1),
                new Poker("7", new Corazon(), 7, 1)
        );
        AtomicInteger index = new AtomicInteger(0);
        Mazo<Poker> mazoMock = Mockito.mock(Mazo.class);
        when(mazoMock.tomarCarta()).thenAnswer(invocation -> {
            int currentIndex = index.getAndIncrement();
            return cartas.get(currentIndex);
        });
        Mano mano = new Mano(mazoMock, new JugadaManager());
        mano.repartir();

        mano.seleccionarCarta(cartas.get(0));
        mano.seleccionarCarta(cartas.get(1));
        mano.seleccionarCarta(cartas.get(2));
        mano.seleccionarCarta(cartas.get(3));
        mano.seleccionarCarta(cartas.get(4));

        assertThrows(SeleccionInvalidaError.class, () -> mano.seleccionarCarta(cartas.get(5)));
    }

    @Test
    public void test03SePuedeRecargarElMazoConCartasDescartadas() {
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker("As", new Pica(), 10, 1));
        cartas.add(new Poker("As", new Pica(), 10, 1));
        cartas.add(new Poker("As", new Pica(), 10, 1));
        cartas.add(new Poker("As", new Pica(), 10, 1));
        cartas.add(new Poker("As", new Pica(), 10, 1));
        cartas.add(new Poker("As", new Pica(), 10, 1));
        cartas.add(new Poker("As", new Pica(), 10, 1));
        cartas.add(new Poker("As", new Pica(), 10, 1));
        ArrayList<Poker> cartasMazo = new ArrayList<>(cartas);

        Mano mano = new Mano(new Mazo<>(cartasMazo), new JugadaManager());
        mano.repartir();

        mano.seleccionarCarta(cartas.get(0));
        mano.seleccionarCarta(cartas.get(1));
        mano.seleccionarCarta(cartas.get(2));
        mano.seleccionarCarta(cartas.get(3));
        mano.seleccionarCarta(cartas.get(4));

        mano.jugar();
        mano.retornarCartasAMazo();

        assertDoesNotThrow(mano::repartir);
    }
}
