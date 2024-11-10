package javaEnjoyers.modelo;

import javaEnjoyers.modelo.impl_dao.FederacionDAOImpl;
import javaEnjoyers.modelo.impl_dao.SocioEstandarDAOImpl;
import javaEnjoyers.modelo.impl_dao.SocioFederadoDAOImpl;
import javaEnjoyers.modelo.impl_dao.SocioInfantilDAOImpl;
import javaEnjoyers.service.SocioService;
import javaEnjoyers.service.ExcursionService;
import javaEnjoyers.service.InscripcionService;
import java.util.List;

public class Datos {

    private SocioService socioService;
    private ExcursionService excursionService;
    private InscripcionService inscripcionService;

    public Datos() {
        socioService = new SocioService(
                new SocioEstandarDAOImpl(),
                new SocioFederadoDAOImpl(),
                new SocioInfantilDAOImpl(),
                new FederacionDAOImpl()
        );
        excursionService = new ExcursionService();
        inscripcionService = new InscripcionService();
    }

    // Métodos para acceder a los datos a través de los servicios

    // Obtener todos los socios
    public List<Socio> getSocios() {
        return socioService.findAll();
    }

    // Obtener todas las inscripciones
    public List<Inscripcion> getInscripciones() {
        return inscripcionService.findAll();
    }

    // Obtener todas las excursiones
    public List<Excursion> getExcursiones() {
        return excursionService.findAll();
    }

    // Métodos para agregar nuevos socios, inscripciones y excursiones
    public void agregarSocio(Socio socio) {
        socioService.save(socio);
    }

    public void agregarInscripcion(Inscripcion inscripcion) {
        inscripcionService.save(inscripcion);
        inscripcion.getSocio().agregarInscripcionSocios(inscripcion);  // Agregar inscripción al socio
    }

    public void agregarExcursion(Excursion excursion) {
        excursionService.save(excursion);
    }

    // Métodos para eliminar socios, inscripciones y excursiones
    public boolean eliminarSocio(String numeroSocio) {
        socioService.delete(numeroSocio);
        return true; // Considera que el delete retorna un booleano en el servicio
    }

    public boolean eliminarInscripcion(String codigoInscripcion) {
        inscripcionService.delete(codigoInscripcion);
        return true;
    }

    public boolean eliminarExcursion(String codigoExcursion) {
        excursionService.delete(codigoExcursion);
        return true;
    }

    // Métodos para buscar (usando los servicios)
    public Socio buscarSocioPorNumero(String numeroSocio) {
        return socioService.findByNumeroSocio(numeroSocio);
    }

    public Excursion buscarExcursionPorCodigo(String codigoExcursion) {
        return excursionService.findByCodigo(codigoExcursion);
    }

    public Inscripcion buscarInscripcionPorCodigo(String codigoInscripcion) {
        return inscripcionService.findByCodigo(codigoInscripcion);
    }

    public List<Inscripcion> buscarInscripcionesPorSocio(String numeroSocio) {
        return inscripcionService.findByNumeroSocio(numeroSocio);
    }

    public List<Inscripcion> mostrarInscripcionesPorExcursion(String codigoExcursion) {
        return inscripcionService.findByExcursion(codigoExcursion);
    }

    // toString para ver los datos actuales desde la base de datos
    @Override
    public String toString() {
        return "Datos desde BD: " +
                "\nSocios: " + getSocios() +
                "\nInscripciones: " + getInscripciones() +
                "\nExcursiones: " + getExcursiones();
    }
}