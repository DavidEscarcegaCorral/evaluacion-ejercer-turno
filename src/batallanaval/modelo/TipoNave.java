package batallanaval.modelo;

public enum TipoNave {
    PORTAAVIONES("Portaaviones", 4),
    CRUCERO("Crucero", 3),
    SUBMARINO("Submarino", 2),
    BARCO("Barco", 1);

    private final String nombre;
    private final int longitud;

    TipoNave(String nombre, int longitud) {
        this.nombre = nombre;
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public int getLongitud() {
        return longitud;
    }
}
