package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.comodin.*;
import edu.fiuba.algo3.modelo.contenedores.Mazo;
import edu.fiuba.algo3.modelo.juego.ConfiguracionJuego;
import edu.fiuba.algo3.modelo.juego.ConfiguracionRonda;
import edu.fiuba.algo3.modelo.jugada.*;
import edu.fiuba.algo3.modelo.palo.*;
import edu.fiuba.algo3.modelo.tarot.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class LectorJson {
    public ArrayList<Poker> leerMazo() {
        JSONObject archivo;
        try {
            archivo = parsearArchivo("/json/mazo.json");
        } catch (IOException e) {
            throw new RuntimeException("No se pudo abrir el archivo");
        } catch (ParseException e) {
            throw new RuntimeException("No se pudo parsear el contenido JSON");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        JSONArray mazo = (JSONArray) archivo.get("mazo");
        return parsearMazo(mazo);
    }

    public ArrayList<Comodin> leerComodines() {
        JSONObject archivo;
        try {
            archivo = parsearArchivo("/json/comodines.json");
        } catch (IOException e) {
            throw new RuntimeException("No se pudo abrir el archivo");
        } catch (ParseException e) {
            throw new RuntimeException("No se pudo parsear el contenido JSON");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        return parsearCategoriasDeComodines(archivo);
    }

    public ArrayList<Tarot> leerTarots() {
        JSONObject archivo;
        try {
            archivo = parsearArchivo("/json/tarots.json");
        } catch (IOException e) {
            throw new RuntimeException("No se pudo abrir el archivo");
        } catch (ParseException e) {
            throw new RuntimeException("No se pudo parsear el contenido JSON");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        JSONArray tarots = (JSONArray) archivo.get("tarots");
        return parsearTarots(tarots);
    }

    public ConfiguracionJuego leerConfiguracion() {
        JSONObject archivo;
        try {
            archivo = parsearArchivo("/json/balatro.json");
        } catch (IOException e) {
            throw new RuntimeException("No se pudo abrir el archivo");
        } catch (ParseException e) {
            throw new RuntimeException("No se pudo parsear el contenido JSON");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        ArrayList<ConfiguracionRonda> configuracionRondas = new ArrayList<>();
        JSONArray rondas = (JSONArray) archivo.get("rondas");
        for (Object ronda : rondas) {
            ConfiguracionRonda configuracionRonda = parsearRonda((JSONObject) ronda);
            configuracionRondas.add(configuracionRonda);
        }

        Mazo<Poker> mazo = new Mazo<>(parsearMazo((JSONArray) archivo.get("mazo")));

        return new ConfiguracionJuego(configuracionRondas, mazo);
    }

    private ConfiguracionRonda parsearRonda(JSONObject ronda) {
        int turnos = Math.toIntExact((long) ronda.get("manos"));
        int descartes = Math.toIntExact((long) ronda.get("descartes"));
        int puntajeObjetivo = Math.toIntExact((long) ronda.get("puntajeASuperar"));
        JSONObject tienda =  (JSONObject) ronda.get("tienda");
        ArrayList<Tarot> tarots = parsearTarots((JSONArray) tienda.get("tarots"));
        ArrayList<Comodin> comodines = parsearListaDeComodines((JSONArray) tienda.get("comodines"));
        ArrayList<Poker> cartas = new ArrayList<>();
        cartas.add(parsearPoker((JSONObject) tienda.get("carta")));

        return new ConfiguracionRonda(puntajeObjetivo, turnos, descartes, comodines, tarots, cartas);
    }

    private JSONObject parsearArchivo(String nombreArchivo) throws IOException, ParseException, URISyntaxException {
        JSONParser parser = new JSONParser();
        String pathArchivo = Paths.get(getClass().getResource(nombreArchivo).toURI()).toString();
        return (JSONObject) parser.parse(new FileReader(pathArchivo));
    }

    private ArrayList<Poker> parsearMazo(JSONArray mazo) {
        ArrayList<Poker> pokers = new ArrayList<>();
        for (Object carta : mazo) {
            Poker poker = parsearPoker((JSONObject) carta);
            pokers.add(poker);
        }
        return pokers;
    }

    private ArrayList<Comodin> parsearCategoriasDeComodines(JSONObject archivo) {
        ArrayList<Comodin> listaComodines = new ArrayList<>();
        for (Object key : archivo.keySet()) {
            JSONArray comodines = (JSONArray) ((JSONObject) archivo.get(key)).get("comodines");
            for (Object obj : comodines) {
                JSONObject comodin = (JSONObject) obj;
                listaComodines.add(parsearComodin(comodin));
            }
        }

        return listaComodines;
    }

    private ArrayList<Comodin> parsearListaDeComodines(JSONArray comodines) {
        ArrayList<Comodin> listaComodines = new ArrayList<>();
        for (Object comodin : comodines) {
            Comodin comodinParseado = parsearComodin((JSONObject) comodin);
            listaComodines.add(comodinParseado);
        }

        return listaComodines;
    }

    private ArrayList<Tarot> parsearTarots(JSONArray tarots) {
        ArrayList<Tarot> listaTarots = new ArrayList<>();
        for (Object carta : tarots) {
            Tarot tarot = parsearTarot((JSONObject) carta);
            listaTarots.add(tarot);
        }

        return listaTarots;
    }

    private Poker parsearPoker(JSONObject carta) {
        // String nombre = (String) carta.get("nombre");
        Palo palo;
        switch ((String) carta.get("palo")) {
            case "Trebol":
                palo = new Trebol();
                break;
            case "Diamantes":
                palo = new Diamante();
                break;
            case "Corazones":
                palo = new Corazon();
                break;
            case "Picas":
                palo = new Pica();
                break;
            default:
                throw new RuntimeException("Palo inválido");
        }
        String numero = (String) carta.get("numero");
        int puntos = Math.toIntExact((long) carta.get("puntos"));
        int multiplicador = Integer.parseInt((String) carta.get("multiplicador"));
        return new Poker(numero, palo, puntos, multiplicador);
    }

    private Tarot parsearTarot(JSONObject carta) {
        String nombre = (String) carta.get("nombre");
        String descripcion = (String) carta.get("descripcion");
        JSONObject efecto = (JSONObject) carta.get("efecto");
        int puntos = Math.toIntExact((long) efecto.get("puntos"));
        double multiplicador = ((Number) efecto.get("multiplicador")).doubleValue();
        String sobre = (String) carta.get("sobre");
        String ejemplar = (String) carta.get("ejemplar");

        if (sobre.equals("carta")) {
            return new Tarot(nombre, descripcion, puntos, multiplicador, new ActivacionTarotPokerCualquiera());
        } else if (sobre.equals("mano")) {
            return new Tarot(nombre, descripcion, puntos, multiplicador, new ActivacionTarotJugadaParticular(parsearJugada(ejemplar)));
        }

        throw new SobreNoEncontradoError("Sobre no encontrado");
    }

    private Jugada parsearJugada(String activacionString) {
        switch (activacionString.toLowerCase()) {
            case "escalera real":
                return new JugadaEscaleraReal();
            case "escalera de color":
                return new JugadaEscaleraColor();
            case "poker":
                return new JugadaPoker();
            case "full":
                return new JugadaFullHouse();
            case "color":
                return new JugadaColor();
            case "escalera":
                return new JugadaEscalera();
            case "trio":
                return new JugadaPierna();
            case "doble par":
                return new JugadaDoblePar();
            case "par":
                return new JugadaPar();
            case "carta alta":
                return new JugadaCartaAlta();
            default:
                throw new JugadaNoEncontradaError("Jugada desconocida: " + activacionString);
        }
    }

    private ActivacionComodin parsearActivacion(Object activacionJSON) {
        if (activacionJSON instanceof String) {
            switch ((String) activacionJSON) {
                case "Siempre":
                    return new ActivacionComodinSiempre();
                case "Descarte":
                    return new ActivacionComodinDescarte();
                default:
                    throw new RuntimeException("Activación desconocida");
            }

        } else if (activacionJSON instanceof JSONObject) {
            String key = (String) ((JSONObject) activacionJSON).keySet().iterator().next();
            Object valor = ((JSONObject) activacionJSON).get(key);
            switch (key) {
                case "1 en":
                    return new ActivacionComodinProbabilidad(Math.toIntExact((long) valor));
                case "Mano Jugada":
                    return new ActivacionComodinJugada(parsearJugada((String) valor));
                default:
                    throw new RuntimeException("Activación desconocida");
            }
        }

        throw new RuntimeException("Activación no parseable");
    }

    private Comodin parsearComodin(JSONObject carta) {
        String nombre = (String) carta.get("nombre");
        String descripcion = (String) carta.get("descripcion");

        JSONArray comodines = (JSONArray) carta.get("comodines");
        if (comodines != null) {
            ArrayList<Comodin> listaComodines = new ArrayList<>();
            for (Object obj : comodines) {
                JSONObject comodin = (JSONObject) obj;
                Comodin comodinIndividual = parsearComodin(comodin);
                listaComodines.add(comodinIndividual);
            }
            return new ComodinCompuesto(nombre, descripcion, listaComodines);

        } else {
            ActivacionComodin activacionComodin = parsearActivacion(carta.get("activacion"));
            JSONObject efecto = (JSONObject) carta.get("efecto");
            int puntos = Math.toIntExact((long) efecto.get("puntos"));
            int multiplicador = Math.toIntExact((long) efecto.get("multiplicador"));
            return new ComodinIndividual(nombre, descripcion, puntos, multiplicador, activacionComodin);
        }
    }
}
