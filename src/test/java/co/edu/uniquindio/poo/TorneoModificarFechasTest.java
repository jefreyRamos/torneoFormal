package co.edu.uniquindio.poo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDate;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;

/**
 * Clase para realizar un banco de pruebas.
 */
public class TorneoModificarFechasTest {
    public static final Logger LOG = Logger.getLogger(TorneoTest.class.getName());

    @Test
    public void modificarFechaInicio() {
        LOG.info("iniciando test modificar fecha inicio");

        Torneo torneo = new Torneo("copa del mundo", LocalDate.of(2023, 10,1), LocalDate.of(2023, 8,1), LocalDate.of(2023, 9,15), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL);

        torneo.setFechaInicio(LocalDate.of(2023, 10,12));
        assertEquals(LocalDate.of(2023, 10,12), torneo.getFechaInicio());

        LOG.info("finalizando test modificar fecha inicio");
    }

    @Test
    public void modificarFechaInicioNull() {
        LOG.info("iniciando test modificar fecha inicio null");

        Torneo torneo = new Torneo("copa del mundo", LocalDate.of(2023, 10,1), LocalDate.of(2023, 8,1), LocalDate.of(2023, 9,15), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL);

        assertThrows(Throwable.class, ()-> torneo.setFechaInicio(null));

        LOG.info("Finalizando test modificar fecha inicio null");
    }

    @Test
    public void modificarFechaInicioAnteriorInscripciones() {
        LOG.info("iniciando test modificar fecha inicio anterior inscripciones");

        Torneo torneo = new Torneo("copa del mundo", LocalDate.of(2023, 10,1), LocalDate.of(2023, 8,1), LocalDate.of(2023, 9,15), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL);
        assertThrows(Throwable.class, ()-> torneo.setFechaInicio(LocalDate.of(2023, 7,1)));

        LOG.info("Finalizando test modificar fecha inicio anterior inscripciones");
    }

    @Test
    public void modificarFechaInicioInscripciones() {
        LOG.info("iniciando test modificar fecha inicio inscripciones");

        Torneo torneo = new Torneo("copa del mundo", LocalDate.of(2023, 10,1), LocalDate.of(2023, 8,1), LocalDate.of(2023, 9,15), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL);

        torneo.setFechaInicioInscripciones(LocalDate.of(2023, 8,10));
        assertEquals(LocalDate.of(2023, 8,10), torneo.getFechaInicioInscripciones());

        LOG.info("Finalizando test modificar fecha inicio inscripciones");
    }

    @Test
    public void modificarFechaInicioInscripcionesNull() {
        LOG.info("iniciando test modificar fecha inicio inscripciones null");

        Torneo torneo = new Torneo("copa del mundo", LocalDate.of(2023, 10,1), LocalDate.of(2023, 8,1), LocalDate.of(2023, 9,15), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL);

        assertThrows(Throwable.class, ()-> torneo.setFechaInicioInscripciones(null));

        LOG.info("Finalizando test modificar fecha inicio inscripciones null");
    }

    @Test
    public void modificarFechaInicioInscripcionesPosteriorFechaCierre() {
        LOG.info("iniciando test modificar fecha inicio inscripciones posterior fecha cierre");

        Torneo torneo = new Torneo("copa del mundo", LocalDate.of(2023, 10,1), LocalDate.of(2023, 8,1), LocalDate.of(2023, 9,15), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL);

        assertThrows(Throwable.class, ()-> torneo.setFechaInicioInscripciones(LocalDate.of(2023, 9,16)));

        LOG.info("Finalizando test modificar fecha inicio inscripciones posterior fecha cierre");
    }

    @Test
    public void modificarFechaCierreInscripciones() {
        LOG.info("iniciando test modificar fecha cierre inscripciones");

        Torneo torneo = new Torneo("copa del mundo", LocalDate.of(2023, 10,1), LocalDate.of(2023, 8,1), LocalDate.of(2023, 9,15), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL);

        torneo.setFechaCierreInscripciones(LocalDate.of(2023, 9,16));
        assertEquals(LocalDate.of(2023, 9,16), torneo.getFechaCierreInscripciones());

        LOG.info("finalizando test modificar fecha cierre inscripciones");
    }

    @Test
    public void modificarFechaCierreInscripcionesNull() {
        LOG.info("iniciando test modificar fecha cierre inscripciones null");

        Torneo torneo = new Torneo("copa del mundo", LocalDate.of(2023, 10,1), LocalDate.of(2023, 8,1), LocalDate.of(2023, 9,15), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL);

        assertThrows(Throwable.class, ()-> torneo.setFechaCierreInscripciones(null));

        LOG.info("Finalizando test modificar fecha cierre inscripciones null");
    }

    @Test
    public void modificarFechaCierreInscripcionesPosteriorFechaInicio() {
        LOG.info("iniciando test modificar fecha cierre inscripciones posterior fecha inicio");

        Torneo torneo = new Torneo("copa del mundo", LocalDate.of(2023, 10,1), LocalDate.of(2023, 8,1), LocalDate.of(2023, 9,15), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL);

        assertThrows(Throwable.class, ()-> torneo.setFechaCierreInscripciones(LocalDate.of(2023, 10,2)));

        LOG.info("Finalizando test modificar fecha cierre inscripciones posterior fecha inicio");
    }

    @Test
    public void modificarFechaCierreInscripcionesAnteriorFechaInicio() {
        LOG.info("iniciando test modificar fecha cierre inscripciones anterior fecha inicio inscripciones");

        Torneo torneo = new Torneo("copa del mundo", LocalDate.of(2023, 10,1), LocalDate.of(2023, 8,1), LocalDate.of(2023, 9,15), (byte) 24, (byte) 0, 0, TipoTorneo.LOCAL);

        assertThrows(Throwable.class, ()-> torneo.setFechaCierreInscripciones(LocalDate.of(2023, 7,30)));

        LOG.info("Finalizando test modificar fecha cierre inscripciones anterior fecha inicio inscripciones");
    }
  
}
