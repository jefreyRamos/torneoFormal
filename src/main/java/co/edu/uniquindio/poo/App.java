package co.edu.uniquindio.poo;

import java.time.LocalDate;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        var representante = new Persona("Carlos", "Gomez", "carlos@email.com", "6067351234");
        var equipo = new Equipo("Armenia", null);
        var jugador1 = new Jugador("Bernarda", "Jaramillo", "bjaramillo@email.com", "6067343412", LocalDate.now().minusYears(20));
        var jugador2 = new Jugador("Zully", "Gomez", "zgomez@email.com", "6067343413", LocalDate.now().minusYears(18));
        var jugador3 = new Jugador("Ana Maria", "Calderon", "amcalderon@email.com", "6067343414", LocalDate.now().minusYears(22));

        equipo.registrarJugador(jugador1);
        equipo.registrarJugador(jugador2);
        equipo.registrarJugador(jugador3);

        System.out.println(equipo.jugadores());
        System.out.println(equipo.getJugadoresOrdenados());
    }
    
}
