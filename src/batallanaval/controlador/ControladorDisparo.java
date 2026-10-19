package batallanaval.controlador;

import batallanaval.modelo.Coordenada;
import batallanaval.modelo.Disparo;
import batallanaval.modelo.Jugador;
import batallanaval.modelo.Partida;
import batallanaval.modelo.ResultadoDisparo;
import batallanaval.modelo.TableroFlota;
import batallanaval.modelo.TableroRastreo;

public class ControladorDisparo {

    private final Partida partida;

    public ControladorDisparo(Partida partida) {
        this.partida = partida;
    }

    public void disparar(int fila, int columna) {
        if (partida.isTerminada()) {
            return;
        }
        Jugador tirador = partida.getJugadorEnTurno();
        Coordenada objetivo = new Coordenada(fila, columna);

        if (!objetivo.estaDentro(TableroFlota.TAMANO)) {
            partida.rechazarDisparo("La coordenada se sale del tablero");
            return;
        }
        TableroRastreo rastreo = tirador.getTableroRastreo();
        if (!rastreo.esValida(objetivo)) {
            partida.rechazarDisparo("Ya disparaste a " + objetivo + ". Elige otra casilla");
            return;
        }

        Disparo disparo = partida.tableroFlotaRival().recibirDisparo(objetivo);
        rastreo.marcar(disparo);
        partida.registrarDisparo(disparo);

        if (disparo.resultado() == ResultadoDisparo.AGUA) {
            partida.cederTurno();
        }
    }
}
