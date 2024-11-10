package javaEnjoyers.controlador;

import java.time.LocalDate;
import java.util.List;

import javaEnjoyers.modelo.*;
import javaEnjoyers.modelo.impl_dao.FederacionDAOImpl;
import javaEnjoyers.modelo.impl_dao.SocioEstandarDAOImpl;
import javaEnjoyers.modelo.impl_dao.SocioFederadoDAOImpl;
import javaEnjoyers.modelo.impl_dao.SocioInfantilDAOImpl;
import javaEnjoyers.service.ExcursionService;
import javaEnjoyers.service.InscripcionService;
import javaEnjoyers.service.SocioService;
import javaEnjoyers.modelo.excepciones.InscripcionNoEliminableException;
import javaEnjoyers.modelo.excepciones.SocioNoEncontradoException;

public class Controlador {

    // Servicios que gestionan los datos
    private SocioService socioService;
    private ExcursionService excursionService;
    private InscripcionService inscripcionService;

    // Constructor
    public Controlador() {
        this.socioService = new SocioService(
                new SocioEstandarDAOImpl(),
                new SocioFederadoDAOImpl(),
                new SocioInfantilDAOImpl(),
                new FederacionDAOImpl());
        this.excursionService = new ExcursionService();
        this.inscripcionService = new InscripcionService();
    }

    // 1. Agregar un socio estándar
    public void agregarSocioEstandar(String numeroSocio, String nombre, String nif, Seguro seguro) {
        SocioEstandar nuevoSocio = new SocioEstandar(numeroSocio, nombre, nif, seguro);
        socioService.save(nuevoSocio);
    }

    // 2. Agregar un socio federado
    public void agregarSocioFederado(String numeroSocio, String nombre, String nif, Federacion federacion) {
        SocioFederado nuevoSocio = new SocioFederado(numeroSocio, nombre, nif, federacion);
        socioService.save(nuevoSocio);
    }

    // 3. Agregar un socio infantil
    public void agregarSocioInfantil(String numeroSocio, String nombre, String numeroSocioAdulto) {
        SocioInfantil nuevoSocio = new SocioInfantil(numeroSocio, nombre, numeroSocioAdulto);
        socioService.save(nuevoSocio);
    }

    // 4. Agregar una excursión
    public void agregarExcursion(String codigo, String descripcion, LocalDate fecha, int numeroDias, double precio) {
        Excursion nuevaExcursion = new Excursion(codigo, descripcion, fecha, numeroDias, precio);
        excursionService.save(nuevaExcursion);
    }

    // 5. Inscribir a un socio en una excursión
    public void agregarInscripcion(String codigoInscripcion, String numeroSocio, String codigoExcursion) {
        Socio socio = socioService.findByNumeroSocio(numeroSocio);
        Excursion excursion = excursionService.findByCodigo(codigoExcursion);

        if (socio != null && excursion != null) {
            Inscripcion nuevaInscripcion = new Inscripcion(codigoInscripcion, socio, excursion);
            inscripcionService.save(nuevaInscripcion);
            socio.agregarInscripcionSocios(nuevaInscripcion);
        } else {
            System.out.println("Error: El socio o la excursión no existe.");
        }
    }

    // 6. Mostrar todos los socios
    public List<Socio> mostrarSocios() {
        return socioService.findAll();
    }

    // 7. Mostrar todas las excursiones
    public List<Excursion> mostrarExcursiones() {
        return excursionService.findAll();
    }

    // 8. Mostrar todas las inscripciones
    public List<Inscripcion> mostrarInscripciones() {
        return inscripcionService.findAll();
    }

    // 9. Mostrar inscripciones por socio
    public List<Inscripcion> mostrarInscripcionesPorSocio(String numeroSocio) {
        return inscripcionService.findByNumeroSocio(numeroSocio);
    }

    // 10. Mostrar inscripciones por excursión
    public List<Inscripcion> mostrarInscripcionesPorExcursion(String codigoExcursion) {
        return inscripcionService.findByExcursion(codigoExcursion);
    }

    public List<Inscripcion> mostrarInscripcionesPorFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        return inscripcionService.findByFecha(fechaInicio, fechaFin);
    }


    // 11. Eliminar socio
    public boolean eliminarSocio(String numeroSocio) {
        Socio socio = socioService.findByNumeroSocio(numeroSocio);
        if (socio != null && inscripcionService.findByNumeroSocio(numeroSocio).isEmpty()) {
            socioService.delete(numeroSocio);
            return true;
        } else {
            System.out.println("No se puede eliminar el socio, está inscrito en una excursión o no existe.");
            return false;
        }
    }

    // 12. Eliminar inscripción
    public void eliminarInscripcion(String codigoInscripcion) throws InscripcionNoEliminableException {
        Inscripcion inscripcion = inscripcionService.findByCodigo(codigoInscripcion);

        if (inscripcion != null) {
            LocalDate fechaExcursion = inscripcion.getExcursion().getFecha();
            if (LocalDate.now().isAfter(fechaExcursion)) {
                throw new InscripcionNoEliminableException("Error: No se puede eliminar la inscripción porque la fecha de la excursión ya pasó.");
            } else {
                inscripcionService.delete(codigoInscripcion);
                System.out.println("Inscripción eliminada correctamente.");
            }
        } else {
            throw new InscripcionNoEliminableException("Error: Inscripción no encontrada.");
        }
    }

    // 13. Eliminar excursión
    public boolean eliminarExcursion(String codigoExcursion) {
        Excursion excursion = excursionService.findByCodigo(codigoExcursion);
        if (excursion != null && inscripcionService.findByExcursion(codigoExcursion).isEmpty()) {
            excursionService.delete(codigoExcursion);
            return true;
        } else {
            System.out.println("No se puede eliminar la excursión, tiene inscripciones asociadas o no existe.");
            return false;
        }
    }

    // 14. Modificar Seguro de Socio Estándar
    public boolean modificarSeguroSocioEstandar(String numeroSocio, Seguro nuevoSeguro) {
        Socio socio = socioService.findByNumeroSocio(numeroSocio);
        if (socio instanceof SocioEstandar) {
            ((SocioEstandar) socio).setSeguro(nuevoSeguro);
            socioService.update(socio);
            return true;
        } else {
            System.out.println("El socio no es estándar o no existe.");
            return false;
        }
    }

    // 15. Filtrar excursiones por fecha
    public List<Excursion> filtrarExcursionesPorFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        return excursionService.findByFecha(fechaInicio, fechaFin);
    }

    // 16. Mostrar federaciones
    public List<Federacion> mostrarFederaciones() {
        return socioService.findAllFederaciones();
    }

    // 17. Calcular Factura Mensual
    public double calcularFacturaMensual(String numeroSocio) {
        // Encuentra al socio
        Socio socio = socioService.findByNumeroSocio(numeroSocio);

        if (socio != null) {
            // Carga las inscripciones del socio
            List<Inscripcion> inscripciones = inscripcionService.findByNumeroSocio(numeroSocio);
            socio.setInscripciones(inscripciones); // Metodo que deberías tener en la clase Socio

            // Calcula la cuota mensual
            return socio.calcularCuotaMensual();
        }

        // Si no existe el socio, retorna -1
        return -1;
    }


    public Socio findByNumeroSocio(String numeroSocio) throws SocioNoEncontradoException {
        Socio socio = socioService.findByNumeroSocio(numeroSocio);
        if (socio == null) {
            throw new SocioNoEncontradoException("Socio con número " + numeroSocio + " no encontrado.");
        }
        return socio;
    }

    public Excursion buscarExcursionPorCodigo(String codigo) {
        return excursionService.findByCodigo(codigo); // Llama al servicio correspondiente para buscar la excursión
    }

    public Inscripcion buscarInscripcionPorCodigo(String codigoInscripcion) {
        return inscripcionService.findByCodigo(codigoInscripcion);
    }

}
