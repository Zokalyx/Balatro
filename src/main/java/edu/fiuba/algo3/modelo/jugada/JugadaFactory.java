package edu.fiuba.algo3.modelo.jugada;

import edu.fiuba.algo3.modelo.Palo;
import edu.fiuba.algo3.modelo.Poker;

import java.util.ArrayList;

public class JugadaFactory {
    public Jugada obtenerJugada(ArrayList<Poker> cartas) {
        ArrayList<Poker> cartasUsadas = new ArrayList<>();

        if (esEscaleraReal(cartas, cartasUsadas)) {
            return new JugadaEscaleraReal(cartasUsadas);
        } else if (esEscaleraColor(cartas, cartasUsadas)) {
            return new JugadaEscaleraColor(cartasUsadas);
        } else if (esPoker(cartas, cartasUsadas)) {
            return new JugadaPoker(cartasUsadas);
        } else if (esFullHouse(cartas, cartasUsadas)) {
            return new JugadaFullHouse(cartasUsadas);
        } else if (esColor(cartas, cartasUsadas)) {
            return new JugadaColor(cartasUsadas);
        } else if (esEscalera(cartas, cartasUsadas)) {
            return new JugadaEscalera(cartasUsadas);
        } else if (esPierna(cartas, cartasUsadas)) {
            return new JugadaPierna(cartasUsadas);
        } else if (esDoblePar(cartas, cartasUsadas)) {
            return new JugadaDoblePar(cartasUsadas);
        } else if (esPar(cartas, cartasUsadas)) {
            return new JugadaPar(cartasUsadas);
        } else if (esCartaAlta(cartas, cartasUsadas)) {
            return new JugadaCartaAlta(cartasUsadas);
        } else {
            return new JugadaNula(cartasUsadas);
        }
    }

    private boolean esCartaAlta(ArrayList<Poker> cartas, ArrayList<Poker> cartasUsadas) {
        return false;
    }

    private boolean esPar(ArrayList<Poker> cartas, ArrayList<Poker> cartasUsadas) {
        return false;
    }

    private boolean esDoblePar(ArrayList<Poker> cartas, ArrayList<Poker> cartasUsadas) {
        return false;
    }

    private boolean esPierna(ArrayList<Poker> cartas, ArrayList<Poker> cartasUsadas) {
        return false;
    }

    private boolean esEscalera(ArrayList<Poker> cartas, ArrayList<Poker> cartasUsadas) {

        if (cartas.size() != 5) {
            return false;
        }

        // Ordenar cartas de menor a mayor
        int n = cartas.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (cartas.get(j).getValorDeCarta() > cartas.get(j + 1).getValorDeCarta()) {
                    // Intercambiar cartas[j] y cartas[j+1]
                    Poker temp = cartas.get(j);
                    cartas.set(j, cartas.get(j + 1));
                    cartas.set(j + 1, temp);
                }
            }
        }

        // Verificar que las cartas tengan valores consecutivos
        for (int i = 0; i < cartas.size() - 1; i++) {
            int valorActual = cartas.get(i).getValorDeCarta();
            int valorSiguiente = cartas.get(i + 1).getValorDeCarta();
            if (valorSiguiente != valorActual + 1) {
                return false;
            }
        }

        return true;
    }

    private boolean esColor(ArrayList<Poker> cartas, ArrayList<Poker> cartasUsadas) {
        return false;
    }

    private boolean esFullHouse(ArrayList<Poker> cartas, ArrayList<Poker> cartasUsadas) {
        return false;
    }

    private boolean esPoker(ArrayList<Poker> cartas, ArrayList<Poker> cartasUsadas) {
        return false;
    }

    private boolean esEscaleraColor(ArrayList<Poker> cartas, ArrayList<Poker> cartasUsadas) {
        if (esEscalera(cartas, cartasUsadas)) {
            for (int i = 1; i < cartas.size(); i++) {
                if (cartas.get(i) != cartas.get(i - 1)) {
                    return false;
                }
            }
        }
        cartasUsadas.addAll(cartas);
        return true;
    }

    private boolean esEscaleraReal(ArrayList<Poker> cartas, ArrayList<Poker> cartasUsadas) {
        if (esEscaleraColor(cartas, cartasUsadas)) {
            if (cartas.get(5).getValorDeCarta() != 1) {
                return false;
            };
        }
        return true;
    }
}
