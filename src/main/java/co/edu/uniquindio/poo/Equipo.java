package co.edu.uniquindio.poo;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.function.Predicate;

public record Equipo(String nombre, Persona representante, Collection<Jugador> jugadores) {

    public Equipo {
        assert representante != null : "El representante no puede ser null";
    }

    public Equipo(String nombre,Persona representante) {
        this(nombre, representante, new LinkedList<>());
    }
    
    public void registrarJugador(Jugador jugador){
        validarJugadorExistente(jugador);
        jugadores.add(jugador);
    }

    private void validarJugadorExistente(Jugador jugador) {
        boolean existeJugador = buscarJugador(jugador).isPresent();
        assert !existeJugador : "El jugador ya esta registrado";
    }

    public Optional<Jugador> buscarJugador(Jugador jugador){
        Predicate<Jugador> nombreIgual = j->j.getNombre().equals(jugador.getNombre());
        Predicate<Jugador> apellidoIgual = j->j.getApellido().equals(jugador.getApellido());
        return jugadores.stream().filter(nombreIgual.and(apellidoIgual)).findAny();
    }

<<<<<<< HEAD
    public Collection<Jugador> getJugadoresOrdenados() {
        return jugadores.stream().sorted(Comparator.comparing(Jugador::getNombre)).toList();
=======
    public Collection<ValorEstadistica> getEstadisticas() {
        return null;
    }

    public void registrarEstadistica(ValorEstadistica valorEstadistica) {
>>>>>>> torneo/05
    }
}