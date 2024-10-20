package javaEnjoyers.controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import javaEnjoyers.modelo.Datos;
import javaEnjoyers.modelo.Socio;
import javaEnjoyers.modelo.SocioEstandar;
import javaEnjoyers.modelo.SocioFederado;
import javaEnjoyers.modelo.SocioInfantil;
import javaEnjoyers.modelo.Excursion;
import javaEnjoyers.modelo.Inscripcion;
import javaEnjoyers.modelo.Seguro;
import javaEnjoyers.modelo.Federacion;

public class Controlador {

    //Atributo que contiene todos los datos del sistema
    private Datos datos;

    // Constructor
    public Controlador(Datos datos) {
        this.datos = datos;
    }

    // Métodos para gestionar las operaciones del usuario

    // 1. Agregar un socio estándar
    public void agregarSocioEstandar(String numeroSocio, String nombre, String nif, Seguro seguro) {
        SocioEstandar nuevoSocio = new SocioEstandar(numeroSocio, nombre, nif, seguro);
        datos.agregarSocio(nuevoSocio);
    }

    // 2. Agregar un socio federado
    public void agregarSocioFederado(String numeroSocio, String nombre, String nif, Federacion federacion) {
        SocioFederado nuevoSocio = new SocioFederado(numeroSocio, nombre, nif, federacion);
        datos.agregarSocio(nuevoSocio);
    }

    // 3. Agregar un socio infantil
    public void agregarSocioInfantil(String numeroSocio, String nombre, String numeroSocioAdulto) {
        SocioInfantil nuevoSocio = new SocioInfantil(numeroSocio, nombre, numeroSocioAdulto);
        datos.agregarSocio(nuevoSocio);
    }

    // 4. Agregar una excursión
    public void agregarExcursion(String codigo, String descripcion, LocalDate fecha, int numeroDias, double precio) {
        Excursion nuevaExcursion = new Excursion(codigo, descripcion, fecha, numeroDias, precio);
        datos.agregarExcursion(nuevaExcursion);
    }

    // 5. Inscribir a un socio en una excursión
    public void agregarInscripcion(String codigoInscripcion, String numeroSocio, String codigoExcursion) {
        Socio socio = datos.buscarSocioPorNumero(numeroSocio);
        Excursion excursion = datos.buscarExcursionPorCodigo(codigoExcursion);

        if (socio != null && excursion != null) {
            Inscripcion nuevaInscripcion = new Inscripcion(codigoInscripcion, socio, excursion);
            datos.agregarInscripcion(nuevaInscripcion);
        } else {
            System.out.println("Error: El socio o la excursión no existe.");
        }
    }

    // 6. Mostrar todos los socios
    public ArrayList<Socio> mostrarSocios() {
        return datos.getSocios();
    }

    // 7. Mostrar todas las excursiones
    public ArrayList<Excursion> mostrarExcursiones() {
        return datos.getExcursiones();
    }

    // 8. Mostrar todas las inscripciones
    public ArrayList<Inscripcion> mostrarInscripciones() {
        return datos.getInscripciones();
    }


    // 9. Mostrar inscripciones por socio
    public ArrayList<Inscripcion> mostrarInscripcionesPorSocio(String numeroSocio) {
        ArrayList<Inscripcion> inscripciones = datos.getInscripciones();
        ArrayList<Inscripcion> resultado = new ArrayList<>();

        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getSocio().getNumeroSocio().equals(numeroSocio)) {
                resultado.add(inscripcion);
            }
        }
        return resultado;
    }

    // 10. Mostrar inscripciones por excursion
    public ArrayList<Inscripcion> mostrarInscripcionesPorExcursion(String codigoExcursion) {
        return datos.mostrarInscripcionesPorExcursion(codigoExcursion);
    }

    // 11. Eliminar socio
    public boolean eliminarSocio(String numeroSocio) {
        Socio socio = datos.buscarSocioPorNumero(numeroSocio);

        // Un socio solo puede ser eliminado si no está inscrito en ninguna excursión
        if (socio != null && datos.buscarInscripcionesPorSocio(numeroSocio).isEmpty()) {
            return datos.eliminarSocio(socio);
        } else {
            System.out.println("No se puede eliminar el socio, está inscrito en una excursión o no existe.");
            return false;
        }
    }

    // 12. Eliminar inscripción
    public boolean eliminarInscripcion(String codigoInscripcion) {
        // Buscar la inscripción por su código
        Inscripcion inscripcion = datos.buscarInscripcionPorCodigo(codigoInscripcion);

        if (inscripcion != null) {
            // Obtener la fecha de la excursión asociada a la inscripción
            LocalDate fechaExcursion = inscripcion.getExcursion().getFecha();

            // Comprobar si la fecha actual es anterior a la fecha de la excursión
            if (LocalDate.now().isBefore(fechaExcursion)) {
                return datos.eliminarInscripcion(inscripcion);  // Eliminar inscripción si la fecha actual es anterior a la fecha de la excursión
            } else {
                System.out.println("No se puede eliminar la inscripción. La fecha de la excursión ya ha pasado.");
                return false;  // No se puede eliminar si la fecha de la excursión ya pasó
            }
        }
        System.out.println("Inscripción no encontrada.");
        return false;  // Si no se encuentra la inscripción
    }

    // 13. Eliminar excursión
    public boolean eliminarExcursion(String codigoExcursion) {
        Excursion excursion = datos.buscarExcursionPorCodigo(codigoExcursion);

        // Una excursión solo se puede eliminar si no tiene inscripciones asociadas
        if (excursion != null && datos.mostrarInscripcionesPorExcursion(codigoExcursion).isEmpty()) {
            return datos.eliminarExcursion(excursion);
        } else {
            System.out.println("No se puede eliminar la excursión, tiene inscripciones asociadas o no existe.");
            return false;
        }
    }

    // 14. Modificar Seguro de Socio Estandar
    public boolean modificarSeguroSocioEstandar(String numeroSocio, Seguro nuevoSeguro) {
        Socio socio = datos.buscarSocioPorNumero(numeroSocio);

        if (socio != null && socio instanceof SocioEstandar) {
            ((SocioEstandar) socio).setSeguro(nuevoSeguro);
            return true;
        } else {
            System.out.println("El socio no es estándar o no existe.");
            return false;
        }
    }

    // 15. Filtrar excursiones por fecha
    public ArrayList<Excursion> filtrarExcursionesPorFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        ArrayList<Excursion> excursiones = datos.getExcursiones();
        ArrayList<Excursion> resultado = new ArrayList<>();

        for (Excursion excursion : excursiones) {
            if (!excursion.getFecha().isBefore(fechaInicio) && !excursion.getFecha().isAfter(fechaFin)) {
                resultado.add(excursion);
            }
        }
        return resultado;
    }

    // 16. Mostrar federaciones
    public ArrayList<Federacion> mostrarFederaciones() {
        return datos.getFederaciones(); //Metodo que devuelve las federaciones precargadas en Datos
    }

    // 17. Buscar socio por número
    public Socio buscarSocioPorNumero(String numeroSocio) {
        return datos.buscarSocioPorNumero(numeroSocio); // Llamada al metodo en Datos
    }

    // 18. Buscar excursión por código
    public Excursion buscarExcursionPorCodigo(String codigoExcursion) {
        return datos.buscarExcursionPorCodigo(codigoExcursion);
    }

    // 17. Calcular Factura Mensual
    public double calcularFacturaMensual(String numeroSocio) {
        Socio socio = datos.buscarSocioPorNumero(numeroSocio);
        if (socio == null) {
            return -1; // Indica que el socio no existe
        }
        return socio.calcularCuotaMensual();  // Llama al metodo en el modelo
    }

    // 18. Agregar inscripciones de cada socio
    public void agregarInscripcionSocios(String codigoInscripcion, String numeroSocio, String codigoExcursion) {
        Socio socio = datos.buscarSocioPorNumero(numeroSocio);
        Excursion excursion = datos.buscarExcursionPorCodigo(codigoExcursion);

        if (socio != null && excursion != null) {
            Inscripcion nuevaInscripcion = new Inscripcion(codigoInscripcion, socio, excursion);
            datos.agregarInscripcion(nuevaInscripcion);  // Agregar a la lista global de inscripciones
            socio.agregarInscripcionSocios(nuevaInscripcion);  // Agregar a la lista de inscripciones del socio
        } else {
            System.out.println("Error: El socio o la excursión no existe.");
        }
    }

    // 19. Agregar nuevos socios
    public void agregarSocio(Socio nuevoSocio) {
        datos.agregarSocio(nuevoSocio);  // Agregar el nuevo socio a la lista de socios en Datos
    }

    // 20. Mostras inscripciones por fecha
    public ArrayList<Inscripcion> mostrarInscripcionesPorFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        ArrayList<Inscripcion> resultado = new ArrayList<>();

        // Obtener todas las inscripciones
        ArrayList<Inscripcion> inscripciones = datos.getInscripciones();

        // Filtrar por rango de fechas
        for (Inscripcion inscripcion : inscripciones) {
            LocalDate fechaExcursion = inscripcion.getExcursion().getFecha();
            if ((fechaExcursion.isEqual(fechaInicio) || fechaExcursion.isAfter(fechaInicio)) &&
                    (fechaExcursion.isEqual(fechaFin) || fechaExcursion.isBefore(fechaFin))) {
                resultado.add(inscripcion);
            }
        }
        return resultado;  // Retornar las inscripciones que están dentro del rango de fechas
    }

}