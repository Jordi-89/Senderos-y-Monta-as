package javaEnjoyers.service;

import javaEnjoyers.modelo.Inscripcion;
import javaEnjoyers.modelo.dao.InscripcionDAO;
import javaEnjoyers.modelo.dao.DAOFactoryProvider;

import java.time.LocalDate;
import java.util.List;

public class InscripcionService {
    private InscripcionDAO inscripcionDAO;

    public InscripcionService() {
        this.inscripcionDAO = DAOFactoryProvider.getDAOFactory().getInscripcionDAO();
    }

    public List<Inscripcion> findAll() {
        return inscripcionDAO.findAll();
    }

    public Inscripcion findByCodigo(String codigoInscripcion) {
        return inscripcionDAO.findByCodigo(codigoInscripcion);
    }

    public void save(Inscripcion inscripcion) {
        inscripcionDAO.save(inscripcion);
    }

    public void delete(String codigoInscripcion) {
        inscripcionDAO.delete(codigoInscripcion);
    }

    public List<Inscripcion> findByNumeroSocio(String numeroSocio) {
        return inscripcionDAO.findByNumeroSocio(numeroSocio);
    }

    public List<Inscripcion> findByExcursion(String codigoExcursion) {
        return inscripcionDAO.findByExcursion(codigoExcursion);
    }

    public List<Inscripcion> findByFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        return inscripcionDAO.findByFecha(fechaInicio, fechaFin);
    }
}
