package javaEnjoyers.service;

import javaEnjoyers.modelo.Excursion;
import javaEnjoyers.modelo.dao.ExcursionDAO;
import javaEnjoyers.modelo.dao.DAOFactoryProvider;

import java.time.LocalDate;
import java.util.List;

public class ExcursionService {
    private ExcursionDAO excursionDAO;

    public ExcursionService() {
        this.excursionDAO = DAOFactoryProvider.getDAOFactory().getExcursionDAO();
    }

    public List<Excursion> findAll() {
        return excursionDAO.findAll();
    }

    public Excursion findByCodigo(String codigoExcursion) {
        return excursionDAO.findByCodigo(codigoExcursion);
    }

    public List<Excursion> findByFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        return excursionDAO.findByFecha(fechaInicio, fechaFin);
    }

    public void save(Excursion excursion) {
        excursionDAO.save(excursion);
    }

    public void update(Excursion excursion) {
        excursionDAO.update(excursion);
    }

    public void delete(String codigoExcursion) {
        excursionDAO.delete(codigoExcursion);
    }
}
