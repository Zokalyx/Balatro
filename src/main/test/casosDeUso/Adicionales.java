package casosDeUso;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.comodin.ActivacionComodinDescarte;
import edu.fiuba.algo3.modelo.comodin.ActivacionComodinJugada;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.comodin.ComodinIndividual;
import edu.fiuba.algo3.modelo.contenedores.Comodines;
import edu.fiuba.algo3.modelo.contenedores.Mano;
import edu.fiuba.algo3.modelo.contenedores.Mazo;
import edu.fiuba.algo3.modelo.contenedores.Tienda;
import edu.fiuba.algo3.modelo.juego.ConfiguracionJuego;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.jugada.*;
import edu.fiuba.algo3.modelo.palo.Corazon;
import edu.fiuba.algo3.modelo.palo.Diamante;
import edu.fiuba.algo3.modelo.palo.Pica;
import edu.fiuba.algo3.modelo.palo.Trebol;
import edu.fiuba.algo3.modelo.tarot.ActivacionTarotJugadaParticular;
import edu.fiuba.algo3.modelo.tarot.ActivacionTarotPokerCualquiera;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class Adicionales {
    @Test
    public void test01JugadaCompleta() {
        List<Poker> cartas = Arrays.asList(
                new Poker("A", new Pica(), 10, 1),
                new Poker("A", new Diamante(), 10, 1),
                new Poker("A", new Trebol(), 10, 1),
                new Poker("A", new Corazon(), 10, 1),
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

        Tarot tarot = new Tarot("a", "b", 20, 1, new ActivacionTarotPokerCualquiera());
        tarot.utilizarSobre(cartas.get(0));

        Comodin comodin1 = new ComodinIndividual("a", "b", 0, 2, new ActivacionComodinDescarte());
        Comodin comodin2 = new ComodinIndividual("b", "c", 1, 1, new ActivacionComodinJugada(new JugadaPar()));
        Comodin comodin3 = new ComodinIndividual("d", "e", 30, 30, new ActivacionComodinJugada(new JugadaPierna()));
        Comodines comodines = new Comodines();
        comodines.agregar(comodin1);
        comodines.agregar(comodin2);
        comodines.agregar(comodin3);

        mano.seleccionarCarta(cartas.get(0));
        mano.seleccionarCarta(cartas.get(1));

        // Par de A
        Jugada jugada = mano.jugar();

        Puntaje puntaje = new Puntaje(0, 1);

        // Par: 10 x 2
        // Primer as (modificado por tarot): 30 x 1
        // Segundo as: 10 x 1
        jugada.modificarPuntaje(puntaje);
        // Primer comodin: 0 x 2 (5 activaciones)
        // Segundo comodin: 1 x 1
        // Tercer comodin: No se activa.
        comodines.modificarPuntaje(puntaje, jugada, 5);

        assertEquals((10 + 30 + 10 + 1) * (2 * 1 * 1 + 10 + 1), puntaje.calcularTotal());
    }

    @Test
    public void test02MejorarUnaJugadaConUnTarotEsPermanente() {
        JugadaManager jugadaManager = new JugadaManager();
        Jugada jugada = jugadaManager.getJugada(new JugadaEscalera());
        Tarot tarot = new Tarot("a", "b", 20, 3, new ActivacionTarotJugadaParticular(new JugadaEscalera()));
        tarot.utilizarSobre(jugada);

        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(new Poker("3", new Trebol(), 0, 1));
        cartas.add(new Poker("4", new Diamante(), 0, 1));
        cartas.add(new Poker("5", new Trebol(), 0, 1));
        cartas.add(new Poker("6", new Trebol(), 0, 1));
        cartas.add(new Poker("7", new Trebol(), 0, 1));
        jugadaManager.calcularJugada(cartas);

        Puntaje puntaje = new Puntaje(0, 1);
        jugada.modificarPuntaje(puntaje);

        // Debería seguir estando el tarot activo.
        // Escalera: 30 x 4
        // Tarot: 20 x 3
        // Total: 50 x 12
        assertEquals(600, puntaje.calcularTotal());
    }

    @Test
    public void test03SeCargaUnaPartidaYSeJuegaUnTurno() {
        LectorJson lector = new LectorJson();

        ConfiguracionJuego configuracion = lector.leerConfiguracion();
        JugadaManager jugadaManager = new JugadaManager();
        Mano mano = new Mano(configuracion.getMazo(), jugadaManager);
        Juego juego = new Juego(configuracion);

        // Inicio de ronda
        int rondaActual = juego.getRondaActual();
        int descartesDisponibles = configuracion.getDescartes(rondaActual);
        Tienda tienda = new Tienda();
        tienda.abrir(configuracion.getComodines(rondaActual), configuracion.getTarots(rondaActual), configuracion.getPokers(rondaActual));
        Comodines comodines = new Comodines();
        ArrayList<Tarot> tarots = new ArrayList<>();

        // Cumbre mística: x15 por descarte disponible
        Comodin comodin = tienda.getComodinesDisponibles().get(1);
        tienda.comprarComodin(comodin);

        // Inicio de turno
        mano.repartir();
        ArrayList<Poker> cartas = mano.getCartas();
        mano.seleccionarCarta(cartas.get(0));
        descartesDisponibles--;

        cartas = mano.getCartas();
        Poker cartaAJugar = cartas.get(0);
        mano.seleccionarCarta(cartaAJugar);

        Jugada jugada = mano.jugar();
        Puntaje puntaje = new Puntaje(0, 1);
        jugada.modificarPuntaje(puntaje);
        comodines.modificarPuntaje(puntaje, jugada, descartesDisponibles);

        juego.jugarTurno(puntaje.calcularTotal());
        if (juego.gano()) {
            // hacer algo
        } else if (juego.perdio()) {
            // hacer algo
        }

        assertTrue(puntaje.calcularTotal() > 0);
    }
}
