package mysql;

import java.sql.SQLException;
import java.sql.Types;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import dao.IVeterinarianDAO;

public class MySQLVeterinarianDAO implements IVeterinarianDAO {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private CallableStatement callableStatement = null;
    private ResultSet resultSet = null;

    private void resetAll(){
        connection = null;
        preparedStatement = null;
        callableStatement = null;
        resultSet = null;
    }
    
    @Override
    public HashMap<String, String> getVeterinariansFullName(){
        HashMap<String, String> veterinariansDetails = new HashMap<>();
        resetAll();

        final String query = "SELECT Name, Surname FROM veterinarian";
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                // 1. Column (Name), 2. Column (Surname)
                String name = resultSet.getString(1);
                String surname = resultSet.getString(2);
                veterinariansDetails.put(name, surname);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }

        return veterinariansDetails;
    }

    @Override
    public Integer getVeterinaianID(String name, String surname){
        resetAll();
        String query = "SELECT IDVeterinarian FROM veterinarian v WHERE v.Name=? AND v.Surname=?";
        try{
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return resultSet.getInt(1);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }
        return null;
    }

    @Override
    public boolean authenticateVeterinarian(String username, String password){
        resetAll();
        String query = "{CALL veterinarian_authentication(?, ?, ?)}";

        try{
            connection = DBUtil.getConnection();
            callableStatement = connection.prepareCall(query);

            callableStatement.setString(1, username);
            callableStatement.setString(2, password);
            callableStatement.registerOutParameter(3, Types.TINYINT);

            callableStatement.execute();
            if(!callableStatement.getBoolean(3)){
                System.out.println("Nevalidni korisnicko ime i/ili lozinka.");
                return false;
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, callableStatement, resultSet);
        }

        return true;
    }
}
