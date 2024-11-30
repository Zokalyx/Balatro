package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.comodin.*;
import edu.fiuba.algo3.modelo.jugada.*;
import edu.fiuba.algo3.modelo.palo.*;
import edu.fiuba.algo3.modelo.tarot.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LectorJson {
    public ArrayList<Poker> leerMazo() {
        JSONObject archivo;
        try {
            archivo = parsearArchivo("src/test/resources/json/mazo.json");
        } catch (IOException e) {
            throw new RuntimeException("No se pudo abrir el archivo");
        } catch (ParseException e) {
            throw new RuntimeException("No se pudo parsear el contenido JSON");
        }

        ArrayList<Poker> pokers = new ArrayList<>();
        JSONArray mazo = (JSONArray) archivo.get("mazo");
        for (Object carta : mazo) {
            Poker poker = parsearPoker((JSONObject) carta);
            pokers.add(poker);
        }

        return pokers;
    }

    public ArrayList<Comodin> leerComodines() {
        JSONObject archivo;
        try {
            archivo = parsearArchivo("src/test/resources/json/comodines.json");
        } catch (IOException e) {
            throw new RuntimeException("No se pudo abrir el archivo");
        } catch (ParseException e) {
            throw new RuntimeException("No se pudo parsear el contenido JSON");
        }

        ArrayList<Comodin> listaComodines = new ArrayList<>();
        for (Object key : archivo.keySet()) {
            String categoria = (String) key;
            JSONArray comodines = (JSONArray) ((JSONObject) archivo.get(key)).get("comodines");
            for (Object obj : comodines) {
                JSONObject comodin = (JSONObject) obj;
                boolean compuesto = categoria.equals("Combinación");
                listaComodines.add(parsearComodin(comodin, compuesto));
            }
        }

        return listaComodines;
    }

    public ArrayList<Tarot> leerTarots() {
        JSONObject archivo;
        try {
            archivo = parsearArchivo("src/test/resources/json/tarots.json");
        } catch (IOException e) {
            throw new RuntimeException("No se pudo abrir el archivo");
        } catch (ParseException e) {
            throw new RuntimeException("No se pudo parsear el contenido JSON");
        }

        ArrayList<Tarot> tarots = new ArrayList<>();
        JSONArray mazo = (JSONArray) archivo.get("tarots");
        for (Object carta : mazo) {
            Tarot tarot = parsearTarot((JSONObject) carta);
            tarots.add(tarot);
        }

        return tarots;
    }

    private JSONObject parsearArchivo(String nombreArchivo) throws IOException, ParseException {
        FileReader reader = new FileReader(nombreArchivo);
        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(reader);
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

    private Comodin parsearComodin(JSONObject carta, boolean compuesto) {
        String nombre = (String) carta.get("nombre");
        String descripcion = (String) carta.get("descripcion");
        if (compuesto) {
            JSONArray comodines =  (JSONArray) carta.get("comodines");
            ArrayList<Comodin> listaComodines = new ArrayList<>();
            for (Object obj : comodines) {
                JSONObject comodin = (JSONObject) obj;
                Comodin comodinIndividual = parsearComodin(comodin, false);
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
