package co.edu.uniquindio.poo;

import java.time.LocalDate;
import java.time.Period;

public class Jugador extends Persona implements Participante{
    public final LocalDate fechaNacimiento;
    private final String genero;
    
    public Jugador(String nombre, String apellido, String email, String celular, LocalDate fechaNacimientos,String string) {
        super(nombre, apellido, email, celular);
        this.fechaNacimiento = fechaNacimientos;
        this.genero = string;
        assert fechaNacimiento != null : "La fecha de nacimiento es requerida";
        assert string != null : "El genero es requerido";

    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    
    public byte calcularEdad(LocalDate fecha){
        return (byte) Period.between(fechaNacimiento, fecha).getYears();
    }

<<<<<<< HEAD
    @Override
    public String toString() {
        return "Jugador: " +getNombre();
    } 

    
=======
    public String getGenero() {
        return genero;
    }

    @Override
    public String getNombreCompleto() {
        throw new UnsupportedOperationException("Unimplemented method 'getNombreCompleto'");
    }

    @Override
    public RegistroEstadistica getEstadisticaRegister() {
        throw new UnsupportedOperationException("Unimplemented method 'getEstadisticaRegister'");
    }
        
>>>>>>> torneo/05
}