package dao;

import mysql.MySQLDAOFactory;

public abstract class DAOFactory {
    
    // geteri za konkretne DAO fabrike
	public abstract IVeterinarianDAO getVeterinarianDAO();

    public static DAOFactory getFactory (DAOFactoryType daoFactoryType) {
		if(daoFactoryType == DAOFactoryType.MySQL) {
			return MySQLDAOFactory.getInstance();
		}
		
        throw new IllegalArgumentException();
	}
}
