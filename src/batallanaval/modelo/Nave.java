package batallanaval.modelo;

import java.util.List;

public class Nave {

    private final TipoNave tipo;
    private final List<Coordenada> casillas;
    private int impactos;

    public Nave(TipoNave tipo, List<Coordenada> casillas) {
        this.tipo = tipo;
        this.casillas = List.copyOf(casillas);
    }

    public boolean ocupa(Coordenada coordenada) {
        return casillas.contains(coordenada);
    }

    public void registrarImpacto() {
        impactos++;
    }

    public EstadoNave estado() {
        if (impactos == 0) {
            return EstadoNave.SIN_DANO;
        }
        return impactos >= tipo.getLongitud() ? EstadoNave.HUNDIDA : EstadoNave.AVERIADA;
    }

    public TipoNave getTipo() {
        return tipo;
    }
}
