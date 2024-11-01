
package javaEnjoyers.controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import javaEnjoyers.modelo.*;
import javaEnjoyers.modelo.excepciones.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ControladorTest {

    private Controlador controlador;
    private Datos datos;

    @BeforeEach
    void setUp() {
        // Inicializa un objeto Datos para las pruebas
        datos = new Datos();
        controlador = new Controlador(datos);
    }

    @Test
    void testAgregarSocioEstandar() {
        Seguro seguro = new Seguro(TipoSeguro.BASICO, 100.00);
        controlador.agregarSocioEstandar("001", "Juan Perez", "12345678A", seguro);

        Socio socio = datos.buscarSocioPorNumero("001");
        assertNotNull(socio);
        assertTrue(socio instanceof SocioEstandar);
        assertEquals("Juan Perez", socio.getNombre());
    }

    @Test
    void testAgregarExcursion() {
        controlador.agregarExcursion("E001", "Excursion a la montaña", LocalDate.now(), 3, 150.0);

        Excursion excursion = datos.buscarExcursionPorCodigo("E001");
        assertNotNull(excursion);
        assertEquals("Excursion a la montaña", excursion.getDescripcion());
    }

    @Test
    void testAgregarInscripcion() {
        Seguro seguro = new Seguro(TipoSeguro.BASICO, 100.0);
        controlador.agregarSocioEstandar("001", "Juan Perez", "12345678A", seguro);
        controlador.agregarExcursion("E001", "Excursion a la montaña", LocalDate.now(), 3, 150.0);

        controlador.agregarInscripcion("I001", "001", "E001");

        Inscripcion inscripcion = datos.buscarInscripcionPorCodigo("I001");
        assertNotNull(inscripcion);
        assertEquals("001", inscripcion.getSocio().getNumeroSocio());
        assertEquals("E001", inscripcion.getExcursion().getCodigoExcursion());
    }

    @Test
    void testEliminarInscripcionYaPasada() {
        assertThrows(InscripcionNoEliminableException.class, () -> {
            Seguro seguro = new Seguro(TipoSeguro.BASICO, 20.0);
            controlador.agregarSocioEstandar("001", "Juan Perez", "12345678A", seguro);
            controlador.agregarExcursion("E001", "Excursion a la montaña", LocalDate.now().minusDays(1), 3, 150.0);
            controlador.agregarInscripcion("I001", "001", "E001");

            controlador.eliminarInscripcion("I001"); // Esto debe lanzar la excepción
        });
    }

    @Test
    void testEliminarSocioSinInscripciones() {
        Seguro seguro = new Seguro(TipoSeguro.BASICO, 20.0);
        controlador.agregarSocioEstandar("001", "Juan Perez", "12345678A", seguro);

        boolean resultado = controlador.eliminarSocio("001");
        assertTrue(resultado);
        assertNull(datos.buscarSocioPorNumero("001"));
    }

    @Test
    void testEliminarSocioConInscripciones() {
        Seguro seguro = new Seguro(TipoSeguro.BASICO, 20.0);
        controlador.agregarSocioEstandar("001", "Juan Perez", "12345678A", seguro);
        controlador.agregarExcursion("E001", "Excursion a la montaña", LocalDate.now(), 3, 150.0);
        controlador.agregarInscripcion("I001", "001", "E001");

        boolean resultado = controlador.eliminarSocio("001");
        assertFalse(resultado); // No debe poder eliminarse
    }

    @Test
    void testMostrarSocios() {
        Seguro seguro = new Seguro(TipoSeguro.BASICO, 20.0);
        controlador.agregarSocioEstandar("001", "Juan Perez", "12345678A", seguro);
        controlador.agregarSocioEstandar("002", "Maria Garcia", "87654321B", seguro);

        ArrayList<Socio> socios = controlador.mostrarSocios();
        assertEquals(9, socios.size());//tenemos 7 socios precargados en Datos e instanciamos en el test 2 mas
    }

    @Test
    void testFiltrarExcursionesPorFecha() {
        controlador.agregarExcursion("E001", "Excursion a la montaña", LocalDate.now(), 3, 150.0);
        controlador.agregarExcursion("E002", "Excursion a la playa", LocalDate.now().plusDays(5), 2, 200.0);

        ArrayList<Excursion> excursiones = controlador.filtrarExcursionesPorFecha(LocalDate.now(), LocalDate.now().plusDays(4));
        assertEquals(1, excursiones.size());
        assertEquals("E001", excursiones.get(0).getCodigoExcursion());
    }

    // Puedes agregar más pruebas para otros métodos del controlador

}

