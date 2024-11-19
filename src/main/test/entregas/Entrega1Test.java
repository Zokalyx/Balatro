package entregas;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.contenedores.Mazo;
import edu.fiuba.algo3.modelo.palo.Corazon;
import edu.fiuba.algo3.modelo.palo.Diamante;
import edu.fiuba.algo3.modelo.palo.Pica;
import edu.fiuba.algo3.modelo.palo.Trebol;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

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
        ArrayList<Poker> cartas = lector.leerCartasDeMazo();
        Mazo mazo = new Mazo(cartas);
        Jugador jugador = new Jugador(mazo);

        assertDoesNotThrow(jugador::repartirMano);
    }

    @Test
    public void test02UnaManoVaciaSePuedeRepartirUsandoUnMazoYRecibe8Cartas() {

        Mazo mockMazo = Mockito.mock(Mazo.class);
        when(mockMazo.tomarCarta()).thenReturn(Mockito.mock(Poker.class));
        Mano mano = new Mano(mockMazo);
        mano.repartir();

        verify(mockMazo, times(8)).tomarCarta();
    }

    @Test
    public void test03JugadorPuedeJugarSuMano() {
        // Arrange
        List<Poker> cartas = Arrays.asList(
                new Poker("A", new Pica(), 10, 0),
                new Poker("A", new Diamante(), 10, 0),
                new Poker("A", new Trebol(), 10, 0),
                new Poker("A", new Corazon(), 10, 0),
                new Poker("7", new Pica(), 7, 0),
                new Poker("7", new Diamante(), 7, 0),
                new Poker("7", new Trebol(), 7, 0),
                new Poker("7", new Corazon(), 7, 0)
        );
        AtomicInteger index = new AtomicInteger(0);
        Mazo mazoMock = Mockito.mock(Mazo.class);
        when(mazoMock.tomarCarta()).thenAnswer(new Answer<Carta>() {
            @Override
            public Carta answer(InvocationOnMock invocation) {
                int currentIndex = index.getAndIncrement();
                return cartas.get(currentIndex);
            }
        });

        Jugador jugador = new Jugador(mazoMock);
        jugador.repartirMano();

        // Par de Aces
        jugador.seleccionarCarta(cartas.get(0));
        jugador.seleccionarCarta(cartas.get(1));

        // Act
        int puntajeFinal = jugador.jugarMano();

        // Assert
        assertEquals(60, puntajeFinal);
    }

    @Test
    public void test04ElOrdenAfectaLaPuntuacionDeCartas() {
        //por ahora esta prueba no hace nada, cuando incorporemos los comodines si va a haber diferencia

        // Arrange
        List<Poker> cartas = Arrays.asList(
                new Poker("A", new Pica(), 10, 0),
                new Poker("A", new Diamante(), 10, 0),
                new Poker("A", new Trebol(), 10, 0),
                new Poker("A", new Corazon(), 10, 0),
                new Poker("7", new Pica(), 7, 0),
                new Poker("7", new Diamante(), 7, 0),
                new Poker("7", new Trebol(), 7, 0),
                new Poker("7", new Corazon(), 7, 0)
        );
        AtomicInteger index = new AtomicInteger(0);
        Mazo mazoMock = Mockito.mock(Mazo.class);
        when(mazoMock.tomarCarta()).thenAnswer(new Answer<Carta>() {
            @Override
            public Carta answer(InvocationOnMock invocation) {
                int currentIndex = index.getAndIncrement();
                return cartas.get(currentIndex);
            }
        });
        Jugador jugador = new Jugador(mazoMock);
        jugador.repartirMano();
        // Par de Aces
        jugador.seleccionarCarta(cartas.get(0));
        jugador.seleccionarCarta(cartas.get(1));

        // Act
        int puntajeFinal = jugador.jugarMano();

        // Assert
        assertEquals(60, puntajeFinal);

    }

    @Test
    public void test05AplicarTarotModificaElValorNumericoDeCarta() {
        Poker carta = new Poker("7", new Diamante(), 7, 1);
        Tarot tarot = Tarot.CrearTarot("La Torre", "Mejora 1 carta seleccionada y la convierte en una carta de piedra.","carta","cualquiera",50,1);
        ArrayList<Poker> pokers = new ArrayList<>();
        pokers.add(carta);
        tarot.modificar(pokers);

        assertEquals(carta.usar(), 50);
    }

    @Test
    public void test06AplicarTarotModificaElMultiplicadorDeCarta() {
        Poker carta = new Poker("7", new Diamante(), 7, 1);
        Tarot tarot = Tarot.CrearTarot("Justicia", "Mejora 1 carta seleccionada y la convierte en una carta de cristal", "carta", "cualquiera", 1, 2);
        ArrayList<Poker> pokers = new ArrayList<>();
        pokers.add(carta);
        tarot.modificar(pokers);

        assertEquals(carta.usar(), 14);
    }

}
