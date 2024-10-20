
import javaEnjoyers.modelo.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DatosTest {

    private Datos datos;

    @BeforeEach
    void setUp() {
        // Inicializar la clase Datos antes de cada prueba
        datos = new Datos();
    }

    @Test
    void testAgregarYBuscarSocio() {
        // Crear un nuevo socio de prueba
        SocioEstandar nuevoSocio = new SocioEstandar("SOC007", "María López", "11223344E", new Seguro(TipoSeguro.BASICO, 20.0));

        // Agregar el socio
        datos.agregarSocio(nuevoSocio);

        // Buscar el socio por número
        Socio encontrado = datos.buscarSocioPorNumero("SOC007");

        // Verificar que el socio se haya encontrado correctamente
        assertNotNull(encontrado, "El socio debería haber sido encontrado");
        assertEquals("María López", encontrado.getNombre(), "El nombre del socio debería ser María López");
    }

    @Test
    void testEliminarSocio() {
        // Crear y agregar un socio de prueba
        SocioEstandar socio = new SocioEstandar("SOC008", "Pedro Martínez", "22334455F", new Seguro(TipoSeguro.COMPLETO, 50.0));
        datos.agregarSocio(socio);

        // Eliminar el socio
        boolean eliminado = datos.eliminarSocio(socio);

        // Verificar que el socio fue eliminado correctamente
        assertTrue(eliminado, "El socio debería haber sido eliminado");
        assertNull(datos.buscarSocioPorNumero("SOC008"), "El socio no debería existir después de ser eliminado");
    }

    @Test
    void testEliminarSocio_NoExistente() {
        // Crear un socio de prueba
        SocioEstandar socio = new SocioEstandar("SOC009", "Ana Pérez", "33445566G", new Seguro(TipoSeguro.BASICO, 20.0));

        // Intentar eliminar un socio que no ha sido agregado
        boolean eliminado = datos.eliminarSocio(socio);

        // Verificar que la eliminación falla
        assertFalse(eliminado, "No se debería poder eliminar un socio que no existe");
    }

    @Test
    void testBuscarSocioPorNumero_NoExistente() {
        // Intentar buscar un socio que no existe
        Socio encontrado = datos.buscarSocioPorNumero("SOC010");

        // Verificar que el socio no se encuentra
        assertNull(encontrado, "El socio no debería ser encontrado");
    }

}