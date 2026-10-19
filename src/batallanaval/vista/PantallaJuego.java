package batallanaval.vista;

import batallanaval.controlador.ControladorDisparo;
import batallanaval.modelo.Disparo;
import batallanaval.modelo.Nave;
import batallanaval.modelo.ObservadorPartida;
import batallanaval.modelo.Partida;
import batallanaval.modelo.ResultadoDisparo;
import java.util.Scanner;

/**
 * Vista de texto. Lee la casilla que escribe el jugador y se la pasa al controlador;
 * todo lo que muestra lo escribe en actualizar(), cuando Partida le avisa de un cambio.
 */
public class PantallaJuego implements ObservadorPartida {

    private final Partida partida;
    private final Scanner entrada;
    private ControladorDisparo controlador;

    public PantallaJuego(Partida partida, Scanner entrada) {
        this.partida = partida;
        this.entrada = entrada;
    }

    public void setControlador(ControladorDisparo controlador) {
        this.controlador = controlador;
    }

    public void iniciar() {
        while (!partida.isTerminada()) {
            System.out.print("Turno de " + partida.getJugadorEnTurno().getNombre()
                    + " Casilla a disparar (ej. B7): ");
            if (!entrada.hasNextLine()) {
                break;
            }
            String texto = entrada.nextLine().trim().toUpperCase().replace(" ", "");
            if (!texto.isEmpty()) {
                clicCasilla(texto);
            }
        }
    }

    // clicl sobre el tablero de rastreo
    private void clicCasilla(String texto) {
        if (texto.length() < 2 || !Character.isLetter(texto.charAt(0))) {
            System.out.println("  Formato no reconocido. Escribe una letra y un número, por ejemplo B7.");
            return;
        }
        int fila = texto.charAt(0) - 'A';
        int columna;
        try {
            columna = Integer.parseInt(texto.substring(1)) - 1;
        } catch (NumberFormatException e) {
            System.out.println("  Formato no reconocido. Escribe una letra y un número, por ejemplo B7.");
            return;
        }
        controlador.disparar(fila, columna);
    }

    @Override
    public void actualizar(String evento) {
        switch (evento) {
            case Partida.PARTIDA_INICIADA ->
                    System.out.println("Se sorteó el primer turno: empieza " + partida.getJugadorEnTurno().getNombre() + ".\n");
            case Partida.DISPARO_RECHAZADO ->
                    System.out.println("  ¡Atención! " + partida.getMensajeRechazo());
            case Partida.DISPARO_REGISTRADO ->
                    mostrarResultado(partida.getUltimoDisparo());
            case Partida.TURNO_CEDIDO ->
                    System.out.println("  El turno pasa a " + partida.getJugadorEnTurno().getNombre() + ".\n");
            case Partida.PARTIDA_TERMINADA ->
                    System.out.println("\nFIN DE LA PARTIDA: gana " + partida.getGanador().getNombre()
                            + ", hundió todas las naves de " + partida.getRival().getNombre() + ".");
            default -> {
            }
        }
    }

    private void mostrarResultado(Disparo disparo) {
        String tirador = partida.getJugadorEnTurno().getNombre();
        if (disparo.resultado() == ResultadoDisparo.AGUA) {
            System.out.println("  " + tirador + " disparó a " + disparo.objetivo() + ": AGUA.");
            return;
        }
        Nave nave = disparo.nave();
        System.out.println("  " + tirador + " disparó a " + disparo.objetivo() + ": ¡IMPACTO! "
                + nave.getTipo().getNombre() + ": nave " + nave.estado().getTexto().toLowerCase() + ".");
        if (!partida.isTerminada()) {
            System.out.println("  Acertaste, tienes otro disparo.");
        }
    }
}
