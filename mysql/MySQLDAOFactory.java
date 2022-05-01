package mysql;

import dao.DAOFactory;
import dao.IVeterinarianDAO;

public class MySQLDAOFactory extends DAOFactory {
    
    private static MySQLDAOFactory instance = new MySQLDAOFactory();
    private MySQLDAOFactory() { }
    
    public static MySQLDAOFactory getInstance(){
        return instance;
    }

    @Override
    public IVeterinarianDAO getVeterinarianDAO(){
        return new MySQLVeterinarianDAO();
    }
}
