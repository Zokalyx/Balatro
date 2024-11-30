package jugada;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.jugada.*;
import edu.fiuba.algo3.modelo.palo.Corazon;
import edu.fiuba.algo3.modelo.palo.Diamante;
import edu.fiuba.algo3.modelo.palo.Pica;
import edu.fiuba.algo3.modelo.palo.Trebol;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JugadaFactoryTest {
    @Test
    public void test01LaJugadaSinCartasLanzaUnaExcepcion() {
        JugadaFactory jugadaFactory = new JugadaFactory();
        ArrayList<Poker> cartas = new ArrayList<>();

        assertThrows(JugadaNulaError.class, () -> jugadaFactory.obtenerJugada(cartas));
    }

    @Test
    public void test02JugarUnaCartaEsCartaAlta() {
        JugadaFactory jugadaFactory = new JugadaFactory();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker("As", new Pica(), 0, 0));

        Jugada jugada = jugadaFactory.obtenerJugada(cartas);

        assertInstanceOf(JugadaCartaAlta.class, jugada);
    }

    @Test
    public void test03JugarCartasQueNoFormanNadaEsCartaAlta() {
        JugadaFactory jugadaFactory = new JugadaFactory();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker("As", new Pica(), 0, 0));
        cartas.add(new Poker("Jota", new Pica(), 0, 0));
        cartas.add(new Poker("7", new Trebol(), 0, 0));
        cartas.add(new Poker("3", new Trebol(), 0, 0));
        cartas.add(new Poker("2", new Diamante(), 0, 0));

        Jugada jugada = jugadaFactory.obtenerJugada(cartas);

        assertInstanceOf(JugadaCartaAlta.class, jugada);
    }

    @Test
    public void test04DosCartasConMismoSimboloFormanUnPar() {
        JugadaFactory jugadaFactory = new JugadaFactory();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker("As", new Pica(), 0, 0));
        cartas.add(new Poker("As", new Trebol(), 0, 0));

        Jugada jugada = jugadaFactory.obtenerJugada(cartas);

        assertInstanceOf(JugadaPar.class,jugada);
    }

    @Test
    public void test05UnParEntreOtrasCartasFormanUnPar() {
        JugadaFactory jugadaFactory = new JugadaFactory();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker("As", new Pica(), 0, 0));
        cartas.add(new Poker("As", new Trebol(), 0, 0));
        cartas.add(new Poker("7", new Diamante(), 0, 0));
        cartas.add(new Poker("2", new Corazon(), 0, 0));

        Jugada jugada = jugadaFactory.obtenerJugada(cartas);

        assertInstanceOf(JugadaPar.class,jugada);
    }

    @Test
    public void test06TresCartasConMismoSimboloFormanUnaPierna() {
        JugadaFactory jugadaFactory = new JugadaFactory();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker("As", new Pica(), 0, 0));
        cartas.add(new Poker("As", new Trebol(), 0, 0));
        cartas.add(new Poker("As", new Diamante(), 0, 0));

        Jugada jugada = jugadaFactory.obtenerJugada(cartas);

        assertInstanceOf(JugadaPierna.class, jugada);
    }

    @Test
    public void test07UnaPiernaEntreOtrasCartasFormanUnaPierna() {
        JugadaFactory jugadaFactory = new JugadaFactory();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker("As", new Pica(), 0, 0));
        cartas.add(new Poker("As", new Trebol(), 0, 0));
        cartas.add(new Poker("As", new Diamante(), 0, 0));
        cartas.add(new Poker("7", new Corazon(), 0, 0));
        cartas.add(new Poker("2", new Diamante(), 0, 0));

        Jugada jugada = jugadaFactory.obtenerJugada(cartas);

        assert(jugada instanceof JugadaPierna);
    }

    @Test
    public void test08CuatroCartasConMismoSimboloFormanUnPoker() {
        JugadaFactory jugadaFactory = new JugadaFactory();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker("As", new Pica(), 0, 0));
        cartas.add(new Poker("As", new Trebol(), 0, 0));
        cartas.add(new Poker("As", new Diamante(), 0, 0));
        cartas.add(new Poker("As", new Corazon(), 0, 0));
        cartas.add(new Poker("2", new Diamante(), 0, 0));

        Jugada jugada = jugadaFactory.obtenerJugada(cartas);

        assertInstanceOf(JugadaPoker.class, jugada);
    }

    @Test
    public void test09CincoCartasConMismoPaloFormanUnColor() {
        JugadaFactory jugadaFactory = new JugadaFactory();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker("As", new Pica(), 0, 0));
        cartas.add(new Poker("2", new Pica(), 0, 0));
        cartas.add(new Poker("5", new Pica(), 0, 0));
        cartas.add(new Poker("7", new Pica(), 0, 0));
        cartas.add(new Poker("8", new Pica(), 0, 0));

        Jugada jugada = jugadaFactory.obtenerJugada(cartas);

        assertInstanceOf(JugadaColor.class, jugada);
    }

    @Test
    public void test10SePuedeFormarUnFullHouse() {
        JugadaFactory jugadaFactory = new JugadaFactory();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker("As", new Pica(), 0, 0));
        cartas.add(new Poker("As", new Corazon(), 0, 0));
        cartas.add(new Poker("As", new Diamante(), 0, 0));
        cartas.add(new Poker("Rey", new Pica(), 0, 0));
        cartas.add(new Poker("Rey", new Corazon(), 0, 0));

        Jugada jugada = jugadaFactory.obtenerJugada(cartas);

        assertInstanceOf(JugadaFullHouse.class, jugada);
    }

    @Test
    public void test11SePuedeFormarUnaEscaleraNormal() {
        JugadaFactory jugadaFactory = new JugadaFactory();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker("2", new Pica(), 0, 0));
        cartas.add(new Poker("3", new Corazon(), 0, 0));
        cartas.add(new Poker("4", new Diamante(), 0, 0));
        cartas.add(new Poker("5", new Pica(), 0, 0));
        cartas.add(new Poker("6", new Corazon(), 0, 0));

        Jugada jugada = jugadaFactory.obtenerJugada(cartas);

        assertInstanceOf(JugadaEscalera.class, jugada);
    }

    @Test
    public void test12SePuedeFormarUnaEscaleraDeColor() {
        JugadaFactory jugadaFactory = new JugadaFactory();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker("2", new Pica(), 0, 0));
        cartas.add(new Poker("3", new Pica(), 0, 0));
        cartas.add(new Poker("4", new Pica(), 0, 0));
        cartas.add(new Poker("5", new Pica(), 0, 0));
        cartas.add(new Poker("6", new Pica(), 0, 0));

        Jugada jugada = jugadaFactory.obtenerJugada(cartas);

        assertInstanceOf(JugadaEscaleraColor.class, jugada);
    }

    @Test
    public void test13SePuedeFormarUnaEscaleraReal() {
        JugadaFactory jugadaFactory = new JugadaFactory();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker("Reina", new Pica(), 0, 0));
        cartas.add(new Poker("Rey", new Pica(), 0, 0));
        cartas.add(new Poker("As", new Pica(), 0, 0));
        cartas.add(new Poker("Jota", new Pica(), 0, 0));
        cartas.add(new Poker("10", new Pica(), 0, 0));

        Jugada jugada = jugadaFactory.obtenerJugada(cartas);

        assertInstanceOf(JugadaEscaleraReal.class, jugada);
    }

    @Test
    public void test14NoSePuedeFormarUnaEscaleraQueTengaAsEnMedio() {
        JugadaFactory jugadaFactory = new JugadaFactory();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker("Reina", new Pica(), 0, 0));
        cartas.add(new Poker("Rey", new Diamante(), 0, 0));
        cartas.add(new Poker("As", new Corazon(), 0, 0));
        cartas.add(new Poker("2", new Pica(), 0, 0));
        cartas.add(new Poker("3", new Pica(), 0, 0));

        Jugada jugada = jugadaFactory.obtenerJugada(cartas);

        assertInstanceOf(JugadaCartaAlta.class, jugada);
    }

    @Test
    public void test15DosParesDistintosFormanUnDoblePar() {
        JugadaFactory jugadaFactory = new JugadaFactory();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker("2", new Pica(), 0, 0));
        cartas.add(new Poker("3", new Diamante(), 0, 0));
        cartas.add(new Poker("4", new Corazon(), 0, 0));
        cartas.add(new Poker("3", new Pica(), 0, 0));
        cartas.add(new Poker("2", new Corazon(), 0, 0));

        Jugada jugada = jugadaFactory.obtenerJugada(cartas);

        assertInstanceOf(JugadaDoblePar.class, jugada);
    }
}
