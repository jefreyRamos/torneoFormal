package co.edu.uniquindio.poo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.Optional;


public class Torneo {

    private final String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaInicioInscripciones;
    private LocalDate fechaCierreInscripciones;
    private final byte numeroParticipantes;
    private final byte limiteEdad;
    private final int valorInscripcion;
    private final TipoTorneo tipoTorneo;
    public final Collection<Equipo> equipos;
    private final Collection<Participante> participantes;
    public final List<Juez> jueces;
    public final List<Enfrentamiento> enfrentamientos;

    public Torneo(String nombre, LocalDate fechaInicio, LocalDate fechaInicioInscripciones,
            LocalDate fechaCierreInscripciones, byte numeroParticipantes, byte limiteEdad, int valorInscripcion,
            TipoTorneo tipoTorneo) {
        assert nombre != null;
        assert numeroParticipantes >= (byte) 0;
        assert limiteEdad >= (byte) 0;
        assert valorInscripcion >= 0;
        assert numeroParticipantes >= 0; 
        setFechaInicio(fechaInicio);
        setFechaInicioInscripciones(fechaInicioInscripciones);
        setFechaCierreInscripciones(fechaCierreInscripciones);

        this.nombre = nombre;
        this.numeroParticipantes = numeroParticipantes;
        this.limiteEdad = limiteEdad;
        this.valorInscripcion = valorInscripcion;
        this.tipoTorneo = tipoTorneo;
        this.equipos = new LinkedList<>();
        this.jueces = new ArrayList<>();
        this.participantes = new LinkedList<>();
        this.enfrentamientos = new ArrayList<>();

    }

    public void registrarJuez(Juez juez) {
        this.jueces.add(juez);
    }

    public void registrarEnfrentamiento(Enfrentamiento enfrentamiento) {
        this.enfrentamientos.add(enfrentamiento);
    }

    public List<Juez> getJueces() {
        return jueces;
    }


    public List<Enfrentamiento> getEnfrentamientos() {
        return enfrentamientos;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaInicioInscripciones() {
        return fechaInicioInscripciones;
    }

    public LocalDate getFechaCierreInscripciones() {
        return fechaCierreInscripciones;
    }

    public byte getNumeroParticipantes() {
        return numeroParticipantes;
    }

    public byte getLimiteEdad() {
        return limiteEdad;
    }

    public int getValorInscripcion() {
        return valorInscripcion;
    }

    public TipoTorneo getTipoTorneo() {
        return tipoTorneo;
    }

    public Collection<Equipo> getEquipos() {
        return Collections.unmodifiableCollection(equipos);
    }

    public void registrarEquipo(Equipo equipo) {
        validarEquipoExistente(equipo);
        validarInscripcionesAbiertas();
        equipos.add(equipo);
    }

    private void validarInscripcionesAbiertas() {
        boolean inscripcionesAbiertas = fechaInicioInscripciones.isBefore(LocalDate.now()) && fechaCierreInscripciones.isAfter(LocalDate.now());
        assert inscripcionesAbiertas : "las inscripciones no están abiertas";
    }

    private void validarEquipoExistente(Equipo equipo){
        boolean existeEquipo = buscarEquipoPorNombre(equipo.nombre()).isPresent();
        assert !existeEquipo :"El equipo ya esta registrado";
    }

    public Optional<Equipo> buscarEquipoPorNombre (String nombre) {
        Predicate<Equipo> condition = equipo -> equipo.nombre().equals(nombre);
        return equipos.stream().filter(condition).findAny();
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        assert fechaInicio != null;
        assert (fechaCierreInscripciones == null || fechaInicio.isAfter(fechaInicioInscripciones))
                && (fechaCierreInscripciones == null || fechaInicio.isAfter(fechaCierreInscripciones));
        this.fechaInicio = fechaInicio;
    }

    public void setFechaInicioInscripciones(LocalDate fechaInicioInscripciones) {
        assert fechaInicioInscripciones != null;
        assert (fechaCierreInscripciones == null || fechaInicioInscripciones.isBefore(fechaCierreInscripciones));
        this.fechaInicioInscripciones = fechaInicioInscripciones;
    }

    public void setFechaCierreInscripciones(LocalDate fechaCierreInscripciones) {
        assert fechaCierreInscripciones != null;
        assert (fechaInicio == null || fechaInicio.isAfter(fechaCierreInscripciones))
                && (fechaInicioInscripciones == null || fechaInicioInscripciones.isBefore(fechaCierreInscripciones));
        this.fechaCierreInscripciones = fechaCierreInscripciones;
    }

    public void registrarJugador(String nombreEquipo, Jugador jugador) {
        var equipo = buscarEquipoPorNombre(nombreEquipo);
        equipo.ifPresent((e)->registrarJugador(e, jugador));
    }

    public void registrarJugador(Equipo equipo, Jugador jugador){
        assert !LocalDate.now().isAfter(fechaCierreInscripciones) : "No se pueden registrar jugadores después de la fecha de cierre";
        validarLimiteEdadJugador(jugador);
        validarJugadorExiste(jugador);
        equipo.registrarJugador(jugador);
    }

    private void validarJugadorExiste(Jugador jugador) {
        boolean existeJugador = buscarJugador(jugador).isPresent();
        assert !existeJugador : "El jugador ya esta registrado";
    }

    public Optional<Jugador> buscarJugador(Jugador jugador) {
        return equipos.stream()
            .map(equipo->equipo.buscarJugador(jugador))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .findAny();
    }

    private void validarLimiteEdadJugador(Jugador jugador) {
        var edadInicioTorneo = jugador.calcularEdad(fechaInicio);
        assert limiteEdad == 0 || limiteEdad >= edadInicioTorneo : "No se pueden registrar jugadores que excedan el limite de edad";
    }

    public List<Enfrentamiento> obtenerEnfrentamientosEquipo(String nombreEquipo) {
        List<Enfrentamiento> enfrentamientosEquipo = new ArrayList<>();
        for (Enfrentamiento enfrentamiento : this.enfrentamientos) {
            for (Jugador jugador : enfrentamiento.equipos) {
                if (jugador.nombre.equals(nombreEquipo)) {
                    enfrentamientosEquipo.add(enfrentamiento);
                }
            }
        }
        return enfrentamientosEquipo;
    }

    public List<Enfrentamiento> obtenerEnfrentamientosJuez(String numeroLicencia) {
        List<Enfrentamiento> enfrentamientosJuez = new ArrayList<>();
        for (Enfrentamiento enfrentamiento : this.enfrentamientos) {
            for (Juez juez : enfrentamiento.jueces) {
                if (juez.licencia.equals(numeroLicencia)) {
                    enfrentamientosJuez.add(enfrentamiento);
                }
            }
        }
        return enfrentamientosJuez;
    }

    public Map<String, Integer> obtenerEstadisticasEquipos() {
        Map<String, Integer> estadisticasEquipos = new HashMap<>();
        for (Enfrentamiento enfrentamiento : this.enfrentamientos) {
            if (enfrentamiento.estado.equals("FINALIZADO")) {
                for (Jugador jugador : enfrentamiento.equipos) {
                    String nombreEquipo = jugador.nombre;
                    int victorias = 0;
                    int empates = 0;
                    int derrotas = 0;

                    if (enfrentamiento.puntosEquipo1 > enfrentamiento.puntosEquipo2) {
                        if (jugador.equals(enfrentamiento.equipos.get(0))) {
                            victorias++;
                        } else {
                            derrotas++;
                        }
                    } else if (enfrentamiento.puntosEquipo1 < enfrentamiento.puntosEquipo2) {
                        if (jugador.equals(enfrentamiento.equipos.get(1))) {
                            victorias++;
                        } else {
                            derrotas++;
                        }
                    } else {
                        empates++;
                    }
                    if (estadisticasEquipos.containsKey(nombreEquipo)) {
                        estadisticasEquipos.put(nombreEquipo,
                                estadisticasEquipos.get(nombreEquipo) + victorias + empates + derrotas);
                    } else {
                        estadisticasEquipos.put(nombreEquipo, victorias + empates + derrotas);
                    }
                }
            }
        }
        return estadisticasEquipos;
    }
    
    public int obtenerPartidosGanadosPorEquipo(String nombreEquipo) {
        int partidosGanados = 0;
        for (Enfrentamiento enfrentamiento : this.enfrentamientos) {
            if (enfrentamiento.estado.equals("FINALIZADO")) {
                for (Jugador jugador : enfrentamiento.equipos) {
                    if (jugador.nombre.equals(nombreEquipo)) {
                        if (enfrentamiento.puntosEquipo1 > enfrentamiento.puntosEquipo2 && jugador.equals(enfrentamiento.equipos.get(0))) {
                            partidosGanados++;
                        } else if (enfrentamiento.puntosEquipo1 < enfrentamiento.puntosEquipo2 && jugador.equals(enfrentamiento.equipos.get(1))) {
                            partidosGanados++;
                        }
                    }
                }
            }
        }
        return partidosGanados;
    }

    public void registrarEstadisticaParticipante(Participante participante, ValorEstadistica valorEstadistica) {
        final var participanteRegistrado = buscarParticipante(participante);
        participanteRegistrado.registrarEstadistica(valorEstadistica);
    }
    public Collection<ValorEstadistica> obtenerEstadisticas(Participante participante) {
        final var participanteRegistrado = buscarParticipante(participante);
        return participanteRegistrado.getEstadisticas();
    }

    private Equipo buscarParticipante(Participante participante) {
        Objects.requireNonNull(participante,"El participante es requerido");
        var participanteRegistrado = buscarEquipoPorNombre(participante.getNombreCompleto());
        assert String( participanteRegistrado.isPresent() ,"El participante no esta registrado");
        return participanteRegistrado.get();
    }

    private boolean String(boolean present, String string) {
        return false;
    }

    public Optional<Participante> buscarParticipantePorNombre(String nombre){
        Predicate<Participante> condicion = participante->participante.getNombreCompleto().equals(nombre);
        return participantes.stream().filter(condicion).findAny();
    }
}