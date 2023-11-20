package co.edu.uniquindio.poo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Enfrentamiento {
    private final String lugar;
    private LocalDate fecha;
    private LocalTime hora; 
    public List<Jugador> equipos;
    public List<Juez> jueces;
    public Equipo equipo;
    public int puntosEquipo1;
    public int puntosEquipo2;
    public String estado;

    public Enfrentamiento(String lugar,  LocalDate fecha, LocalTime hora, List<Jugador> equipos, List<Juez> jueces) {
        this.lugar = lugar;
        this.fecha = fecha;
        this.hora = hora;
        this.equipos = equipos;
        this.jueces = jueces;
        this.estado = "PENDIENTE";
    }

    public String iniciarEnfrentamiento() {
        this.estado = "EN JUEGO";
        if(fecha.isAfter(LocalDate.now())){
            return "el enfrentamiento fue aplazado";
        }else if(fecha.isEqual(LocalDate.now())){
            return "el enfrentamiento esta en curso";
        }else 
            return "el enfrentamiento ya finalizo"; 
    }

    public String finalizarEnfrentamiento(int puntosEquipo1, int puntosEquipo2) {
        this.puntosEquipo1 = puntosEquipo1;
        this.puntosEquipo2 = puntosEquipo2;
        this.estado = "FINALIZADO";
        return "el enfrentamiento ya finalizo";
    }

    public String aplazarEnfrentamiento() {
        this.estado = "APLAZADO";
        if(fecha.isAfter(LocalDate.now())){
            return "el enfrentamiento fue aplazado";
        }
        return estado;
    }

    public String getLugar() {
        return lugar;
    }

    public List<Jugador> getEquipos() {
        return equipos;
    }

    public List<Juez> getJueces() {
        return jueces;
    }

    public int getPuntosEquipo1() {
        return puntosEquipo1;
    }

    public void setPuntosEquipo1(int puntosEquipo1) {
        this.puntosEquipo1 = puntosEquipo1;
    }

    public int getPuntosEquipo2() {
        return puntosEquipo2;
    }

    public void setPuntosEquipo2(int puntosEquipo2) {
        this.puntosEquipo2 = puntosEquipo2;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void setEquipos(List<Jugador> equipos) {
        this.equipos = equipos;
    }

    public void setJueces(List<Juez> jueces) {
        this.jueces = jueces;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
    
}
