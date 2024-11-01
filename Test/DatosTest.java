
package javaEnjoyers.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DatosTest {

    private Datos datos;

    @BeforeEach
    void setUp() {
        datos = new Datos();  // Se inicializa antes de cada prueba
    }

    @Test
    void testInicializarDatos() {
        // Verificar que los datos precargados existen
        assertEquals(7, datos.getSocios().size(), "El número de socios debería ser 7");
        assertEquals(4, datos.getExcursiones().size(), "El número de excursiones debería ser 4");
        assertEquals(4, datos.getInscripciones().size(), "El número de inscripciones debería ser 4");
        assertEquals(2, datos.getSeguros().size(), "El número de seguros debería ser 2");
        assertEquals(2, datos.getFederaciones().size(), "El número de federaciones debería ser 2");
    }

    @Test
    void testAgregarSocio() {
        // Crear un nuevo socio y agregarlo
        Seguro seguro = new Seguro(TipoSeguro.BASICO, 20.0);
        Socio nuevoSocio = new SocioEstandar("SOC008", "Pedro González", "11223344Z", seguro);
        datos.agregarSocio(nuevoSocio);

        // Verificar que el nuevo socio se ha agregado correctamente
        assertEquals(8, datos.getSocios().size(), "El número de socios debería ser 8");
        assertEquals("Pedro González", datos.buscarSocioPorNumero("SOC008").getNombre());
    }

    @Test
    void testBuscarSocioPorNumero() {
        // Buscar un socio existente
        Socio socio = datos.buscarSocioPorNumero("SOC001");
        assertNotNull(socio, "El socio con número 'SOC001' debería existir");
        assertEquals("Juan Pérez", socio.getNombre());

        // Buscar un socio no existente
        Socio socioNoExistente = datos.buscarSocioPorNumero("SOC999");
        assertNull(socioNoExistente, "El socio con número 'SOC999' no debería existir");
    }

    @Test
    void testBuscarExcursionPorCodigo() {
        // Buscar una excursión existente
        Excursion excursion = datos.buscarExcursionPorCodigo("EXC001");
        assertNotNull(excursion, "La excursión con código 'EXC001' debería existir");
        assertEquals("Excursión a la Montaña", excursion.getDescripcion());

        // Buscar una excursión no existente
        Excursion excursionNoExistente = datos.buscarExcursionPorCodigo("EXC999");
        assertNull(excursionNoExistente, "La excursión con código 'EXC999' no debería existir");
    }

    @Test
    void testAgregarInscripcion() {
        Socio socio = datos.buscarSocioPorNumero("SOC001");
        Excursion excursion = datos.buscarExcursionPorCodigo("EXC003");
        Inscripcion nuevaInscripcion = new Inscripcion("INS007", socio, excursion);

        datos.agregarInscripcion(nuevaInscripcion);

        // Verificar que la inscripción se ha agregado correctamente
        assertEquals(5, datos.getInscripciones().size(), "El número de inscripciones debería ser 5");
        assertEquals("INS007", datos.buscarInscripcionPorCodigo("INS007").getCodigoInscripcion());
    }

    @Test
    void testEliminarSocio() {
        Socio socioAEliminar = datos.buscarSocioPorNumero("SOC001");

        boolean eliminado = datos.eliminarSocio(socioAEliminar);

        // Verificar que el socio se eliminó correctamente
        assertTrue(eliminado, "El socio debería eliminarse correctamente");
        assertEquals(6, datos.getSocios().size(), "El número de socios debería ser 6");
        assertNull(datos.buscarSocioPorNumero("SOC001"), "El socio eliminado no debería existir más");
    }

    @Test
    void testMostrarInscripcionesPorExcursion() {
        ArrayList<Inscripcion> inscripciones = datos.mostrarInscripcionesPorExcursion("EXC001");

        assertEquals(1, inscripciones.size(), "Debería haber 1 inscripción para la excursión 'EXC001'");
        assertEquals("INS001", inscripciones.get(0).getCodigoInscripcion());
    }
}
