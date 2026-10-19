package batallanaval.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Partida {

    public static final String PARTIDA_INICIADA = "PARTIDA_INICIADA";
    public static final String DISPARO_RECHAZADO = "DISPARO_RECHAZADO";
    public static final String DISPARO_REGISTRADO = "DISPARO_REGISTRADO";
    public static final String TURNO_CEDIDO = "TURNO_CEDIDO";
    public static final String PARTIDA_TERMINADA = "PARTIDA_TERMINADA";

    private final List<ObservadorPartida> observadores = new ArrayList<>();
    private final Jugador jugador1;
    private final Jugador jugador2;

    private Jugador turnoDe;
    private Jugador ganador;
    private Disparo ultimoDisparo;
    private String mensajeRechazo;

    public Partida(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
    }

    public void agregarObservador(ObservadorPartida observador) {
        observadores.add(observador);
    }

    // sortear quien empeiza
    public void iniciar(Random random) {
        turnoDe = random.nextBoolean() ? jugador1 : jugador2;
        notificar(PARTIDA_INICIADA);
    }

    public boolean esTurnoDe(Jugador jugador) {
        return turnoDe == jugador;
    }

    public TableroFlota tableroFlotaRival() {
        return getRival().getTableroFlota();
    }

    public void registrarDisparo(Disparo disparo) {
        ultimoDisparo = disparo;
        if (tableroFlotaRival().todasHundidas()) {
            ganador = turnoDe;
        }
        notificar(DISPARO_REGISTRADO);
        if (isTerminada()) {
            notificar(PARTIDA_TERMINADA);
        }
    }

    public void rechazarDisparo(String motivo) {
        mensajeRechazo = motivo;
        notificar(DISPARO_RECHAZADO);
    }

    public void cederTurno() {
        turnoDe = getRival();
        notificar(TURNO_CEDIDO);
    }

    private void notificar(String evento) {
        for (ObservadorPartida observador : observadores) {
            observador.actualizar(evento);
        }
    }

    public Jugador getJugadorEnTurno() {
        return turnoDe;
    }

    public Jugador getRival() {
        return turnoDe == jugador1 ? jugador2 : jugador1;
    }

    public boolean isTerminada() {
        return ganador != null;
    }

    public Jugador getGanador() {
        return ganador;
    }

    public Disparo getUltimoDisparo() {
        return ultimoDisparo;
    }

    public String getMensajeRechazo() {
        return mensajeRechazo;
    }
}
