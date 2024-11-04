// src/javaEnjoyers/modelo/dao/ExcursionDAO.java
package javaEnjoyers.modelo.dao;

import javaEnjoyers.modelo.Excursion;
import java.util.List;

public interface ExcursionDAO {
    Excursion findByCodigo(String codigo);
    List<Excursion> findAll();
    void save(Excursion excursion);
    void update(Excursion excursion);
    void delete(String codigo);
}
