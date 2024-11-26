import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JuegoTest {
    @Test
    public void test01EstarEnLaUltimaRondaNoEsGanar() {
        Juego juego = new Juego();
        juego.jugarTurno(10000); // Pasamos a ronda 2 de una
        juego.jugarTurno(10000);
        juego.jugarTurno(10000);
        juego.jugarTurno(10000);
        juego.jugarTurno(10000);
        juego.jugarTurno(10000);
        juego.jugarTurno(10000); // Pasamos a ronda 8 (último)

        assertFalse(juego.gano());
    }

    @Test
    public void test02GanarLaOctavaRondaEsGanar() {
        Juego juego = new Juego();
        juego.jugarTurno(10000); // Pasamos a ronda 2 de una
        juego.jugarTurno(10000);
        juego.jugarTurno(10000);
        juego.jugarTurno(10000);
        juego.jugarTurno(10000);
        juego.jugarTurno(10000);
        juego.jugarTurno(10000); // Pasamos a ronda 8 (último)
        juego.jugarTurno(10000);

        assertTrue(juego.gano());
    }

    @Test
    public void test03SiNoSumaSuficientesPorCincoTurnosPuntosPierde() {
        Juego juego = new Juego();
        juego.jugarTurno(1);
        juego.jugarTurno(1);
        juego.jugarTurno(1);
        juego.jugarTurno(1);
        juego.jugarTurno(1);

        assertTrue(juego.perdio());
    }


    @Test
    public void test04SiSuma100PuntosEnPrimeraRondaNoPierde() {
        Juego juego = new Juego();
        juego.jugarTurno(20);
        juego.jugarTurno(20);
        juego.jugarTurno(20);
        juego.jugarTurno(20);
        juego.jugarTurno(20);

        assertFalse(juego.perdio());
    }

    @Test
    public void test05SiSuma99PuntosEnPrimeraRondaPierde() {
        Juego juego = new Juego();
        juego.jugarTurno(20);
        juego.jugarTurno(20);
        juego.jugarTurno(20);
        juego.jugarTurno(20);
        juego.jugarTurno(19);

        assertTrue(juego.perdio());
    }


    @Test
    public void test06SiSuma200PuntosEnSegundaRondaNoPierde() {
        Juego juego = new Juego();
        juego.jugarTurno(10000); // Gana primera ronda de una

        juego.jugarTurno(40);
        juego.jugarTurno(40);
        juego.jugarTurno(40);
        juego.jugarTurno(40);
        juego.jugarTurno(40);

        assertFalse(juego.perdio());
    }

    @Test
    public void test07SiSuma199PuntosEnSegundaRondaPierde() {
        Juego juego = new Juego();
        juego.jugarTurno(10000); // Gana primera ronda de una

        juego.jugarTurno(40);
        juego.jugarTurno(40);
        juego.jugarTurno(40);
        juego.jugarTurno(40);
        juego.jugarTurno(39);

        assertFalse(juego.perdio());
    }
}
