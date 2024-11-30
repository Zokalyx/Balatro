import edu.fiuba.algo3.modelo.comodin.ActivacionComodinSiempre;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.comodin.ComodinIndividual;
import edu.fiuba.algo3.modelo.contenedores.CartaNoDisponibleError;
import edu.fiuba.algo3.modelo.contenedores.Mazo;
import edu.fiuba.algo3.modelo.contenedores.Tienda;
import edu.fiuba.algo3.modelo.jugada.JugadaEscalera;
import edu.fiuba.algo3.modelo.tarot.ActivacionTarotJugadaParticular;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import edu.fiuba.algo3.modelo.LectorJson;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TiendaTest {
    @Test
    public void test01SePuedeComprarUnTarot() {
        LectorJson lector = new LectorJson();

        Mazo<Comodin> mazoComodines = new Mazo<>(lector.leerComodines());
        Mazo<Tarot> mazoTarots = new Mazo<>(lector.leerTarots());

        Tienda tienda = new Tienda(mazoComodines, mazoTarots);
        tienda.refrescar();

        Tarot tarotAComprar = tienda.getTarotsDisponibles().get(0);
        Tarot tarotRecibido = tienda.comprarTarot(tarotAComprar);

        assertEquals(tarotAComprar, tarotRecibido);
    }

    @Test
    public void test02SePuedeComprarUnComodin() {
        LectorJson lector = new LectorJson();

        Mazo<Comodin> mazoComodines = new Mazo<>(lector.leerComodines());
        Mazo<Tarot> mazoTarots = new Mazo<>(lector.leerTarots());

        Tienda tienda = new Tienda(mazoComodines, mazoTarots);
        tienda.refrescar();

        Comodin comodinAComprar = tienda.getComodinesDisponibles().get(0);
        Comodin comodinRecibido = tienda.comprarComodin(comodinAComprar);

        assertEquals(comodinAComprar, comodinRecibido);
    }

    @Test
    public void test03NoSePuedeComprarUnComodinDosVeces() {
        LectorJson lector = new LectorJson();

        Mazo<Comodin> mazoComodines = new Mazo<>(lector.leerComodines());
        Mazo<Tarot> mazoTarots = new Mazo<>(lector.leerTarots());

        Tienda tienda = new Tienda(mazoComodines, mazoTarots);
        tienda.refrescar();

        Comodin comodinAComprar = tienda.getComodinesDisponibles().get(0);
        tienda.comprarComodin(comodinAComprar);

        assertThrows(CartaNoDisponibleError.class, () -> tienda.comprarComodin(comodinAComprar));
    }

    @Test
    public void test04NoSePuedeComprarUnTarotDosVeces() {
        LectorJson lector = new LectorJson();

        Mazo<Comodin> mazoComodines = new Mazo<>(lector.leerComodines());
        Mazo<Tarot> mazoTarots = new Mazo<>(lector.leerTarots());

        Tienda tienda = new Tienda(mazoComodines, mazoTarots);
        tienda.refrescar();

        Tarot tarotAComprar = tienda.getTarotsDisponibles().get(0);
        tienda.comprarTarot(tarotAComprar);

        assertThrows(CartaNoDisponibleError.class, () -> tienda.comprarTarot(tarotAComprar));
    }

    @Test
    public void test05NoSePuedeComprarUnComodinQueNoEsDeLaTienda() {
        LectorJson lector = new LectorJson();

        Mazo<Comodin> mazoComodines = new Mazo<>(lector.leerComodines());
        Mazo<Tarot> mazoTarots = new Mazo<>(lector.leerTarots());

        Tienda tienda = new Tienda(mazoComodines, mazoTarots);
        tienda.refrescar();

        Comodin comodinAComprar = new ComodinIndividual("A", "B", 1, 2, new ActivacionComodinSiempre());

        assertThrows(CartaNoDisponibleError.class, () -> tienda.comprarComodin(comodinAComprar));
    }

    @Test
    public void test06NoSePuedeComprarUnTarotQueNoEsDeLaTienda() {
        LectorJson lector = new LectorJson();

        Mazo<Comodin> mazoComodines = new Mazo<>(lector.leerComodines());
        Mazo<Tarot> mazoTarots = new Mazo<>(lector.leerTarots());

        Tienda tienda = new Tienda(mazoComodines, mazoTarots);
        tienda.refrescar();

        Tarot tarotAComprar = new Tarot("a", "b", 1, 2, new ActivacionTarotJugadaParticular(new JugadaEscalera()));

        assertThrows(CartaNoDisponibleError.class, () -> tienda.comprarTarot(tarotAComprar));
    }

    @Test
    public void test07LosComodinesDisponiblesSon2() {
        LectorJson lector = new LectorJson();

        Mazo<Comodin> mazoComodines = new Mazo<>(lector.leerComodines());
        Mazo<Tarot> mazoTarots = new Mazo<>(lector.leerTarots());

        Tienda tienda = new Tienda(mazoComodines, mazoTarots);
        tienda.refrescar();

        assertEquals(2, tienda.getComodinesDisponibles().size());
    }

    @Test
    public void test08LosTarotsDisponiblesSon2() {
        LectorJson lector = new LectorJson();

        Mazo<Comodin> mazoComodines = new Mazo<>(lector.leerComodines());
        Mazo<Tarot> mazoTarots = new Mazo<>(lector.leerTarots());

        Tienda tienda = new Tienda(mazoComodines, mazoTarots);
        tienda.refrescar();

        assertEquals(2, tienda.getTarotsDisponibles().size());
    }

    @Test
    public void test09RefrescarVuelveLosComodinesDisponiblesA2() {
        LectorJson lector = new LectorJson();

        Mazo<Comodin> mazoComodines = new Mazo<>(lector.leerComodines());
        Mazo<Tarot> mazoTarots = new Mazo<>(lector.leerTarots());

        Tienda tienda = new Tienda(mazoComodines, mazoTarots);
        tienda.refrescar();

        tienda.comprarComodin(tienda.getComodinesDisponibles().get(0));
        tienda.comprarComodin(tienda.getComodinesDisponibles().get(0));

        tienda.refrescar();
        assertEquals(2, tienda.getComodinesDisponibles().size());
    }

    @Test
    public void test10RefrescarVuelveLosTarotsDisponiblesA2() {
        LectorJson lector = new LectorJson();

        Mazo<Comodin> mazoComodines = new Mazo<>(lector.leerComodines());
        Mazo<Tarot> mazoTarots = new Mazo<>(lector.leerTarots());

        Tienda tienda = new Tienda(mazoComodines, mazoTarots);
        tienda.refrescar();

        tienda.comprarTarot(tienda.getTarotsDisponibles().get(0));
        tienda.comprarTarot(tienda.getTarotsDisponibles().get(0));

        tienda.refrescar();
        assertEquals(2, tienda.getTarotsDisponibles().size());
    }
}
