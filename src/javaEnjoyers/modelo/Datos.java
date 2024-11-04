package javaEnjoyers.modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Datos {

    // Listas de entidades gestionadas por la aplicación
    private ArrayList<Socio> socios;
    private ArrayList<Inscripcion> inscripciones;
    private ArrayList<Excursion> excursiones;
    private ArrayList<Seguro> seguros;
    private ArrayList<Federacion> federaciones;

    // Constructor que precarga algunos datos iniciales
    public Datos() {
        socios = new ArrayList<>();
        inscripciones = new ArrayList<>();
        excursiones = new ArrayList<>();
        seguros = new ArrayList<>();
        federaciones = new ArrayList<>();

        // Inicializar los datos precargados
        inicializarDatos();
    }

    // Getters y Setters
    public ArrayList<Socio> getSocios() {
        return socios;
    }
    public ArrayList<Inscripcion> getInscripciones() {
        return inscripciones;
    }
    public ArrayList<Excursion> getExcursiones() {
        return excursiones;
    }
    public ArrayList<Seguro> getSeguros() {
        return seguros;
    }
    public ArrayList<Federacion> getFederaciones() {
        return federaciones;
    }

    // Metodo para inicializar datos precargados
    private void inicializarDatos() {
        // Precargar seguros
        seguros.add(new Seguro(TipoSeguro.BASICO, 20.0));
        seguros.add(new Seguro(TipoSeguro.COMPLETO, 50.0));

        // Precargar federaciones
        federaciones.add(new Federacion("FED001", "Federación de Montaña"));
        federaciones.add(new Federacion("FED002", "Federación de Senderismo"));

        // Precargar socios (2 de cada tipo)
        // Socios Estándar
        SocioEstandar socio1 = new SocioEstandar("SOC001", "Juan Pérez", "12345678A", seguros.get(0));
        SocioEstandar socio2 = new SocioEstandar("SOC002", "Ana García", "87654321B", seguros.get(1));

        // Socios Federados
        SocioFederado socio3 = new SocioFederado("SOC003", "Carlos Martínez", "54321678C", federaciones.get(0));
        SocioFederado socio4 = new SocioFederado("SOC004", "Lucía Fernández", "43218765D", federaciones.get(1));
        SocioFederado socio5 = new SocioFederado("SOC005", "Jordi Cañadillas", "47175802L", federaciones.get(1));

        // Socios Infantiles
        SocioInfantil socio6 = new SocioInfantil("SOC006", "Luis Gutiérrez", "SOC001");
        SocioInfantil socio7 = new SocioInfantil("SOC007", "Laura Rodríguez", "SOC003");

        // Agregar socios a la lista
        socios.add(socio1);
        socios.add(socio2);
        socios.add(socio3);
        socios.add(socio4);
        socios.add(socio5);
        socios.add(socio6);
        socios.add(socio7);

        // Precargar excursiones (3 excursiones)
        Excursion excursion1 = new Excursion("EXC001", "Excursión a la Montaña", LocalDate.of(2024, 11, 15), 2, 100.0);
        Excursion excursion2 = new Excursion("EXC002", "Senderismo por el Bosque", LocalDate.of(2024, 12, 1), 1, 50.0);
        Excursion excursion3 = new Excursion("EXC003", "Excursión a la Playa", LocalDate.of(2024, 11, 20), 3, 150.0);
        Excursion excursion4 = new Excursion("EXC004", "Excursión a la Montaña Vieja", LocalDate.of(2022, 5, 10), 1, 80.0);

        // Agregar excursiones a la lista
        excursiones.add(excursion1);
        excursiones.add(excursion2);
        excursiones.add(excursion3);
        excursiones.add(excursion4);

        // Precargar inscripciones (3 inscripciones)
        Inscripcion inscripcion1 = new Inscripcion("INS001", socio1, excursion1); // Juan Pérez en Excursión a la Montaña
        Inscripcion inscripcion2 = new Inscripcion("INS002", socio3, excursion2); // Carlos Martínez en Senderismo por el Bosque
        Inscripcion inscripcion3 = new Inscripcion("INS003", socio6, excursion3); // Luis Gutiérrez (infantil) en Excursión a la Playa
        Inscripcion inscripcion4 = new Inscripcion("INS004", socio5, excursion4); // Jordi Cañadillas en Excursión a la Montaña Vieja

        // Agregar inscripciones a la lista
        inscripciones.add(inscripcion1);
        inscripciones.add(inscripcion2);
        inscripciones.add(inscripcion3);
        inscripciones.add(inscripcion4);

        // Actualizar las listas de inscripciones de los socios
        socio1.agregarInscripcionSocios(inscripcion1);
        socio3.agregarInscripcionSocios(inscripcion2);
        socio6.agregarInscripcionSocios(inscripcion3);
        socio5.agregarInscripcionSocios(inscripcion4);
    }

    // Métodos para agregar nuevos socios, inscripciones y excursiones
    public void agregarSocio(Socio socio) {
        socios.add(socio);
    }

    public void agregarInscripcion(Inscripcion inscripcion) {
        inscripciones.add(inscripcion);
        inscripcion.getSocio().agregarInscripcionSocios(inscripcion);  // Agregar inscripción al socio correspondiente
    }

    public void agregarExcursion(Excursion excursion) {
        excursiones.add(excursion);
    }

    // Métodos para eliminar socios, inscripciones y excursiones
    public boolean eliminarSocio(Socio socio) {
        return socios.remove(socio);  // Devuelve true si se elimina correctamente
    }

    public boolean eliminarInscripcion(Inscripcion inscripcion) {
        return inscripciones.remove(inscripcion);
    }

    public boolean eliminarExcursion(Excursion excursion) {
        return excursiones.remove(excursion);
    }

    // Métodos para buscar (ejemplos sencillos, se pueden mejorar)
    public Socio buscarSocioPorNumero(String numeroSocio) {
        for (Socio socio : socios) {
            if (socio.getNumeroSocio().equals(numeroSocio)) {
                return socio;
            }
        }
        return null;  // Si no se encuentra el socio
    }

    public Excursion buscarExcursionPorCodigo(String codigoExcursion) {
        for (Excursion excursion : excursiones) {
            if (excursion.getCodigoExcursion().equals(codigoExcursion)) {
                return excursion;
            }
        }
        return null;  // Si no se encuentra la excursión
    }

    public Inscripcion buscarInscripcionPorCodigo(String codigoInscripcion) {
        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getCodigoInscripcion().equals(codigoInscripcion)) {
                return inscripcion;
            }
        }
        return null;  // Si no se encuentra la inscripción
    }

    public ArrayList<Inscripcion> buscarInscripcionesPorSocio(String numeroSocio) {
        ArrayList<Inscripcion> inscripcionesPorSocio = new ArrayList<>();

        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getSocio().getNumeroSocio().equals(numeroSocio)) {
                inscripcionesPorSocio.add(inscripcion);
            }
        }
        return inscripcionesPorSocio;
    }

    public ArrayList<Inscripcion> mostrarInscripcionesPorExcursion(String codigoExcursion) {
        ArrayList<Inscripcion> resultado = new ArrayList<>();

        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getExcursion().getCodigoExcursion().equals(codigoExcursion)) {
                resultado.add(inscripcion);
            }
        }
        return resultado;
    }

    // toString
    @Override
    public String toString() {
        return "Datos: " +
                "\nSocios: " + socios +
                "\nInscripciones: " + inscripciones +
                "\nExcursiones: " + excursiones;
    }
}