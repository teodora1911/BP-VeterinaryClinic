package mysql;

import java.sql.SQLException;
import java.sql.Types;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.IVeterinarianDAO;
import dto.Veterinarian;

public class MySQLVeterinarianDAO implements IVeterinarianDAO {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private CallableStatement callableStatement = null;
    private ResultSet resultSet = null;

    private static Integer currentVeterinarianID = null;

    private void resetAll(){
        connection = null;
        preparedStatement = null;
        callableStatement = null;
        resultSet = null;
    }
    
    @Override
    public List<Veterinarian> getVeterinarians(){
        List<Veterinarian> veterinariansDetails = new ArrayList<>();
        resetAll();

        final String query = "SELECT IDVeterinarian, Name, Surname FROM veterinarian";
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                // 1. Column (IDVeterinarian), 2. Column (Name), 3. Column (Surname)\
                Integer id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                veterinariansDetails.add(new Veterinarian(id, name, surname));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }

        return veterinariansDetails;
    }

    @Override
    public boolean authenticateVeterinarian(String username, String password){
        resetAll();
        final String query = "{CALL veterinarian_authentication(?, ?, ?, ?)}";

        try{
            connection = DBUtil.getConnection();
            callableStatement = connection.prepareCall(query);

            callableStatement.setString(1, username);
            callableStatement.setString(2, password);
            callableStatement.registerOutParameter(3, Types.TINYINT);
            callableStatement.registerOutParameter(4, Types.INTEGER);

            callableStatement.execute();
            if(!callableStatement.getBoolean(3)){
                System.out.println("Nevalidni korisnicko ime i/ili lozinka.");
                return false;
            }
            currentVeterinarianID = callableStatement.getInt(4);
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, callableStatement, resultSet);
        }

        return true;
    }

    public static Integer getCurrentVeterinarianID(){
        return (currentVeterinarianID == null) ? null : Integer.valueOf(currentVeterinarianID.intValue());
    }
}
