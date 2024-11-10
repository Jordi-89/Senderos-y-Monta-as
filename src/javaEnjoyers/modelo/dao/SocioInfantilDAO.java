// src/javaEnjoyers/modelo/dao/SocioInfantilDAO.java
package javaEnjoyers.modelo.dao;

import javaEnjoyers.modelo.Socio;
import javaEnjoyers.modelo.SocioInfantil;

import java.util.List;

public interface SocioInfantilDAO extends SocioDAO {
    SocioInfantil findByNumeroSocio(String numeroSocio);
    List<Socio> findAll();
    void save(Socio socio);
    void update(Socio socio);
    void delete(String numeroSocio);
}
