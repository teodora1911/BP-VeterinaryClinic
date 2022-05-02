package mysql;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import dao.IVeterinarianDAO;

public class MySQLVeterinarianDAO implements IVeterinarianDAO {

    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;

    private void resetAll(){
        connection = null;
        statement = null;
        resultSet = null;
    }
    
    @Override
    public HashMap<String, String> getVeterinariansFullName(){
        HashMap<String, String> veterinariansDetails = new HashMap<>();
        resetAll();

        final String query = "SELECT Name, Surname FROM veterinarian";
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while(resultSet.next()){
                // 1. Column (Name), 2. Column (Surname)
                String name = resultSet.getString(1);
                String surname = resultSet.getString(2);
                veterinariansDetails.put(name, surname);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }

        return veterinariansDetails;
    }

    @Override
    public Integer getVeterinaianID(String name, String surname){
        resetAll();
        String query = "SELECT IDVeterinarian FROM veterinarian v WHERE v.Name=? AND v.Surname=?";
        try{
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, surname);
            resultSet = statement.executeQuery();

            if(resultSet.next()){
                return resultSet.getInt(1);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return null;
    }
}
