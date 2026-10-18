package batallanaval.modelo;

public class Jugador {

    private final String nombre;
    private final TableroFlota tableroFlota = new TableroFlota();
    private final TableroRastreo tableroRastreo = new TableroRastreo();

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public TableroFlota getTableroFlota() {
        return tableroFlota;
    }

    public TableroRastreo getTableroRastreo() {
        return tableroRastreo;
    }
}
