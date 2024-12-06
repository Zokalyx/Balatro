package edu.fiuba.algo3.vistas.general;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;

public class SonidoManager {
    static SonidoManager instancia;
    Map<String, MediaPlayer> sonidos;
    double volumenMusica;
    double volumenEfectos;

    private SonidoManager() {
        HashMap<String, String> archivos = new HashMap<>();

        archivos.put("error", "/audio/error.wav");
        archivos.put("click", "/audio/sonido_click.wav");
        archivos.put("seleccion_carta", "/audio/seleccion_carta.mp3");
        archivos.put("cartas", "/audio/sonido_cartas.mp3");
        archivos.put("moneda", "/audio/sonido_moneda.wav");
        archivos.put("fuego", "/audio/fosforo.mp3");
        archivos.put("deseleccion_carta", "/audio/deseleccion_carta.mp3");
        archivos.put("musica", "/audio/Balatro Main Theme - Funk Fusion Cover.mp3");
        archivos.put("fichas", "/audio/sonido_fichas.mp3");

        sonidos = new HashMap<>();
        for (Map.Entry<String, String> archivo : archivos.entrySet()) {
            Media media = new Media(getClass().getResource(archivo.getValue()).toExternalForm());
            sonidos.put(archivo.getKey(), new MediaPlayer(media));
        }

        sonidos.get("musica").setOnEndOfMedia(() -> {
            sonidos.get("musica").seek(Duration.ZERO);
            sonidos.get("musica").play();
        });

        setVolumenEfectos(1.0);
        setVolumenMusica(1.0);
    }

    public static SonidoManager getInstancia() {
        if (instancia == null) {
            instancia = new SonidoManager();
        }

        return instancia;
    }

    public void setVolumenEfectos(double volumen) {
        volumenEfectos = volumen;

        sonidos.get("error").setVolume(0.5 * volumen);
        sonidos.get("click").setVolume(0.3 * volumen);
        sonidos.get("moneda").setVolume(0.9 * volumen);
        sonidos.get("fichas").setVolume(0.9 * volumen);
        sonidos.get("fuego").setVolume(1.1 * volumen);
        sonidos.get("seleccion_carta").setVolume(0.7 * volumen);
        sonidos.get("deseleccion_carta").setVolume(0.7 * volumen);
        sonidos.get("cartas").setVolume(0.6 * volumen);
    }

    public void setVolumenMusica(double volumen) {
        volumenMusica = volumen;

        sonidos.get("musica").setVolume(0.2 * volumen);
    }

    public void setMuteEfectos(boolean mute) {
        sonidos.get("error").setMute(mute);
        sonidos.get("musica").setMute(mute);
        sonidos.get("click").setMute(mute);
        sonidos.get("moneda").setMute(mute);
        sonidos.get("fichas").setMute(mute);
        sonidos.get("fuego").setMute(mute);
        sonidos.get("seleccion_carta").setMute(mute);
        sonidos.get("cartas").setMute(mute);
    }

    public void setMuteMusica(boolean mute) {
        sonidos.get("musica").setMute(mute);
    }

    public void play(String sonido) {
        sonidos.get(sonido).seek(Duration.ZERO);
        sonidos.get(sonido).play();
    }

    public double getVolumenMusica() {
        return volumenMusica;
    }

    public double getVolumenEfectos() {
        return volumenEfectos;
    }
}
