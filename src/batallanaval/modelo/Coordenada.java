package batallanaval.modelo;

/**
 * Casilla del tablero
 */
public record Coordenada(int fila, int columna) {

    public boolean estaDentro(int tamano) {
        return fila >= 0 && fila < tamano && columna >= 0 && columna < tamano;
    }

    @Override
    public String toString() {
        return "" + (char) ('A' + fila) + (columna + 1);
    }
}
