package co.edu.uniquindio.poo;
import java.util.Collection;
import java.util.Optional;

public interface RegistroEstadistica {
    Collection<ValorEstadistica> getEstadisticas();
    Optional<ValorEstadistica> getEstadistica(Estadistica estadistica);
    void registrarEstadistica(ValorEstadistica estadistica);
} 