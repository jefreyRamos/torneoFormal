package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDate;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;

/**
 * Clase para realizar un banco de pruebas.
 */
public class TorneoTest {
    public static final Logger LOG = Logger.getLogger(TorneoTest.class.getName());

    @Test
    public void datosCompletos() {
       LOG.info("Iniciado test datos completos");

       Torneo torneo = new Torneo("copa del mundo", LocalDate.of(2023, 10,11), LocalDate.of(2023, 8,1), LocalDate.of(2023, 9,15), (byte) 24, (byte) 0, (byte) 0, TipoTorneo.LOCAL);
       assertEquals("copa del mundo", torneo.getNombre());
       assertEquals(LocalDate.of(2023, 10,11), torneo.getFechaInicio());
       assertEquals(LocalDate.of(2023, 8,1), torneo.getFechaInicioInscripciones());
       assertEquals(LocalDate.of(2023, 9,15), torneo.getFechaCierreInscripciones());
       assertEquals(24, torneo.getNumeroParticipantes());
       assertEquals(0, torneo.getLimiteEdad());
       assertEquals(0, torneo.getValorInscripcion());

       LOG.info("Finalizando test datos completos");
    }

    @Test
    public void datosNulos() {
        LOG.info("Iniciando test datos nulos");

        assertThrows(Throwable.class, () -> new Torneo(null, null, null, null, (byte)24, (byte)0, (byte)0, TipoTorneo.LOCAL));

        LOG.info("Finalizando test datos nulos");
    }

    @Test
    public void participantesNegativos() {
        LOG.info("Iniciando test participantes negativos");

        assertThrows(Throwable.class, () -> new Torneo("copa del mundo", LocalDate.of(2023, 10,11), LocalDate.of(2023, 8,1), LocalDate.of(2023, 9,15), (byte) -24, (byte) 0, (byte) 0, TipoTorneo.LOCAL));
         
        LOG.info("Finalizando test participantes negativos");
    }

     @Test
    public void limiteEdadNegativo() {
        LOG.info("Iniciando test limite edad negativos");

        assertThrows(Throwable.class, () -> new Torneo("copa del mundo", LocalDate.of(2023, 10,11), LocalDate.of(2023, 8,1), LocalDate.of(2023, 9,15), (byte) 24, (byte) -1, (byte) 0, TipoTorneo.LOCAL));
         
        LOG.info("Finalizando test limite edad negativos");
    }

    @Test
    public void inscripcionNegativa() {
        LOG.info("Iniciando test inscripción negativa");

        assertThrows(Throwable.class, () -> new Torneo("copa del mundo", LocalDate.of(2023, 10,11), LocalDate.of(2023, 8,1), LocalDate.of(2023, 9,15), (byte) 24, (byte) 0, (byte) -1, TipoTorneo.LOCAL));
         
        LOG.info("Finalizando test inscripción negativa");
    }

    @Test 
    public void inscripciónTardia() {
        LOG.info("Iniciando test inscripción tardia");

        assertThrows(Throwable.class, () -> new Torneo("copa del mundo", LocalDate.of(2023, 10,1), LocalDate.of(2023, 11,1), LocalDate.of(2023, 11,15), (byte) 24, (byte) 0, (byte) 0, TipoTorneo.LOCAL));
            
        LOG.info("Finalizando test inscripción tardia");

    }

    @Test 
    public void cierreInscripcionesAnteriorInicio() {
        LOG.info("Iniciando test cierre inscripciones anterior inicio");

        assertThrows(Throwable.class, () -> new Torneo("copa del mundo", LocalDate.of(2023, 10,1), LocalDate.of(2023, 8,15), LocalDate.of(2023, 8,1), (byte) 24, (byte) 0, (byte) 0, TipoTorneo.LOCAL));
            
        LOG.info("Finalizando test  cierre inscripciones anterior inicio");

    }
}