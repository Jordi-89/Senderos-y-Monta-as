// src/javaEnjoyers/modelo/dao/FederacionDAO.java
package javaEnjoyers.modelo.dao;

import javaEnjoyers.modelo.Federacion;

public interface FederacionDAO {
    Federacion findByCodigo(String codigoFederacion);
}
