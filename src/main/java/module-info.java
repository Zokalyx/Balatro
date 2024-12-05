module edu.fiuba.algo3 {
    requires javafx.controls;
    requires javafx.media;

    requires json.simple;

    requires java.desktop;

    exports edu.fiuba.algo3.vistas;
    exports edu.fiuba.algo3.modelo;
    exports edu.fiuba.algo3.modelo.contenedores;
    exports edu.fiuba.algo3.modelo.tarot;
    exports edu.fiuba.algo3.modelo.comodin;
    exports edu.fiuba.algo3.modelo.palo;
    exports edu.fiuba.algo3.modelo.jugada;
    exports edu.fiuba.algo3.modelo.juego;
}