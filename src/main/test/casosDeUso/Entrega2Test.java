package casosDeUso;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.comodin.*;
import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.contenedores.Mazo;
import edu.fiuba.algo3.modelo.contenedores.SinCartasError;
import edu.fiuba.algo3.modelo.jugada.*;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class Entrega2Test {
    @Test
    public void test01UsarComodinPuntajeAfectaPuntaje() {
        Comodin comodin = new ComodinIndividual("A", "B", 0, 8, new ActivacionComodinSiempre());
        Puntaje puntaje = new Puntaje(3, 1);
        comodin.modificarPuntaje(puntaje, new JugadaCartaAlta(), 0);

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
        ArrayList<Poker> cartas = lector.leerMazo();
        Mazo<Poker> mazo = new Mazo<>(cartas);
        for (int i = 0; i < 52; i++) {
            mazo.tomarCarta();
        }

        // No debería tener más cartas
        assertThrows(SinCartasError.class, mazo::tomarCarta);
    }

    @Test
    public void  test04multiplicaPor3SiSeJuegaUnaEscalera(){
        Comodin comodin = new ComodinIndividual("a", "a", 0, 3, new ActivacionComodinJugada(new JugadaEscalera()));
        Puntaje puntaje = new Puntaje(2, 1);
        Jugada jugada = new JugadaEscalera();
        comodin.modificarPuntaje(puntaje, jugada, 0);
        assertEquals(8, puntaje.calcularTotal());
    }

    @Test
    public void test05seSuman10PuntosPorDescarteDisponible(){
        List<Poker> cartas = Arrays.asList(
                new Poker("A", new Pica(), 10, 1),
                new Poker("A", new Diamante(), 10, 1),
                new Poker("A", new Trebol(), 10, 1),
                new Poker("A", new Corazon(), 10, 1),
                new Poker("7", new Pica(), 7, 1),
                new Poker("7", new Diamante(), 7, 1),
                new Poker("7", new Trebol(), 7, 1),
                new Poker("7", new Corazon(), 7, 1),
                new Poker("4", new Corazon(), 4, 1),
                new Poker("4", new Trebol(), 4, 1)
        );
        AtomicInteger index = new AtomicInteger(0);
        Mazo<Poker> mazoMock = Mockito.mock(Mazo.class);
        when(mazoMock.tomarCarta()).thenAnswer(invocation -> {
            int currentIndex = index.getAndIncrement();
            return cartas.get(currentIndex);
        });
        Mano mano = new Mano(mazoMock, new JugadaManager());

        Comodin comodin = new ComodinIndividual("a", "a",  10, 1, new ActivacionComodinDescarte());

        mano.repartir();

        int descartesDisponibles = 3;

        mano.seleccionarCarta(cartas.get(2));
        mano.seleccionarCarta(cartas.get(3));
        mano.descartar();
        descartesDisponibles--;

        mano.seleccionarCarta(cartas.get(4));

        Puntaje puntaje = new Puntaje(0, 1);
        Jugada jugada = mano.jugar();
        jugada.modificarPuntaje(puntaje);
        comodin.modificarPuntaje(puntaje, jugada, descartesDisponibles);

        // Comodin 10 x 1 + 10 x 1 (doble activacion)
        // Carta alta 5 x 1
        // Carta 7 x 1
        // Total 32 x 3
        assertEquals(96, puntaje.calcularTotal());
    }

    @Test
    public void test06seSuman10PuntosPorDescarteDisponible(){
        List<Poker> cartas = Arrays.asList(
                new Poker("A", new Pica(), 10, 1),
                new Poker("A", new Diamante(), 10, 1),
                new Poker("A", new Trebol(), 10, 1),
                new Poker("A", new Corazon(), 10, 1),
                new Poker("7", new Pica(), 7, 1),
                new Poker("7", new Diamante(), 7, 1),
                new Poker("7", new Trebol(), 7, 1),
                new Poker("7", new Corazon(), 7, 1),
                new Poker("4", new Corazon(), 4, 1),
                new Poker("4", new Trebol(), 4, 1)
        );
        AtomicInteger index = new AtomicInteger(0);
        Mazo<Poker> mazoMock = Mockito.mock(Mazo.class);
        when(mazoMock.tomarCarta()).thenAnswer(invocation -> {
            int currentIndex = index.getAndIncrement();
            return cartas.get(currentIndex);
        });
        Mano mano = new Mano(mazoMock, new JugadaManager());
        mano.repartir();

        Comodin comodin = new ComodinIndividual("a", "a",  10, 1, new ActivacionComodinDescarte());

        int descartesDisponibles = 3;

        mano.seleccionarCarta(cartas.get(2));
        mano.seleccionarCarta(cartas.get(3));
        mano.descartar();
        descartesDisponibles--;

        mano.seleccionarCarta(cartas.get(4));
        Puntaje puntaje = new Puntaje(0, 1);
        Jugada jugada = mano.jugar();
        jugada.modificarPuntaje(puntaje);
        comodin.modificarPuntaje(puntaje, jugada, descartesDisponibles);

        assertEquals(96, puntaje.calcularTotal());
    }

    @Test
    public void test08SePuedenJugarComodinesConCombinacionesDeEfectos() {
        Comodin comodinJugada = new ComodinIndividual("Jugada", "solo en escalera", 10, 1, new ActivacionComodinJugada(new JugadaEscalera()));
        Comodin comodinSiempre = new ComodinIndividual("Siempre", "siempre se activa", 0, 2, new ActivacionComodinSiempre());
        Comodin comodinJugada2 = new ComodinIndividual("Jugada", "solo en flush", 100, 100, new ActivacionComodinJugada(new JugadaColor()));
        ArrayList<Comodin> comodines = new ArrayList<>();
        comodines.add(comodinJugada);
        comodines.add(comodinSiempre);
        comodines.add(comodinJugada2);
        Comodin comodinCompuesto = new ComodinCompuesto("compuesto", "varios efectos", comodines);
        Puntaje puntaje = new Puntaje(0, 1);

        // Comodin escalera: 10 x 1
        // Comodin siempre: 0 x 2
        // Comodin color: 0 (no se activa)
        comodinCompuesto.modificarPuntaje(puntaje, new JugadaEscalera(), 0);

        assertEquals(40, puntaje.calcularTotal());
    }
}
