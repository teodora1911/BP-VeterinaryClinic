package mysql;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.IVeterinarianDAO;

public class MySQLVeterinarianDAO implements IVeterinarianDAO {
    
    @Override
    public List<String> getAllVeterinarians(){
        List<String> veterinarians = new ArrayList<>();
        
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;

        final String query = "SELECT * FROM veterinarian";
        try {
            connection = ConnectionPool.getInstance().checkOut();
            statement = connection.prepareCall(query);
            resultSet = statement.executeQuery();

            while(resultSet.next()){
                // 2. Column (Name), 3. Column (Surname)
                String veterinarian = "dr vet. med. " + resultSet.getString(2) + " " + resultSet.getString(3);
                veterinarians.add(veterinarian);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(connection);
            Util.close(statement);
            Util.close(resultSet);
        }

        return veterinarians;
    }
}
