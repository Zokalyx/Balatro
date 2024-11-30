package tarot;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.jugada.Jugada;
import edu.fiuba.algo3.modelo.jugada.JugadaEscalera;
import edu.fiuba.algo3.modelo.palo.Diamante;
import edu.fiuba.algo3.modelo.tarot.ActivacionTarotJugadaParticular;
import edu.fiuba.algo3.modelo.tarot.ActivacionTarotPokerCualquiera;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TarotTest {
    @Test
    public void test01AplicarTarotModificaElValorNumericoDeCarta() {
        Poker carta = new Poker("7", new Diamante(), 7, 1);
        Tarot tarot = new Tarot("La Torre", "Mejora 1 carta seleccionada y la convierte en una carta de piedra.",50,1, new ActivacionTarotPokerCualquiera());
        tarot.modificar(carta);
        Puntaje puntaje = new Puntaje(0, 0);

        carta.modificarPuntaje(puntaje);

        assertEquals(50, puntaje.calcularTotal());
    }

    @Test
    public void test02AplicarTarotModificaElMultiplicadorDeCarta() {
        Poker carta = new Poker("7", new Diamante(), 7, 1);
        Tarot tarot = new Tarot("Justicia", "Mejora 1 carta seleccionada y la convierte en una carta de cristal", 1, 2, new ActivacionTarotPokerCualquiera());
        tarot.modificar(carta);
        Puntaje puntaje = new Puntaje(0, 0);

        carta.modificarPuntaje(puntaje);

        assertEquals(14, puntaje.calcularTotal());
    }

    @Test
    public void test03AplicarTarotModificaElValorNumericoDeJugada() {
        Jugada jugada = new JugadaEscalera();  // 30 x 4 normalmente
        Tarot tarot = new Tarot("Test", "A", 10, 0, new ActivacionTarotJugadaParticular(new JugadaEscalera()));
        tarot.modificar(jugada);
        Puntaje puntaje = new Puntaje(0, 0);

        jugada.modificarPuntaje(puntaje);

        assertEquals(160, puntaje.calcularTotal());
    }

    @Test
    public void test04AplicarTarotModificaElMultiplicadorDeJugada() {
        Jugada jugada = new JugadaEscalera();  // 30 x 4 normalmente
        Tarot tarot = new Tarot("Test", "A", 0, 1, new ActivacionTarotJugadaParticular(new JugadaEscalera()));
        tarot.modificar(jugada);
        Puntaje puntaje = new Puntaje(0, 0);

        jugada.modificarPuntaje(puntaje);

        assertEquals(150, puntaje.calcularTotal());
    }
}
