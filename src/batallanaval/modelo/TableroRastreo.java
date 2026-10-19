package batallanaval.modelo;

import java.util.ArrayList;
import java.util.List;

public class TableroRastreo {

    private final List<Disparo> disparos = new ArrayList<>();

    // Una casilla es válida si todavía no se ha disparado a ella
    public boolean esValida(Coordenada objetivo) {
        for (Disparo disparo : disparos) {
            if (disparo.objetivo().equals(objetivo)) {
                return false;
            }
        }
        return true;
    }

    public void marcar(Disparo disparo) {
        disparos.add(disparo);
    }
}
