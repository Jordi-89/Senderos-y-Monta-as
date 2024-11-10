// src/javaEnjoyers/modelo/dao/SocioFederadoDAO.java
package javaEnjoyers.modelo.dao;

import javaEnjoyers.modelo.Socio;
import javaEnjoyers.modelo.SocioFederado;

import java.util.List;

public interface SocioFederadoDAO extends SocioDAO {
    SocioFederado findByNumeroSocio(String numeroSocio);
    List<Socio> findAll();
    void save(Socio socio);
    void update(Socio socio);
    void delete(String numeroSocio);
}
