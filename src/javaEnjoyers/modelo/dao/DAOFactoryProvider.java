package javaEnjoyers.modelo.dao;

import javaEnjoyers.modelo.impl_dao.MySQLDAOFactory;

public class DAOFactoryProvider {
    private static DAOFactory daoFactory = new MySQLDAOFactory();

    public static DAOFactory getDAOFactory() {
        return daoFactory;
    }

    // Permite cambiar la implementación del DAOFactory si fuera necesario
    public static void setDAOFactory(DAOFactory factory) {
        daoFactory = factory;
    }
}
