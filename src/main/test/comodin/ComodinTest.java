package comodin;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.comodin.ComodinPuntaje;
import edu.fiuba.algo3.modelo.jugada.Jugada;
import edu.fiuba.algo3.modelo.jugada.JugadaEscalera;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ComodinTest {
    @Test
    public void test01UsarComodinPuntajeAfectaPuntaje() {
        Comodin comodin = new ComodinPuntaje("A", "B", 0, 8);
        Puntaje puntaje = new Puntaje(3, 1);
        ArrayList<Poker> cartas = new ArrayList<>();
        Jugada jugada = new JugadaEscalera(cartas);
        comodin.modificarPuntaje(puntaje, jugada, 0);

        assertEquals(27, puntaje.calcularTotal());
    }

    //Verificar que el jugador recibe un aumento correspondiente si tiene el comodín que
    //aumenta el multiplicador por 3 si juega una escalera
    @Test
    public void test02UsarComodinBonusPorManoJugadaAumentando3PorEscalera() {
        Comodin comodin = new ComodinPuntaje("A", "B", 0, 8);

        fail("No implementado");
    }
}
