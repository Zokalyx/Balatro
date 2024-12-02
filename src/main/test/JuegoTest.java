import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.juego.Juego;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JuegoTest {
    @Test
    public void test01SePuedeCargarJuego() {
        LectorJson lectorJson = new LectorJson();
        Juego juego = new Juego(lectorJson.leerConfiguracion());

        assertEquals(0, juego.getRondaActual());
    }

    @Test
    public void test02NoLlegarAlPuntajeObjetivoEnLaRondaPierde() {
        LectorJson lectorJson = new LectorJson();
        Juego juego = new Juego(lectorJson.leerConfiguracion());

        juego.jugarTurno(0);
        juego.jugarTurno(0);
        juego.jugarTurno(0);

        assertTrue(juego.perdio());
    }

    @Test
    public void test03SuperarElPuntajeObjetivoAvanzaRonda() {
        LectorJson lectorJson = new LectorJson();
        Juego juego = new Juego(lectorJson.leerConfiguracion());

        juego.jugarTurno(1000000);

        assertEquals(1, juego.getRondaActual());
    }

    @Test
    public void test04SuperarTodasLasRondasEsGanar() {
        LectorJson lectorJson = new LectorJson();
        Juego juego = new Juego(lectorJson.leerConfiguracion());

        for (int i = 0; i < 8; i++) {
            juego.jugarTurno(1000000);
        }

        assertTrue(juego.gano());
    }
}
