package mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import dao.IAppointmentDAO;
import dto.Appointment;
import dto.PetOwner;

public class MySQLAppointmentDAO  implements IAppointmentDAO {

    private Connection connection = null;
    // private PreparedStatement preparedStatement = null;
    private CallableStatement callableStatement = null;
    private ResultSet resultSet = null;

    private void resetAll(){
        connection = null;
       // preparedStatement = null;
        callableStatement = null;
        resultSet = null;
    }

    @Override
    public boolean addNewAppointment(PetOwner customer, Integer idVeterinarian, String date, String entryReason) {
        resetAll();
        String query = "{CALL add_appointment(?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try{
            connection = DBUtil.getConnection();
            callableStatement = connection.prepareCall(query);

            callableStatement.setString(1, customer.getName());
            callableStatement.setString(2, customer.getSurname());
            callableStatement.setString(3, customer.getEmail());
            callableStatement.setString(4, customer.getPhoneNumber());
            callableStatement.setInt(5, idVeterinarian);
            callableStatement.setString(6, date);
            callableStatement.setString(7, entryReason);
            callableStatement.registerOutParameter(8, Types.TINYINT);
            callableStatement.registerOutParameter(9, Types.VARCHAR);

            callableStatement.execute();
            if(!callableStatement.getBoolean(8)){
                System.out.println(callableStatement.getString(9));
                return false;
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, callableStatement, resultSet);
        }

        return true;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        // TODO: Implement this
        return null;
    }
    
}
