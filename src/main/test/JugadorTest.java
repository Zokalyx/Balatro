import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.contenedores.Mazo;
import edu.fiuba.algo3.modelo.palo.Corazon;
import edu.fiuba.algo3.modelo.palo.Diamante;
import edu.fiuba.algo3.modelo.palo.Pica;
import edu.fiuba.algo3.modelo.palo.Trebol;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class JugadorTest {

    @Test
    public void test01JugadorPoseeCartasSuficientesEnElMazoParaEmpezarElJuego() {
        LectorJson lector = new LectorJson();
        ArrayList<Poker> cartas = lector.leerCartasDeMazo();
        Mazo mazo = new Mazo(cartas);
        Jugador jugador = new Jugador(mazo);

        assertDoesNotThrow(jugador::repartirMano);
    }

    @Test
    public void test02JugadorObtiene0PuntosSiJuegaSinSeleccionarCartas() {
        LectorJson lector = new LectorJson();
        ArrayList<Poker> cartas = lector.leerCartasDeMazo();
        Mazo mazo = new Mazo(cartas);
        Jugador jugador = new Jugador(mazo);
        jugador.repartirMano();

        // En el futuro, esto no estará permitido
        // Ya que es una jugada nula.
        int puntajeFinal = jugador.jugarMano();

        assertEquals(puntajeFinal, 0);
    }

    @Test
    public void test03JugadorPuedeJugarSuMano() {
        // Arrange
        List<Poker> cartas = Arrays.asList(
                new Poker(0, "A", 10, new Pica()),
                new Poker(0, "A", 10, new Diamante()),
                new Poker(0, "A", 10, new Trebol()),
                new Poker(0, "A", 10, new Corazon()),
                new Poker(0, "7", 7, new Pica()),
                new Poker(0, "7", 7, new Diamante()),
                new Poker(0, "7", 7, new Trebol()),
                new Poker(0, "7", 7, new Corazon())
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
    public void test05ElOrdenAfectaLaPuntuacionDeCartas() {
        //por ahora esta prueba no hace nada, cuando incorporemos los comodines si va a haber diferencia

        // Arrange
        List<Poker> cartas = Arrays.asList(
                new Poker(0, "A", 10, new Pica()),
                new Poker(0, "A", 10, new Diamante()),
                new Poker(0, "A", 10, new Trebol()),
                new Poker(0, "A", 10, new Corazon()),
                new Poker(0, "7", 7, new Pica()),
                new Poker(0, "7", 7, new Diamante()),
                new Poker(0, "7", 7, new Trebol()),
                new Poker(0, "7", 7, new Corazon())
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
}
