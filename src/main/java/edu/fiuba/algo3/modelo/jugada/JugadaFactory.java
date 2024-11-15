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
        return false;
    }

    private boolean esDoblePar(ArrayList<Poker> cartas, ArrayList<Poker> cartasUsadas) {
        return false;
    }

    private boolean esPierna(ArrayList<Poker> cartas, ArrayList<Poker> cartasUsadas) {
        return false;
    }

    private boolean esEscalera(ArrayList<Poker> cartas, ArrayList<Poker> cartasUsadas) {
        return false;
    }

    private boolean esFullHouse(ArrayList<Poker> cartas, ArrayList<Poker> cartasUsadas) {
        if (cartas.size() > 5) {
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

                            if (carta1.esMismoPaloQue(carta2) && carta2.esMismoPaloQue(carta3)) {
                                cartasUsadas.add(carta1);
                                cartasUsadas.add(carta2);
                                cartasUsadas.add(carta3);
                                cartasUsadas.add(carta4);
                                cartasUsadas.add(carta5);
                            }
                        }
                    }
                }
            }
        }
        return esPar(cartasSinUsar, cartasUsadas);
    }

    private boolean esFullHouse(ArrayList<Poker> cartas, ArrayList<Poker> cartasUsadas) {
        ArrayList<Poker> cartasSinUsar = new ArrayList<>();
        if (cartas.size() > 5) {
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
        return false;
    }

    private boolean esEscaleraColor(ArrayList<Poker> cartas, ArrayList<Poker> cartasUsadas) {
        return false;
    }

    private boolean esEscaleraReal(ArrayList<Poker> cartas, ArrayList<Poker> cartasUsadas) {
        return false;
    }
}
