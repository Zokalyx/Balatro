package edu.fiuba.algo3.modelo.jugada;

import edu.fiuba.algo3.modelo.Poker;

import java.util.ArrayList;
import java.util.Collections;

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
        if (cartas.isEmpty()) {
            return false;
        }

        ArrayList<Poker> cartasAuxiliar = new ArrayList<>(cartas);
        Collections.sort(cartasAuxiliar);
        cartasUsadas.add(cartasAuxiliar.get(0));
        return true;
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
        if (esPar(cartas, cartasUsadas)) {
            ArrayList<Poker> cartasRestantes = new ArrayList<>();
            for (Poker carta : cartas) {
                if (!cartasUsadas.contains(carta)) {
                    cartasRestantes.add(carta);
                }
            }
            if (esPar(cartasRestantes, cartasUsadas)) {
                return true;
            }

            cartasUsadas.clear();
        }
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

        ArrayList<Poker> cartasAuxiliar = new ArrayList<>(cartas);
        Collections.sort(cartasAuxiliar);

        // Caso especial: Hay un As y un Rey.
        // Movemos el As al final.
        if (cartasAuxiliar.get(0).esAs() && cartasAuxiliar.get(0).esSimboloSiguienteA(cartasAuxiliar.get(4))) {
            Poker as = cartasAuxiliar.get(0);
            cartasAuxiliar.remove(as);
            cartasAuxiliar.add(as);
        }

        // Verificar que las cartas tengan valores consecutivos
        for (int i = 0; i < cartas.size() - 1; i++) {
            if (!cartas.get(i).esSimboloAnteriorA(cartas.get(i + 1))) {
                return false;
            }
        }

        for (Poker carta : cartas) {
            if (cartasAuxiliar.contains(carta)) {
                cartasUsadas.add(carta);
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
        if (esPierna(cartas, cartasUsadas)) {
            for (Poker carta : cartas) {
                if (!cartasUsadas.contains(carta)) {
                    cartasSinUsar.add(carta);
                }
            }
            if (esPar(cartasSinUsar, cartasUsadas)) {
                return true;
            } else {
                // esPierna pudo haber modificado cartasUsadas, pero no queremos eso si en realidad no hubo full house.
                cartasUsadas.clear();
                return false;
            }
        }
        return false;
    }

    private boolean esPoker(ArrayList<Poker> cartas, ArrayList<Poker> cartasUsadas) {
        if (cartas.size() < 4) {
            return false;
        }

        // Caso base
        if (cartas.size() == 5) {
            ArrayList<Poker> cartasAuxiliar = new ArrayList<>(cartas);
            Collections.sort(cartasAuxiliar);
            boolean cartaDistintaEsLaPrimera = !cartasAuxiliar.get(0).esMismoSimboloQue(cartasAuxiliar.get(1));
            if (cartaDistintaEsLaPrimera) {
                cartasAuxiliar.remove(0);
            } else {
                cartasAuxiliar.remove(4);
            }
            return esPoker(cartasAuxiliar, cartasUsadas);
        }

        if (!cartas.get(0).esMismoSimboloQue(cartas.get(1)) || !cartas.get(1).esMismoSimboloQue(cartas.get(2)) || !cartas.get(2).esMismoSimboloQue(cartas.get(3))) {
            return false;
        }

        cartasUsadas.addAll(cartas);
        return true;
    }

    private boolean esEscaleraColor(ArrayList<Poker> cartas, ArrayList<Poker> cartasUsadas) {
        if (esEscalera(cartas, new ArrayList<>())) {
            return esColor(cartas, cartasUsadas);
        }

        return false;
    }

    private boolean esEscaleraReal(ArrayList<Poker> cartas, ArrayList<Poker> cartasUsadas) {
        if (esEscaleraColor(cartas, cartasUsadas)) {
            if (cartas.get(4).esAs()) {
                return true;
            }
            cartasUsadas.clear();
            return false;
        }

        return false;
    }
}
