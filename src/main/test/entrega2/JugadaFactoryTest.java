package entrega2;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.jugada.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JugadaFactoryTest {
    @Test
    public void test01LaJugadaSinCartasEsNula() {
        JugadaFactory jugadaFactory = new JugadaFactory();
        ArrayList<Poker> cartas = new ArrayList<>();

        Jugada jugada = jugadaFactory.obtenerJugada(cartas);

        assert(jugada instanceof JugadaNula);
    }

    @Test
    public void test02JugarUnaCartaEsCartaAlta() {
        JugadaFactory jugadaFactory = new JugadaFactory();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker(0, "As", 0, new Pica()));

        Jugada jugada = jugadaFactory.obtenerJugada(cartas);

        assert(jugada instanceof JugadaCartaAlta);
    }

    @Test
    public void test03JugarCartasQueNoFormanNadaEsCartaAlta() {
        JugadaFactory jugadaFactory = new JugadaFactory();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker(0, "As", 0, new Pica()));
        cartas.add(new Poker(0, "Jota", 0, new Pica()));
        cartas.add(new Poker(0, "7", 0, new Trebol()));
        cartas.add(new Poker(0, "3", 0, new Trebol()));
        cartas.add(new Poker(0, "2", 0, new Diamante()));

        Jugada jugada = jugadaFactory.obtenerJugada(cartas);

        assert(jugada instanceof JugadaCartaAlta);
    }

    @Test
    public void test04DosCartasConMismoSimboloFormanUnPar() {
        JugadaFactory jugadaFactory = new JugadaFactory();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker(0, "As", 0, new Pica()));
        cartas.add(new Poker(0, "As", 0, new Trebol()));

        Jugada jugada = jugadaFactory.obtenerJugada(cartas);

        assert(jugada instanceof JugadaPar);
    }

    @Test
    public void test05UnParEntreOtrasCartasFormanUnPar() {
        JugadaFactory jugadaFactory = new JugadaFactory();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker(0, "As", 0, new Pica()));
        cartas.add(new Poker(0, "As", 0, new Trebol()));
        cartas.add(new Poker(0, "7", 0, new Diamante()));
        cartas.add(new Poker(0, "2", 0, new Corazon()));

        Jugada jugada = jugadaFactory.obtenerJugada(cartas);

        assert(jugada instanceof JugadaPar);
    }

    @Test
    public void test06TresCartasConMismoSimboloFormanUnaPierna() {
        JugadaFactory jugadaFactory = new JugadaFactory();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker(0, "As", 0, new Pica()));
        cartas.add(new Poker(0, "As", 0, new Trebol()));
        cartas.add(new Poker(0, "As", 0, new Diamante()));

        Jugada jugada = jugadaFactory.obtenerJugada(cartas);

        assert(jugada instanceof JugadaPierna);
    }

    @Test
    public void test07UnaPiernaEntreOtrasCartasFormanUnaPierna() {
        JugadaFactory jugadaFactory = new JugadaFactory();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker(0, "As", 0, new Pica()));
        cartas.add(new Poker(0, "As", 0, new Trebol()));
        cartas.add(new Poker(0, "As", 0, new Diamante()));
        cartas.add(new Poker(0, "7", 0, new Corazon()));
        cartas.add(new Poker(0, "2", 0, new Diamante()));

        Jugada jugada = jugadaFactory.obtenerJugada(cartas);

        assert(jugada instanceof JugadaPierna);
    }

    @Test
    public void test08CuatroCartasConMismoSimboloFormanUnPoker() {
        JugadaFactory jugadaFactory = new JugadaFactory();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker(0, "As", 0, new Pica()));
        cartas.add(new Poker(0, "As", 0, new Trebol()));
        cartas.add(new Poker(0, "As", 0, new Diamante()));
        cartas.add(new Poker(0, "As", 0, new Corazon()));
        cartas.add(new Poker(0, "2", 0, new Diamante()));

        Jugada jugada = jugadaFactory.obtenerJugada(cartas);

        assert(jugada instanceof JugadaPoker);
    }

    @Test
    public void test09CuatroCartasConMismoPaloFormanUnColor() {
        JugadaFactory jugadaFactory = new JugadaFactory();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker(0, "As", 0, new Pica()));
        cartas.add(new Poker(0, "2", 0, new Pica()));
        cartas.add(new Poker(0, "5", 0, new Pica()));
        cartas.add(new Poker(0, "8", 0, new Pica()));

        Jugada jugada = jugadaFactory.obtenerJugada(cartas);

        assert(jugada instanceof JugadaColor);
    }

    @Test
    public void test10SePuedeFormarUnFullHouse() {
        JugadaFactory jugadaFactory = new JugadaFactory();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker(0, "As", 0, new Pica()));
        cartas.add(new Poker(0, "As", 0, new Corazon()));
        cartas.add(new Poker(0, "As", 0, new Diamante()));
        cartas.add(new Poker(0, "Rey", 0, new Pica()));
        cartas.add(new Poker(0, "Rey", 0, new Corazon()));

        Jugada jugada = jugadaFactory.obtenerJugada(cartas);

        assert(jugada instanceof JugadaFullHouse);
    }

    @Test
    public void test11SePuedeFormarUnaEscaleraNormal() {
        JugadaFactory jugadaFactory = new JugadaFactory();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker(0, "2", 0, new Pica()));
        cartas.add(new Poker(0, "3", 0, new Corazon()));
        cartas.add(new Poker(0, "4", 0, new Diamante()));
        cartas.add(new Poker(0, "5", 0, new Pica()));
        cartas.add(new Poker(0, "6", 0, new Corazon()));

        Jugada jugada = jugadaFactory.obtenerJugada(cartas);

        assert(jugada instanceof JugadaEscalera);
    }

    @Test
    public void test12SePuedeFormarUnaEscaleraDeColor() {
        JugadaFactory jugadaFactory = new JugadaFactory();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker(0, "2", 0, new Pica()));
        cartas.add(new Poker(0, "3", 0, new Pica()));
        cartas.add(new Poker(0, "4", 0, new Pica()));
        cartas.add(new Poker(0, "5", 0, new Pica()));
        cartas.add(new Poker(0, "6", 0, new Pica()));

        Jugada jugada = jugadaFactory.obtenerJugada(cartas);

        assert(jugada instanceof JugadaEscaleraColor);
    }

    @Test
    public void test12SePuedeFormarUnaEscaleraReal() {
        JugadaFactory jugadaFactory = new JugadaFactory();
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker(0, "As", 0, new Pica()));
        cartas.add(new Poker(0, "Rey", 0, new Pica()));
        cartas.add(new Poker(0, "Reina", 0, new Pica()));
        cartas.add(new Poker(0, "Jota", 0, new Pica()));
        cartas.add(new Poker(0, "10", 0, new Pica()));

        Jugada jugada = jugadaFactory.obtenerJugada(cartas);

        assert(jugada instanceof JugadaEscaleraReal);
    }
}
