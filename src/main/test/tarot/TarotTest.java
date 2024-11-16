package tarot;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.palo.Diamante;
import edu.fiuba.algo3.modelo.tarot.Tarot;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TarotTest {
    @Test
    public void test01AplicarTarotModificaElValorNumericoDeCarta() {
        Poker carta = new Poker("7", new Diamante(), 7, 1);
        Tarot tarot = Tarot.CrearTarot("La Torre", "Mejora 1 carta seleccionada y la convierte en una carta de piedra.","carta","cualquiera",50,1);
        ArrayList<Poker> pokers = new ArrayList<>();
        pokers.add(carta);
        tarot.modificar(pokers);

        assertEquals(carta.usar(), 50);
    }

    @Test
    public void test02AplicarTarotModificaElMultiplicadorDeCarta() {
        Poker carta = new Poker("7", new Diamante(), 7, 1);
        Tarot tarot = Tarot.CrearTarot("Justicia", "Mejora 1 carta seleccionada y la convierte en una carta de cristal", "carta", "cualquiera", 1, 2);
        ArrayList<Poker> pokers = new ArrayList<>();
        pokers.add(carta);
        tarot.modificar(pokers);

        assertEquals(carta.usar(), 14);
    }
}
