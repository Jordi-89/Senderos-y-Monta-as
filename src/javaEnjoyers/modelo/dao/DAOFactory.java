package javaEnjoyers.modelo.dao;

public interface DAOFactory {
    SocioEstandarDAO getSocioEstandarDAO();
    SocioFederadoDAO getSocioFederadoDAO();
    SocioInfantilDAO getSocioInfantilDAO();
    ExcursionDAO getExcursionDAO();
    InscripcionDAO getInscripcionDAO();
    FederacionDAO getFederacionDAO();
}
