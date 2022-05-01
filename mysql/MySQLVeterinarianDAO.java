package mysql;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;

import dao.IVeterinarianDAO;

public class MySQLVeterinarianDAO implements IVeterinarianDAO {
    
    @Override
    public HashMap<Integer, String> getAllVeterinarians(){
        HashMap<Integer, String> veterinariansDetails = new HashMap<>();
        
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;

        final String query = "SELECT IDVeterinarian, Name, Surname FROM veterinarian";
        try {
            connection = Util.getConnection();
            statement = connection.prepareCall(query);
            resultSet = statement.executeQuery();

            while(resultSet.next()){
                // 1. Column (ID), 2. Column (Name), 3. Column (Surname)
                Integer id = resultSet.getInt(1);
                String details = "dr vet. med. " + resultSet.getString(2) + " " + resultSet.getString(3);
                veterinariansDetails.put(id, details);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(connection, statement, resultSet);
        }

        return veterinariansDetails;
    }
}
