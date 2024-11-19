package entregas;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.LectorJson;
import edu.fiuba.algo3.modelo.Poker;
import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.comodin.ComodinBonusPorManoJugada;
import edu.fiuba.algo3.modelo.comodin.ComodinDescarte;
import edu.fiuba.algo3.modelo.comodin.ComodinPuntaje;
import edu.fiuba.algo3.modelo.contenedores.Mazo;
import edu.fiuba.algo3.modelo.contenedores.SinCartasError;
import edu.fiuba.algo3.modelo.jugada.Jugada;
import edu.fiuba.algo3.modelo.jugada.JugadaEscalera;
import edu.fiuba.algo3.modelo.palo.Corazon;
import edu.fiuba.algo3.modelo.palo.Diamante;
import edu.fiuba.algo3.modelo.palo.Pica;
import edu.fiuba.algo3.modelo.palo.Trebol;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Entrega2Test {
    @Test
    public void test01UsarComodinPuntajeAfectaPuntaje() {
        Comodin comodin = new ComodinPuntaje("A", "B", 0, 8);
        Puntaje puntaje = new Puntaje(3, 1);
        ArrayList<Poker> cartas = new ArrayList<>();
        Jugada jugada = new JugadaEscalera(cartas);
        comodin.modificarPuntaje(puntaje, jugada);

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
        Comodin comodin = new ComodinBonusPorManoJugada("a", "a", "escalera", 0, 3);
        Puntaje puntaje = new Puntaje(0, 0);
        ArrayList<Poker> cartas = new ArrayList<>();
        Jugada jugada = new JugadaEscalera(cartas);
        jugada.modificarPuntaje(puntaje);
        comodin.modificarPuntaje(puntaje, jugada);
        assertEquals(210, puntaje.calcularTotal());
    }

    @Test
    public void test05seSuman10PuntosPorDescarte(){
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker("As", new Pica(), 10, 0));
        cartas.add(new Poker("As", new Diamante(), 10, 0));
        cartas.add(new Poker("As", new Trebol(), 10, 0));
        cartas.add(new Poker("As", new Corazon(), 10, 0));
        cartas.add(new Poker("7", new Pica(), 7, 0));
        cartas.add(new Poker("7", new Diamante(), 7, 0));
        cartas.add(new Poker("7", new Trebol(), 7, 0));
        cartas.add(new Poker("2", new Corazon(), 2, 0));
        cartas.add(new Poker("3", new Corazon(), 3, 0));
        cartas.add(new Poker("4", new Corazon(), 4, 0));

        ArrayList<Poker> cartas2 = new ArrayList<>(cartas);

        Mazo mazo = new Mazo(cartas2);
        Jugador jugador = new Jugador(mazo);

        Comodin comodin = new ComodinDescarte("a", "a",  10, 1);
        jugador.agregarComodin(comodin);

        jugador.repartirMano();

        jugador.seleccionarCarta(cartas.get(2));
        jugador.seleccionarCarta(cartas.get(3));
        jugador.descartarCartas();

        jugador.seleccionarCarta(cartas.get(5));

        assertEquals(40, jugador.jugarMano());
    }
}
