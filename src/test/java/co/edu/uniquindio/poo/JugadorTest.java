package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

public class JugadorTest {
    public static final Logger LOG = Logger.getLogger(TorneoTest.class.getName());

    @Test
    public void registrarJugadorEquipo() {
        LOG.info("iniciando test registrar jugador equipo");

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var equipo = new Equipo("Uniquidio", representante);
        var jugador = new Jugador("christian", "candela", "chrcandela@email.com", "6067432334",LocalDate.now().minusYears(15), "masculino");

        equipo.registrarJugador(jugador);

        assertTrue(equipo.jugadores().contains(jugador));
        assertEquals(1, equipo.jugadores().size());

        LOG.info("Finalizando test registrar jugador equipo");

    }

    @Test
    public void registrarJugadorTorneo() {
        LOG.info("iniciando test registrar jugador torneo");

        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte)24, (byte)18, 0,TipoTorneo.LOCAL);

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var equipo = new Equipo("Uniquindio", representante);
        var jugador = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234",LocalDate.now().minusYears(15), "masculino");

        torneo.registrarEquipo(equipo);
        torneo.registrarJugador("Uniquindio",jugador);

        assertTrue(equipo.jugadores().contains(jugador));
        assertEquals(1, equipo.jugadores().size());

        LOG.info("Finalizando test registrar jugador torneo");

    }

    @Test
    public void registrarJugadorConLimiteEdad() {
        LOG.info("iniciando test registrar jugador con limite de edad");

        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte)24, (byte)18, 0, TipoTorneo.LOCAL);

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var equipo = new Equipo("Uniquindio", representante);
        var jugador = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234",LocalDate.now().minusYears(21), "masculino");

        torneo.registrarEquipo(equipo);
        assertThrows(Throwable.class, ()-> torneo.registrarJugador("Uniquindio", jugador));

        LOG.info("Finalizando test registrar jugador con limite de edad");

    }

    @Test
    public void registrarJugadorInscripcionesCerradas() {
        LOG.info("iniciando test registrar jugador con inscripciones cerradas");

        Torneo torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(1), (byte)24, (byte)18, 0, TipoTorneo.LOCAL);

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var equipo = new Equipo("Uniquindio", representante);
        var jugador = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234",LocalDate.now().minusYears(21), "masculino");

        torneo.registrarEquipo(equipo);
        torneo.setFechaCierreInscripciones(LocalDate.now().minusDays(1));
        assertThrows(Throwable.class, ()-> torneo.registrarJugador("Uniquindio", jugador));

        LOG.info("Finalizando test registrar jugador con inscripciones cerradas");

    }

    @Test
    public void registrarJugadorRepetido() {
        LOG.info("iniciando test registrar jugador repetido");

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var equipo = new Equipo("Uniquindio", representante);
        var jugador1 = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234",LocalDate.now().minusYears(21), "masculino");
        var jugador2 = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234",LocalDate.now().minusYears(21), "masculino");
        

        equipo.registrarJugador(jugador1);
        assertThrows(Throwable.class, ()-> equipo.registrarJugador( jugador2));

        LOG.info("Finalizando test registrar jugador repetido");

    }

    @Test
    public void registrarJugadoresRepetidosTorneo() {
        LOG.info("Inicio de prueba registrar jugadores repetidos torneo");

        var torneo = new Torneo("Copa Mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte)24, (byte)18, 0,TipoTorneo.LOCAL);

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var equipo = new Equipo("Uniquindio", representante);
        var equipo2 = new Equipo("Quindío", representante);
        torneo.registrarEquipo(equipo);
        torneo.registrarEquipo(equipo2);

        var jugador1 = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234",LocalDate.now().minusYears(15), "masculino");
        var jugador2 = new Jugador("Christian", "Candela", "ccandela@email.com", "6067431235",LocalDate.now().minusYears(15), "masculino");
                
        torneo.registrarJugador("Uniquindio",jugador1);
        assertThrows(Throwable.class,()->torneo.registrarJugador("Quindío",jugador2));

        LOG.info("Fin de prueba registrar jugadores repetidos torneo");
    } 

}