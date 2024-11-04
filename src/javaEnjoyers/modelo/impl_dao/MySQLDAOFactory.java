package javaEnjoyers.modelo.impl_dao;

import javaEnjoyers.modelo.dao.*;

public class MySQLDAOFactory implements DAOFactory {

    @Override
    public SocioEstandarDAO getSocioEstandarDAO() {
        return new SocioEstandarDAOImpl();
    }

    @Override
    public SocioFederadoDAO getSocioFederadoDAO() {
        return new SocioFederadoDAOImpl();
    }

    @Override
    public SocioInfantilDAO getSocioInfantilDAO() {
        return new SocioInfantilDAOImpl();
    }

    @Override
    public ExcursionDAO getExcursionDAO() {
        return new ExcursionDAOImpl();
    }

    @Override
    public InscripcionDAO getInscripcionDAO() {
        return new InscripcionDAOImpl();
    }

    @Override
    public FederacionDAO getFederacionDAO() {
        return new FederacionDAOImpl();
    }

}
