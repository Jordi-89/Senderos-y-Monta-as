// src/javaEnjoyers/modelo/dao/InscripcionDAO.java
package javaEnjoyers.modelo.dao;

import java.time.LocalDate;
import javaEnjoyers.modelo.Inscripcion;
import java.util.List;

public interface InscripcionDAO {
    Inscripcion findByCodigo(String codigo);
    List<Inscripcion> findAll();
    List<Inscripcion> findBySocio(String numeroSocio);
    List<Inscripcion> findByExcursion(String codigoExcursion);
    List<Inscripcion> findByFecha(LocalDate fechaInicio, LocalDate fechaFin);
    void save(Inscripcion inscripcion);
    void delete(String codigo);
}
