package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

public class GenerosTest {
    public static final Logger LOG = Logger.getLogger(TorneoTest.class.getName());

    @Test
    void registrarJuez() {
        LOG.info("iniciando test registrar jugador con inscripciones cerradas");
        var torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15),
                LocalDate.now().plusDays(1), (byte) 24, (byte) 18, 0, TipoTorneo.LOCAL);
        var juez = new Juez("Licencia1", "Juan", "Perez", "juan.perez@email.com", "123456789");

        torneo.registrarJuez(juez);

        assertTrue(torneo.jueces.contains(juez));
        LOG.info("iniciando test registrar jugador con inscripciones cerradas");
    }

    @Test
    void registrarEnfrentamiento() {
        LOG.info("iniciando test registrar jugador con inscripciones cerradas");
        var torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15),
                LocalDate.now().plusDays(1), (byte) 24, (byte) 18, 0, TipoTorneo.LOCAL);
        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var equipo = new Equipo("Uniquidio", representante);
        var jugador1 = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234",
                LocalDate.now().minusYears(18), "masculino");
        var jugador2 = new Jugador("Carlos", "Candela", "chrcandela@email.com", "6067431234",
                LocalDate.now().minusYears(18), "masculino");
        var juez = new Juez("Licencia1", "Juan", "Perez", "juan.perez@email.com", "123456789");

        torneo.registrarJugador(equipo, jugador1);
        torneo.registrarJugador(equipo, jugador2);
        torneo.registrarJuez(juez);

        List<Jugador> equipos = List.of(jugador1, jugador2);
        List<Juez> jueces = List.of(juez);
        var enfrentamiento = new Enfrentamiento("Estadio 1", LocalDate.now(), LocalTime.now(), equipos, jueces);


        torneo.registrarEnfrentamiento(enfrentamiento);

        assertTrue(torneo.enfrentamientos.contains(enfrentamiento));
        LOG.info("iniciando test registrar jugador con inscripciones cerradas");
    }

    @Test
    void obtenerEnfrentamientosEquipo() {
        LOG.info("iniciando test registrar jugador con inscripciones cerradas");
        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15),
                LocalDate.now().plusDays(1), (byte) 24, (byte) 18, 0, TipoTorneo.LOCAL);
        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var equipo1 = new Equipo("Uniquidio", representante);
        var equipo2 = new Equipo("Uniquidi", representante);
        var jugador1 = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234",
                LocalDate.now().minusYears(18), "masculino");
        var jugador2 = new Jugador("Carlos", "Candela", "chrcandela@email.com", "6067431234",
                LocalDate.now().minusYears(18), "masculino");
        var juez = new Juez("Licencia1", "Juan", "Perez", "juan.perez@email.com", "123456789");

        torneo.registrarJugador(equipo1, jugador1);
        torneo.registrarJugador(equipo2, jugador2);
        torneo.registrarJuez(juez);

        List<Jugador> equipos = List.of(jugador1, jugador2);
        List<Juez> jueces = List.of(juez);

        var enfrentamiento1 = new Enfrentamiento("Estadio 1", LocalDate.now(), LocalTime.now(), equipos, jueces); 
        var enfrentamiento2 = new Enfrentamiento("Estadio 2", LocalDate.now(), LocalTime.now(), equipos, jueces);

        torneo.registrarEnfrentamiento(enfrentamiento1);
        torneo.registrarEnfrentamiento(enfrentamiento2);

        List<Enfrentamiento> enfrentamientosEquipo = torneo.obtenerEnfrentamientosEquipo("Christian");

        assertEquals(2, enfrentamientosEquipo.size());
        assertTrue(enfrentamientosEquipo.contains(enfrentamiento1));
        assertTrue(enfrentamientosEquipo.contains(enfrentamiento2));
        LOG.info("iniciando test registrar jugador con inscripciones cerradas");
    }

    @Test
    void obtenerEnfrentamientosJuez() {
        var torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15),
                LocalDate.now().plusDays(1), (byte) 24, (byte) 18, 0, TipoTorneo.LOCAL);
        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var equipo = new Equipo("Uniquidio", representante);
        var jugador1 = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234",
                LocalDate.now().minusYears(18), "masculino");
        var jugador2 = new Jugador("Carlos", "Candela", "chrcandela@email.com", "6067431234",
                LocalDate.now().minusYears(18), "masculino");
        var juez1 = new Juez("Licencia1", "Juan", "Perez", "juan.perez@email.com", "123456789");

        torneo.registrarJugador(equipo, jugador1);
        torneo.registrarJugador(equipo, jugador2);
        torneo.registrarJuez(juez1);

        List<Jugador> equipos = List.of(jugador1, jugador2);
        List<Juez> jueces = List.of(juez1);

        var enfrentamiento1 = new Enfrentamiento("Estadio 1", LocalDate.now(), LocalTime.now(), equipos, jueces);
        var enfrentamiento2 = new Enfrentamiento("Estadio 1", LocalDate.now(), LocalTime.now(), equipos, jueces);

        torneo.registrarEnfrentamiento(enfrentamiento1);
        torneo.registrarEnfrentamiento(enfrentamiento2);

        List<Enfrentamiento> enfrentamientosJuez = torneo.obtenerEnfrentamientosJuez("123456789");

        assertEquals(2, enfrentamientosJuez.size());
        assertTrue(enfrentamientosJuez.contains(enfrentamiento2));
    }
}
