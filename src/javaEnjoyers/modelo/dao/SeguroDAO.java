// src/javaEnjoyers/modelo/dao/SeguroDAO.java
package javaEnjoyers.modelo.dao;

import javaEnjoyers.modelo.Seguro;
import javaEnjoyers.modelo.TipoSeguro;

public interface SeguroDAO {
    Seguro findByTipoSeguro(TipoSeguro tipoSeguro);
}
