// src/javaEnjoyers/modelo/dao/FederacionDAO.java
package javaEnjoyers.modelo.dao;

import javaEnjoyers.modelo.Federacion;

import java.util.List;

public interface FederacionDAO {
    Federacion findByCodigo(String codigoFederacion);
    void save (Federacion federacion);
    List<Federacion> findAll();

}
