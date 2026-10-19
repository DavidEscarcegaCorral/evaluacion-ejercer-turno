package batallanaval;

import batallanaval.controlador.ControladorDisparo;
import batallanaval.modelo.Coordenada;
import batallanaval.modelo.Jugador;
import batallanaval.modelo.Partida;
import batallanaval.modelo.TableroFlota;
import batallanaval.modelo.TipoNave;
import batallanaval.vista.PantallaJuego;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Jugador jugador1 = new Jugador("Jugador 1");
        Jugador jugador2 = new Jugador("Jugador 2");
        simularFlota(jugador1.getTableroFlota());
        simularFlota(jugador2.getTableroFlota());

        Partida partida = new Partida(jugador1, jugador2);
        PantallaJuego pantalla = new PantallaJuego(partida, new Scanner(System.in));
        ControladorDisparo controlador = new ControladorDisparo(partida);
        pantalla.setControlador(controlador);
        partida.agregarObservador(pantalla);

        System.out.println("BATALLA NAVAL");
        partida.iniciar(new Random());
        pantalla.iniciar();
    }

    // Colocacion de la flota
    private static void simularFlota(TableroFlota flota) {
        flota.colocarNave(TipoNave.PORTAAVIONES, new Coordenada(0, 0)); // A1-A4
        flota.colocarNave(TipoNave.PORTAAVIONES, new Coordenada(0, 5)); // A6-A9
        flota.colocarNave(TipoNave.CRUCERO, new Coordenada(2, 0));      // C1-C3
        flota.colocarNave(TipoNave.CRUCERO, new Coordenada(2, 4));      // C5-C7
        flota.colocarNave(TipoNave.SUBMARINO, new Coordenada(4, 0));    // E1-E2
        flota.colocarNave(TipoNave.SUBMARINO, new Coordenada(4, 3));    // E4-E5
        flota.colocarNave(TipoNave.SUBMARINO, new Coordenada(4, 6));    // E7-E8
        flota.colocarNave(TipoNave.SUBMARINO, new Coordenada(6, 0));    // G1-G2
        flota.colocarNave(TipoNave.BARCO, new Coordenada(6, 3));        // G4
        flota.colocarNave(TipoNave.BARCO, new Coordenada(6, 5));        // G6
        flota.colocarNave(TipoNave.BARCO, new Coordenada(6, 7));        // G8
    }
}
