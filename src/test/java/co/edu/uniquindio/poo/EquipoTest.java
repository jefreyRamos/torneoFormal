package co.edu.uniquindio.poo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;

public class EquipoTest {
    public static final Logger LOG = Logger.getLogger(TorneoTest.class.getName());

    @Test
    public void registrarEquipo() {
        LOG.info("iniciando test registrar el equipo");

        var torneo = new Torneo("copa del mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL);

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var equipo = new Equipo("Uniquidio", representante);

        torneo.registrarEquipo(equipo);

        assertTrue(torneo.getEquipos().contains(equipo));
        assertEquals(1, torneo.getEquipos().size());

        LOG.info("Finalizando test registrar el equipo");

    }

    @Test
    public void nombreEquipoRepetido() {
        LOG.info("iniciando test nombre equipo repetido");

        var torneo = new Torneo("copa del mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().plusDays(15), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL);

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var equipo1 = new Equipo("Uniquidio", representante);
        var equipo2 = new Equipo("Uniquidio", representante);

        torneo.registrarEquipo(equipo1);
        assertThrows(Throwable.class, ()->torneo.registrarEquipo(equipo2));

        LOG.info("Finalizando test nombre equipo repetido");

    }

    @Test
    public void inscripcionesCerraron() {
        LOG.info("iniciando test inscripciones cerraron ");

        var torneo = new Torneo("copa del mundo", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(15), LocalDate.now().minusDays(1), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL);

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var equipo = new Equipo("Uniquidio", representante);

        assertThrows(Throwable.class, ()->torneo.registrarEquipo(equipo));

        LOG.info("Finalizando test inscripciones cerraron");

    }

    @Test
    public void inscripcionesNoAbiertas() {
        LOG.info("iniciando test inscripciones no abiertas ");

        var torneo = new Torneo("copa del mundo", LocalDate.now().plusMonths(1), LocalDate.now().plusDays(1), LocalDate.now().plusDays(15), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL);

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var equipo = new Equipo("Uniquidio", representante);

        assertThrows(Throwable.class, ()->torneo.registrarEquipo(equipo));

        LOG.info("Finalizando test inscripciones no abiertas");

    }


}
