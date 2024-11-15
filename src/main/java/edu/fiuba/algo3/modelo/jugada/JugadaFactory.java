package edu.fiuba.algo3.modelo.jugada;

import edu.fiuba.algo3.modelo.Palo;
import edu.fiuba.algo3.modelo.Poker;

import java.util.ArrayList;
import java.util.HashMap;

public class JugadaFactory {
    public Jugada obtenerJugada(ArrayList<Poker> cartas) {
        ArrayList<Poker> cartasUsadas = new ArrayList<>();

        if (esEscaleraReal(cartas, cartasUsadas)) {
            return (new JugadaEscaleraReal(cartasUsadas));
        } else if (esEscaleraColor(cartas, cartasUsadas)) {
            return new JugadaEscaleraColor(cartasUsadas);
        } else if (esPoker(cartas, cartasUsadas)) {
            return new JugadaPoker(cartasUsadas);
        } else if (esFullHouse(cartas, cartasUsadas)) {
            return (new JugadaFullHouse(cartasUsadas));
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
        for (Poker carta1 : cartas) {
            for (Poker carta2 : cartas) {
                if (carta1.equals(carta2)) {
                    continue;
                }

                if (carta1.esMismoSimboloQue(carta2)) {
                    // Si hubiera otro par, ya tendría que haber sido detectado por otra jugada.
                    cartasUsadas.add(carta1);
                    cartasUsadas.add(carta2);
                    return true;
                }
            }
        }

        return false;
    }

    private boolean esDoblePar(ArrayList<Poker> cartas, ArrayList<Poker> cartasUsadas) {
        return false;
    }

    private boolean esPierna(ArrayList<Poker> cartas, ArrayList<Poker> cartasUsadas) {
        for (Poker carta1 : cartas) {
            for (Poker carta2 : cartas) {
                for (Poker carta3 : cartas) {
                    if (carta1.equals(carta2) || carta2.equals(carta3) || carta3.equals(carta1)) {
                        continue;
                    }

                    if (carta1.esMismoSimboloQue(carta2) && carta2.esMismoSimboloQue(carta3)) {
                        cartasUsadas.add(carta1);
                        cartasUsadas.add(carta2);
                        cartasUsadas.add(carta3);
                        return true;
                    }
                }
            }
        }

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
        if (cartas.size() != 5) {
            return false;
        }
        for (Poker carta : cartas) {

            if (!carta.esMismoPaloQue(cartas.get(0))) {
                return false;
            }
        }
        cartasUsadas.addAll(cartas);
        return true;
    }

    private boolean esFullHouse(ArrayList<Poker> cartas, ArrayList<Poker> cartasUsadas) {
        ArrayList<Poker> cartasSinUsar = new ArrayList<>();
        if (cartas.size() != 5) {
            return false;
        }
        for (Poker carta1 : cartas) {
            for (Poker carta2 : cartas) {
                for (Poker carta3 : cartas) {
                    for (Poker carta4 : cartas) {
                        for (Poker carta5 : cartas) {
                            if (carta1.equals(carta2) || carta2.equals(carta3) || carta3.equals(carta4) || carta4.equals(carta5)) {
                                continue;
                            }

                            if (carta1.esMismoSimboloQue(carta2) && carta2.esMismoSimboloQue(carta3)) {
                                cartasSinUsar.add(carta4);
                                cartasSinUsar.add(carta5);
                                cartasUsadas.add(carta1);
                                cartasUsadas.add(carta2);
                                cartasUsadas.add(carta3);
                            }
                        }
                    }
                }
            }
        }
        return esPar(cartasSinUsar, cartasUsadas);
    }

    private boolean esPoker(ArrayList<Poker> cartas, ArrayList<Poker> cartasUsadas) {
        if (cartas.size() < 4) {
            return false;
        }
        HashMap<String,Integer> contador = new HashMap<>();
        for (Poker carta : cartas) {
            contador.put(carta.getSimbolo(),contador.getOrDefault(carta.getSimbolo(),0)+1);
        }
        String simbolo = "";
        boolean pokerEncontrado = false;
        for (HashMap.Entry<String,Integer> entry :contador.entrySet()) {
            if (entry.getValue() == 4) {
                simbolo = entry.getKey();
                pokerEncontrado = true;
                break;
            }
        }
        if (pokerEncontrado) {
            for (Poker carta : cartas) {
                if (carta.getSimbolo().equals(simbolo)) {
                    cartasUsadas.add(carta);
                }
            }
            return true;
        }
        return false;
    }

    private boolean esEscaleraColor(ArrayList<Poker> cartas, ArrayList<Poker> cartasUsadas) {
        if (esEscalera(cartas, cartasUsadas)) {
            for (int i = 1; i < cartas.size(); i++) {
                if (!cartas.get(i).esMismoPaloQue(cartas.get(i - 1))) {
                    return false;
                }
            }
            cartasUsadas.addAll(cartas);
            return true;
        }

        return false;
    }

    private boolean esEscaleraReal(ArrayList<Poker> cartas, ArrayList<Poker> cartasUsadas) {
        if (esEscaleraColor(cartas, cartasUsadas)) {
            if (cartas.get(4).getValorDeCarta() != 14) {
                return false;
            }
            return true;
        }

        return false;
    }
}
