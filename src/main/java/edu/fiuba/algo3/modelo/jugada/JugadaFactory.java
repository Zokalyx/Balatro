package edu.fiuba.algo3.modelo.jugada;

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
        return false;
    }

    private boolean esColor(ArrayList<Poker> cartas, ArrayList<Poker> cartasUsadas) {
        return false;
    }

    private boolean esFullHouse(ArrayList<Poker> cartas, ArrayList<Poker> cartasUsadas) {
        return false;
    }

    private boolean esPoker(ArrayList<Poker> cartas, ArrayList<Poker> cartasUsadas) {
        for (Poker carta1 : cartas) {
            for (Poker carta2 : cartas) {
                for (Poker carta3 : cartas) {
                    for (Poker carta4 : cartas) {
                        if (carta1.equals(carta2) || carta2.equals(carta3) || carta3.equals(carta4) || carta4.equals(carta1)) {
                            continue;
                        }

                        if (carta1.esMismoSimboloQue(carta2) && carta2.esMismoSimboloQue(carta3) && carta3.esMismoSimboloQue(carta4)) {
                            cartasUsadas.add(carta1);
                            cartasUsadas.add(carta2);
                            cartasUsadas.add(carta3);
                            cartasUsadas.add(carta4);
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    private boolean esEscaleraColor(ArrayList<Poker> cartas, ArrayList<Poker> cartasUsadas) {
        return false;
    }

    private boolean esEscaleraReal(ArrayList<Poker> cartas, ArrayList<Poker> cartasUsadas) {
        return false;
    }
}
