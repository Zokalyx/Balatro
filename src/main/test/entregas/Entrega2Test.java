package entregas;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.comodin.*;
import edu.fiuba.algo3.modelo.contenedores.Mazo;
import edu.fiuba.algo3.modelo.contenedores.SinCartasError;
import edu.fiuba.algo3.modelo.jugada.Jugada;
import edu.fiuba.algo3.modelo.jugada.JugadaEscalera;
import edu.fiuba.algo3.modelo.jugada.JugadaNula;
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
import static org.mockito.Mockito.when;

public class Entrega2Test {
    @Test
    public void test01UsarComodinPuntajeAfectaPuntaje() {
        Comodin comodin = new Comodin("A", "B", 0, 8, new ActivacionSiempre());
        Puntaje puntaje = new Puntaje(3, 1);
        ArrayList<Poker> cartas = new ArrayList<>();
        Jugada jugada = new JugadaEscalera(cartas);
        comodin.modificarPuntaje(puntaje, jugada, 0);

        assertEquals(27, puntaje.calcularTotal());
    }

    @Test
    public void test02SePuedenCargarComodinesConJSON() {
        ArrayList<Comodin> cartas = new LectorJson().leerComodines();
        assert(cartas.size() > 10);
    }

    @Test
    public void test03MazoSePuedeCargarConJson() {
        LectorJson lector = new LectorJson();
        ArrayList<Poker> cartas = lector.leerCartasDeMazo();
        Mazo mazo = new Mazo(cartas);
        for (int i = 0; i < 52; i++) {
            mazo.tomarCarta();
        }

        // No debería tener más cartas
        assertThrows(SinCartasError.class, mazo::tomarCarta);
    }

    @Test
    public void  test04multiplicaPor3SiSeJuegaUnaEscalera(){
        Comodin comodin = new Comodin("a", "a", 0, 3, new ActivacionJugada("escalera"));
        Puntaje puntaje = new Puntaje(0, 0);
        ArrayList<Poker> cartas = new ArrayList<>();
        Jugada jugada = new JugadaEscalera(cartas);
        jugada.modificarPuntaje(puntaje);
        comodin.modificarPuntaje(puntaje, jugada, 0);
        assertEquals(210, puntaje.calcularTotal());
    }

    @Test
    public void test05seSuman10PuntosPorDescarte(){
        List<Poker> cartas = Arrays.asList(
                new Poker("A", new Pica(), 10, 0),
                new Poker("A", new Diamante(), 10, 0),
                new Poker("A", new Trebol(), 10, 0),
                new Poker("A", new Corazon(), 10, 0),
                new Poker("7", new Pica(), 7, 0),
                new Poker("7", new Diamante(), 7, 0),
                new Poker("7", new Trebol(), 7, 0),
                new Poker("7", new Corazon(), 7, 0),
                new Poker("4", new Corazon(), 4, 0),
                new Poker("4", new Trebol(), 4, 0)
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

        Comodin comodin = new Comodin("a", "a",  10, 0, new ActivacionDescarte());
        jugador.agregarComodin(comodin);

        jugador.repartirMano();

        jugador.seleccionarCarta(cartas.get(2));
        jugador.seleccionarCarta(cartas.get(3));
        jugador.descartarCartas();

        // Carta alta 5 * 1 + valor de carta (7) + 10 * 2 del comodín por descarte = 32
        jugador.seleccionarCarta(cartas.get(4));
        assertEquals(32, jugador.jugarMano());
    }

    @Test
    public void test06seSuman10PuntosPorDescarte(){
        List<Poker> cartas = Arrays.asList(
                new Poker("A", new Pica(), 10, 0),
                new Poker("A", new Diamante(), 10, 0),
                new Poker("A", new Trebol(), 10, 0),
                new Poker("A", new Corazon(), 10, 0),
                new Poker("7", new Pica(), 7, 0),
                new Poker("7", new Diamante(), 7, 0),
                new Poker("7", new Trebol(), 7, 0),
                new Poker("7", new Corazon(), 7, 0),
                new Poker("4", new Corazon(), 4, 0),
                new Poker("4", new Trebol(), 4, 0)
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

        Comodin comodin = new Comodin("a", "a",  10, 0, new ActivacionDescarte());
        jugador.agregarComodin(comodin);

        jugador.repartirMano();

        jugador.seleccionarCarta(cartas.get(2));
        jugador.seleccionarCarta(cartas.get(3));
        jugador.descartarCartas();

        // Carta alta 5 * 1 + valor de carta (7) + 10 * 2 del comodín por descarte = 32
        jugador.seleccionarCarta(cartas.get(4));
        assertEquals(32, jugador.jugarMano());
    }

    @Test
    public void test07LaProbabilidad1En1000EsAproximadamenteCorrecta(){
        Comodin comodin = new Comodin("a", "a",  1, 0, new ActivacionProbabilidad(1000));

        Puntaje puntaje = new Puntaje(0, 1);
        int N = 1000000;
        for (int i = 0; i < N; i++) {
            comodin.modificarPuntaje(puntaje, new JugadaNula(new ArrayList<>()), 0);
        }

        assertTrue(puntaje.calcularTotal() > 1000 - 100);
        assertTrue(puntaje.calcularTotal() < 1000 + 100);
    }

    @Test
    public void test08SePuedenJugarComodinesConCombinacionesDeEfectos() {
        fail("Not implemented");
    }
}
