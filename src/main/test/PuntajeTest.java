import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.modelo.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuntajeTest {
    @Test
    public void test01PuntajeConMultiplicadorCeroYBaseCeroDaCero(){
        Puntaje puntaje = new Puntaje(0, 0);

        int total = puntaje.calcularTotal();

        assertEquals(0, total);
    }

    @Test
    public void test02PuntajeConMultiplicadorNoCeroYBaseCeroDaCero(){
        Puntaje puntaje = new Puntaje(0, 10);

        int total = puntaje.calcularTotal();

        assertEquals(0, total);
    }

    @Test
    public void test03PuntajeConMultiplicadorCeroYBaseNoCeroDaCero(){
        Puntaje puntaje = new Puntaje(10, 0);

        int total = puntaje.calcularTotal();

        assertEquals(0, total);
    }

    @Test
    public void test04PuntajeConMultiplicadorUnoDaIgualAValorBase(){
        Puntaje puntaje = new Puntaje(10, 1);

        int total = puntaje.calcularTotal();

        assertEquals(10, total);
    }

    @Test
    public void test05PuntajeConMultiplicadorDosDuplicaValorBase(){
        Puntaje puntaje = new Puntaje(10, 2);

        int total = puntaje.calcularTotal();

        assertEquals(20, total);
    }

    @Test
    public void test06SePuedeSumarPuntaje(){
        Puntaje puntaje = new Puntaje(10, 1);

        puntaje.sumarValorBase(20);

        assertEquals(30, puntaje.calcularTotal());
    }

    @Test
    public void test07SePuedeMultiplicarMultiplicador(){
        Puntaje puntaje = new Puntaje(20, 2);

        puntaje.multiplicarMultiplicador(3);

        assertEquals(120, puntaje.calcularTotal());
    }
}