package batallanaval.modelo;

public enum EstadoNave {
    SIN_DANO("Sin daños"),
    AVERIADA("Averiada"),
    HUNDIDA("Hundida");

    private final String texto;

    EstadoNave(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }
}
