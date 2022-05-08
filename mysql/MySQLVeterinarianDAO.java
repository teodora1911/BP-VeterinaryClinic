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
    private ResultSet rs = null;

    private static Veterinarian currentVeterinarian = null;

    private void resetAll(){
        connection = null;
        preparedStatement = null;
        callableStatement = null;
        rs = null;
    }
    
    @Override
    public List<Veterinarian> getVeterinarians(){
        List<Veterinarian> veterinariansDetails = new ArrayList<>();
        resetAll();

        final String query = "SELECT IDVeterinarian, Name, Surname FROM veterinarian";
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();

            while(rs.next()){
                // 1. Column (IDVeterinarian), 2. Column (Name), 3. Column (Surname)
                veterinariansDetails.add(new Veterinarian(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, rs);
        }

        return veterinariansDetails;
    }

    @Override
    public boolean authenticateVeterinarian(String username, String password){
        resetAll();
        final String query = "{CALL veterinarian_authentication(?, ?, ?, ?, ?, ?)}";

        try{
            connection = DBUtil.getConnection();
            callableStatement = connection.prepareCall(query);

            callableStatement.setString(1, username);
            callableStatement.setString(2, password);
            callableStatement.registerOutParameter(3, Types.TINYINT);
            callableStatement.registerOutParameter(4, Types.INTEGER);
            callableStatement.registerOutParameter(5, Types.VARCHAR);
            callableStatement.registerOutParameter(6, Types.VARCHAR);

            callableStatement.execute();
            if(!callableStatement.getBoolean(3)){
                System.out.println("Nevalidni korisnicko ime i/ili lozinka.");
                return false;
            }
            
            currentVeterinarian = new Veterinarian(callableStatement.getInt(4), 
                                                    callableStatement.getString(5), 
                                                      callableStatement.getString(6));
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, callableStatement, rs);
        }

        return true;
    }

    public static Veterinarian getCurrentVeterinarian(){
        return currentVeterinarian;
    }
}
