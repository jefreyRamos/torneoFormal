package co.edu.uniquindio.poo;

public class Juez extends Persona{
    public final String licencia;

    public Juez(String nombre, String apellido, String email, String celular, String licencia) {
        super(nombre, apellido, email, celular);
        this.licencia = licencia;
    }

    public String getLicencia() {
        return licencia;
    }
}
