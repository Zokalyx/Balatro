package comodin;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.comodin.ActivacionComodinJugada;
import edu.fiuba.algo3.modelo.comodin.ActivacionComodinSiempre;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.comodin.ComodinIndividual;
import edu.fiuba.algo3.modelo.jugada.Jugada;
import edu.fiuba.algo3.modelo.jugada.JugadaEscalera;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ComodinTest {
    @Test
    public void test01UsarComodinPuntajeAfectaPuntaje() {
        Comodin comodin = new ComodinIndividual("A", "B", 0, 8, new ActivacionComodinSiempre());
        Puntaje puntaje = new Puntaje(3, 1);
        Jugada jugada = new JugadaEscalera();
        comodin.modificarPuntaje(puntaje, jugada, 0);

        assertEquals(27, puntaje.calcularTotal());
    }

    //Verificar que el jugador recibe un aumento correspondiente si tiene el comodín que
    //aumenta el multiplicador por 3 si juega una escalera
    @Test
    public void test02UsarComodinBonusPorManoJugadaAumentando3PorEscalera() {
        Comodin comodin = new ComodinIndividual("A", "B", 0, 8, new ActivacionComodinJugada(new JugadaEscalera()));
        Jugada jugada = new JugadaEscalera();
        Puntaje puntaje = new Puntaje(2, 0);

        comodin.modificarPuntaje(puntaje, jugada, 0);

        assertEquals(16, puntaje.calcularTotal());
    }
}
