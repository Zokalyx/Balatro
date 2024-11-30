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

public class JugadaManagerTest {
    @Test
    public void test01LaJugadaSinCartasLanzaUnaExcepcion() {
        JugadaManager jugadaFactory = new JugadaManager();
        ArrayList<Poker> cartas = new ArrayList<>();

        assertThrows(JugadaNulaError.class, () -> jugadaFactory.calcularJugada(cartas));
    }

    @Test
    public void test02JugarUnaCartaEsCartaAlta() {
        JugadaManager jugadaFactory = new JugadaManager();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker("As", new Pica(), 0, 0));

        Jugada jugada = jugadaFactory.calcularJugada(cartas);

        assertInstanceOf(JugadaCartaAlta.class, jugada);
    }

    @Test
    public void test03JugarCartasQueNoFormanNadaEsCartaAlta() {
        JugadaManager jugadaFactory = new JugadaManager();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker("As", new Pica(), 0, 0));
        cartas.add(new Poker("Jota", new Pica(), 0, 0));
        cartas.add(new Poker("7", new Trebol(), 0, 0));
        cartas.add(new Poker("3", new Trebol(), 0, 0));
        cartas.add(new Poker("2", new Diamante(), 0, 0));

        Jugada jugada = jugadaFactory.calcularJugada(cartas);

        assertInstanceOf(JugadaCartaAlta.class, jugada);
    }

    @Test
    public void test04DosCartasConMismoSimboloFormanUnPar() {
        JugadaManager jugadaFactory = new JugadaManager();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker("As", new Pica(), 0, 0));
        cartas.add(new Poker("As", new Trebol(), 0, 0));

        Jugada jugada = jugadaFactory.calcularJugada(cartas);

        assertInstanceOf(JugadaPar.class,jugada);
    }

    @Test
    public void test05UnParEntreOtrasCartasFormanUnPar() {
        JugadaManager jugadaFactory = new JugadaManager();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker("As", new Pica(), 0, 0));
        cartas.add(new Poker("As", new Trebol(), 0, 0));
        cartas.add(new Poker("7", new Diamante(), 0, 0));
        cartas.add(new Poker("2", new Corazon(), 0, 0));

        Jugada jugada = jugadaFactory.calcularJugada(cartas);

        assertInstanceOf(JugadaPar.class,jugada);
    }

    @Test
    public void test06TresCartasConMismoSimboloFormanUnaPierna() {
        JugadaManager jugadaFactory = new JugadaManager();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker("As", new Pica(), 0, 0));
        cartas.add(new Poker("As", new Trebol(), 0, 0));
        cartas.add(new Poker("As", new Diamante(), 0, 0));

        Jugada jugada = jugadaFactory.calcularJugada(cartas);

        assertInstanceOf(JugadaPierna.class, jugada);
    }

    @Test
    public void test07UnaPiernaEntreOtrasCartasFormanUnaPierna() {
        JugadaManager jugadaFactory = new JugadaManager();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker("As", new Pica(), 0, 0));
        cartas.add(new Poker("As", new Trebol(), 0, 0));
        cartas.add(new Poker("As", new Diamante(), 0, 0));
        cartas.add(new Poker("7", new Corazon(), 0, 0));
        cartas.add(new Poker("2", new Diamante(), 0, 0));

        Jugada jugada = jugadaFactory.calcularJugada(cartas);

        assert(jugada instanceof JugadaPierna);
    }

    @Test
    public void test08CuatroCartasConMismoSimboloFormanUnPoker() {
        JugadaManager jugadaFactory = new JugadaManager();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker("As", new Pica(), 0, 0));
        cartas.add(new Poker("As", new Trebol(), 0, 0));
        cartas.add(new Poker("As", new Diamante(), 0, 0));
        cartas.add(new Poker("As", new Corazon(), 0, 0));
        cartas.add(new Poker("2", new Diamante(), 0, 0));

        Jugada jugada = jugadaFactory.calcularJugada(cartas);

        assertInstanceOf(JugadaPoker.class, jugada);
    }

    @Test
    public void test09CincoCartasConMismoPaloFormanUnColor() {
        JugadaManager jugadaFactory = new JugadaManager();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker("As", new Pica(), 0, 0));
        cartas.add(new Poker("2", new Pica(), 0, 0));
        cartas.add(new Poker("5", new Pica(), 0, 0));
        cartas.add(new Poker("7", new Pica(), 0, 0));
        cartas.add(new Poker("8", new Pica(), 0, 0));

        Jugada jugada = jugadaFactory.calcularJugada(cartas);

        assertInstanceOf(JugadaColor.class, jugada);
    }

    @Test
    public void test10SePuedeFormarUnFullHouse() {
        JugadaManager jugadaFactory = new JugadaManager();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker("As", new Pica(), 0, 0));
        cartas.add(new Poker("As", new Corazon(), 0, 0));
        cartas.add(new Poker("As", new Diamante(), 0, 0));
        cartas.add(new Poker("Rey", new Pica(), 0, 0));
        cartas.add(new Poker("Rey", new Corazon(), 0, 0));

        Jugada jugada = jugadaFactory.calcularJugada(cartas);

        assertInstanceOf(JugadaFullHouse.class, jugada);
    }

    @Test
    public void test11SePuedeFormarUnaEscaleraNormal() {
        JugadaManager jugadaFactory = new JugadaManager();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker("2", new Pica(), 0, 0));
        cartas.add(new Poker("3", new Corazon(), 0, 0));
        cartas.add(new Poker("4", new Diamante(), 0, 0));
        cartas.add(new Poker("5", new Pica(), 0, 0));
        cartas.add(new Poker("6", new Corazon(), 0, 0));

        Jugada jugada = jugadaFactory.calcularJugada(cartas);

        assertInstanceOf(JugadaEscalera.class, jugada);
    }

    @Test
    public void test12SePuedeFormarUnaEscaleraDeColor() {
        JugadaManager jugadaFactory = new JugadaManager();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker("2", new Pica(), 0, 0));
        cartas.add(new Poker("3", new Pica(), 0, 0));
        cartas.add(new Poker("4", new Pica(), 0, 0));
        cartas.add(new Poker("5", new Pica(), 0, 0));
        cartas.add(new Poker("6", new Pica(), 0, 0));

        Jugada jugada = jugadaFactory.calcularJugada(cartas);

        assertInstanceOf(JugadaEscaleraColor.class, jugada);
    }

    @Test
    public void test13SePuedeFormarUnaEscaleraReal() {
        JugadaManager jugadaFactory = new JugadaManager();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker("Reina", new Pica(), 0, 0));
        cartas.add(new Poker("Rey", new Pica(), 0, 0));
        cartas.add(new Poker("As", new Pica(), 0, 0));
        cartas.add(new Poker("Jota", new Pica(), 0, 0));
        cartas.add(new Poker("10", new Pica(), 0, 0));

        Jugada jugada = jugadaFactory.calcularJugada(cartas);

        assertInstanceOf(JugadaEscaleraReal.class, jugada);
    }

    @Test
    public void test14NoSePuedeFormarUnaEscaleraQueTengaAsEnMedio() {
        JugadaManager jugadaFactory = new JugadaManager();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker("Reina", new Pica(), 0, 0));
        cartas.add(new Poker("Rey", new Diamante(), 0, 0));
        cartas.add(new Poker("As", new Corazon(), 0, 0));
        cartas.add(new Poker("2", new Pica(), 0, 0));
        cartas.add(new Poker("3", new Pica(), 0, 0));

        Jugada jugada = jugadaFactory.calcularJugada(cartas);

        assertInstanceOf(JugadaCartaAlta.class, jugada);
    }

    @Test
    public void test15DosParesDistintosFormanUnDoblePar() {
        JugadaManager jugadaFactory = new JugadaManager();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker("2", new Pica(), 0, 0));
        cartas.add(new Poker("3", new Diamante(), 0, 0));
        cartas.add(new Poker("4", new Corazon(), 0, 0));
        cartas.add(new Poker("3", new Pica(), 0, 0));
        cartas.add(new Poker("2", new Corazon(), 0, 0));

        Jugada jugada = jugadaFactory.calcularJugada(cartas);

        assertInstanceOf(JugadaDoblePar.class, jugada);
    }
}
