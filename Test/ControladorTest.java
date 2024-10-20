import javaEnjoyers.controlador.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import javaEnjoyers.modelo.*;

class ControladorTest {

    private Datos datos;
    private Controlador controlador;

    @BeforeEach
    void setUp() {
        // Inicializar la clase Datos y Controlador antes de cada prueba
        datos = new Datos();
        controlador = new Controlador(datos);
    }

    @Test
    void testAgregarSocioEstandar() {
        // Agregar un socio estándar
        controlador.agregarSocioEstandar("SOC011", "Diego López", "12345678Z", new Seguro(TipoSeguro.BASICO, 20.0));

        // Verificar que se haya agregado correctamente
        Socio socio = datos.buscarSocioPorNumero("SOC011");
        assertNotNull(socio, "El socio debería haber sido agregado");
        assertEquals("Diego López", socio.getNombre(), "El nombre del socio debería ser Diego López");
    }

    @Test
    void testAgregarSocioFederado() {
        Federacion federacion = new Federacion("FED003", "Federación de Esquí");
        controlador.agregarSocioFederado("SOC012", "María Gómez", "87654321X", federacion);

        Socio socio = datos.buscarSocioPorNumero("SOC012");
        assertNotNull(socio, "El socio debería haber sido agregado");
        assertEquals("María Gómez", socio.getNombre(), "El nombre del socio debería ser María Gómez");
    }

    @Test
    void testAgregarInscripcion() {
        // Preparar datos para la inscripción
        controlador.agregarSocioEstandar("SOC013", "Carlos Pérez", "23456789A", new Seguro(TipoSeguro.COMPLETO, 50.0));
        controlador.agregarExcursion("EXC004", "Excursión a la Montaña", LocalDate.of(2024, 11, 15), 2, 100.0);

        // Agregar inscripción
        controlador.agregarInscripcion("INS004", "SOC013", "EXC004");

        // Verificar que la inscripción haya sido agregada
        ArrayList<Inscripcion> inscripciones = datos.getInscripciones();
        assertEquals(4, inscripciones.size(), "Debería haber cuatro inscripciones");
    }

    @Test
    void testEliminarSocio() {
        controlador.agregarSocioEstandar("SOC014", "Luis Ruiz", "34567890B", new Seguro(TipoSeguro.BASICO, 20.0));

        // Intentar eliminar el socio
        boolean eliminado = controlador.eliminarSocio("SOC014");

        // Verificar que el socio haya sido eliminado
        assertTrue(eliminado, "El socio debería haber sido eliminado");
        assertNull(datos.buscarSocioPorNumero("SOC014"), "El socio no debería existir después de ser eliminado");
    }

    @Test
    void testEliminarSocio_Inscrito() {
        controlador.agregarSocioEstandar("SOC015", "Ana Torres", "45678901C", new Seguro(TipoSeguro.COMPLETO, 50.0));
        controlador.agregarExcursion("EXC005", "Senderismo", LocalDate.of(2024, 12, 1), 1, 50.0);
        controlador.agregarInscripcion("INS005", "SOC015", "EXC005");

        // Intentar eliminar el socio que está inscrito
        boolean eliminado = controlador.eliminarSocio("SOC015");

        // Verificar que no se pueda eliminar
        assertFalse(eliminado, "No se debería poder eliminar el socio que está inscrito");
        assertNotNull(datos.buscarSocioPorNumero("SOC015"), "El socio debería existir aún");
    }

    @Test
    void testEliminarInscripcion() {
        controlador.agregarSocioEstandar("SOC016", "Roberto Méndez", "56789012D", new Seguro(TipoSeguro.BASICO, 20.0));
        controlador.agregarExcursion("EXC006", "Caminata Nocturna", LocalDate.of(2024, 11, 20), 1, 30.0);
        controlador.agregarInscripcion("INS006", "SOC016", "EXC006");

        // Eliminar inscripción
        boolean eliminado = controlador.eliminarInscripcion("INS006");

        // Verificar que la inscripción haya sido eliminada
        assertTrue(eliminado, "La inscripción debería haber sido eliminada");
        assertNull(datos.buscarInscripcionPorCodigo("INS006"), "La inscripción no debería existir después de ser eliminada");
    }

    @Test
    void testEliminarExcursion() {
        controlador.agregarExcursion("EXC007", "Excursión a la Costa", LocalDate.of(2024, 12, 15), 1, 100.0);

        // Eliminar la excursión
        boolean eliminado = controlador.eliminarExcursion("EXC007");

        // Verificar que la excursión haya sido eliminada
        assertTrue(eliminado, "La excursión debería haber sido eliminada");
        assertNull(datos.buscarExcursionPorCodigo("EXC007"), "La excursión no debería existir después de ser eliminada");
    }

    @Test
    void testFiltrarExcursionesPorFecha() {
        controlador.agregarExcursion("EXC008", "Excursión a la Selva", LocalDate.of(2024, 11, 10), 2, 150.0);
        controlador.agregarExcursion("EXC009", "Excursión al Lago", LocalDate.of(2024, 12, 20), 3, 200.0);

        ArrayList<Excursion> excursionesFiltradas = controlador.filtrarExcursionesPorFecha(LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 15));

        // Verificar que se obtienen las excursiones correctas
        assertEquals(1, excursionesFiltradas.size(), "Debería haber una excursión filtrada");
        assertEquals("Excursión a la Selva", excursionesFiltradas.get(0).getDescripcion(), "La excursión filtrada debería ser 'Excursión a la Selva'");
    }

    @Test
    void testCalcularFacturaMensual() {
        controlador.agregarSocioEstandar("SOC017", "Jorge Medina", "67890123E", new Seguro(TipoSeguro.BASICO, 20.0));

        double factura = controlador.calcularFacturaMensual("SOC017");

        // Verificar que la factura se calcule correctamente
        assertEquals(20.0, factura, "La factura mensual debería ser de 20.0");
    }

    @Test
    void testMostrarSocios() {
        controlador.agregarSocioEstandar("SOC018", "Cristina Silva", "78901234F", new Seguro(TipoSeguro.COMPLETO, 50.0));

        ArrayList<Socio> socios = controlador.mostrarSocios();
        assertEquals(8, socios.size(), "Debería haber 8 socios en la lista (incluyendo los precargados)");
    }

    @Test
    void testMostrarFederaciones() {
        ArrayList<Federacion> federaciones = controlador.mostrarFederaciones();
        assertEquals(2, federaciones.size(), "Debería haber 2 federaciones en la lista precargada");
    }
}
