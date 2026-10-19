package batallanaval.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Tablero dde naves del jugador
 */
public class TableroFlota {

    public static final int TAMANO = 10;

    private final List<Nave> naves = new ArrayList<>();

    // Coloca una nave en horizontal en la casilla principal
    public void colocarNave(TipoNave tipo, Coordenada inicio) {
        List<Coordenada> casillas = new ArrayList<>();

        for (int i = 0; i < tipo.getLongitud(); i++) {
            casillas.add(new Coordenada(inicio.fila(), inicio.columna() + i));
        }

        naves.add(new Nave(tipo, casillas));
    }

    public Disparo recibirDisparo(Coordenada objetivo) {
        Nave nave = naveEn(objetivo);
        if (nave == null) {
            return new Disparo(objetivo, ResultadoDisparo.AGUA, null);
        }
        nave.registrarImpacto();
        return new Disparo(objetivo, ResultadoDisparo.IMPACTO, nave);
    }

    public boolean todasHundidas() {
        for (Nave nave : naves) {
            if (nave.estado() != EstadoNave.HUNDIDA) {
                return false;
            }
        }
        return true;
    }

    private Nave naveEn(Coordenada coordenada) {
        for (Nave nave : naves) {
            if (nave.ocupa(coordenada)) {
                return nave;
            }
        }
        return null;
    }
}
