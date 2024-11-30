package casosDeUso;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.contenedores.Mazo;
import edu.fiuba.algo3.modelo.jugada.Jugada;
import edu.fiuba.algo3.modelo.palo.Corazon;
import edu.fiuba.algo3.modelo.palo.Diamante;
import edu.fiuba.algo3.modelo.palo.Pica;
import edu.fiuba.algo3.modelo.palo.Trebol;
import edu.fiuba.algo3.modelo.tarot.ActivacionTarotPokerCualquiera;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class Entrega1Test {
    @Test
    public void test01JugadorPoseeCartasSuficientesEnElMazoParaEmpezarElJuego() {
        LectorJson lector = new LectorJson();
        ArrayList<Poker> cartas = lector.leerMazo();
        Mazo<Poker> mazo = new Mazo<>(cartas);
        Mano mano = new Mano(mazo);

        assertDoesNotThrow(mano::repartir);
    }

    @Test
    public void test02UnaManoVaciaSePuedeRepartirUsandoUnMazoYRecibe8Cartas() {

        Mazo<Poker> mockMazo = Mockito.mock(Mazo.class);
        when(mockMazo.tomarCarta()).thenReturn(Mockito.mock(Poker.class));
        Mano mano = new Mano(mockMazo);
        mano.repartir();

        verify(mockMazo, times(8)).tomarCarta();
    }

    @Test
    public void test03JugadorPuedeJugarSuMano() {
        // Arrange
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

        Mano mano = new Mano(mazoMock);
        mano.repartir();

        // Par de Aces
        mano.seleccionarCarta(cartas.get(0));
        mano.seleccionarCarta(cartas.get(1));

        // Act
        Jugada jugada = mano.jugar();
        Puntaje puntaje = new Puntaje(0, 1);
        // Par 10 x 2
        // As 10 x 1
        // As 10 x 1
        jugada.modificarPuntaje(puntaje);
        int puntajeFinal = puntaje.calcularTotal();

        // Assert
        assertEquals(60, puntajeFinal);
    }

    @Test
    public void test04ElOrdenAfectaLaPuntuacionDeCartas() {
        // Arrange
        List<Poker> cartas = Arrays.asList(
                mock(Poker.class),
                mock(Poker.class),
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

        // Forzar que las primeras cartas hagan un par
        when(cartas.get(0).esMismoSimboloQue(cartas.get(1))).thenReturn(true);
        when(cartas.get(1).esMismoSimboloQue(cartas.get(0))).thenReturn(true);
        Mano mano = new Mano(mazoMock);
        mano.repartir();
        mano.seleccionarCarta(cartas.get(0));
        mano.seleccionarCarta(cartas.get(1));

        Jugada jugada = mano.jugar();
        Puntaje puntaje = new Puntaje(0, 1);
        jugada.modificarPuntaje(puntaje);

        InOrder inOrder = inOrder(cartas.get(0), cartas.get(1));
        inOrder.verify(cartas.get(0)).modificarPuntaje(any());
        inOrder.verify(cartas.get(1)).modificarPuntaje(any());
    }

    @Test
    public void test05AplicarTarotModificaElValorNumericoDeCarta() {
        Poker carta = new Poker("7", new Diamante(), 7, 1);
        Tarot tarot = new Tarot("La Torre", "Mejora 1 carta seleccionada y la convierte en una carta de piedra.",50,1, new ActivacionTarotPokerCualquiera());
        tarot.modificar(carta);
        Puntaje puntaje = new Puntaje(0, 1);

        carta.modificarPuntaje(puntaje);

        assertEquals(50, puntaje.calcularTotal());
    }

    @Test
    public void test06AplicarTarotModificaElMultiplicadorDeCarta() {
        Poker carta = new Poker("7", new Diamante(), 7, 1);
        Tarot tarot = new Tarot("Justicia", "Mejora 1 carta seleccionada y la convierte en una carta de cristal", 1, 2, new ActivacionTarotPokerCualquiera());
        tarot.modificar(carta);
        Puntaje puntaje = new Puntaje(0, 1);

        carta.modificarPuntaje(puntaje);

        assertEquals(14, puntaje.calcularTotal());
    }

}
