package mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import constants.AppointmentsChoices;
import dao.IAppointmentDAO;
import dto.Appointment;
import dto.PetOwner;
import dto.Veterinarian;

public class MySQLAppointmentDAO  implements IAppointmentDAO {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private CallableStatement callableStatement = null;
    private ResultSet rs = null;

    private void resetAll(){
        connection = null;
        preparedStatement = null;
        callableStatement = null;
        rs = null;
    }

    @Override
    public boolean addNewAppointment(PetOwner customer, Veterinarian veterinarian, String date, String entryReason) {
        resetAll();
        String query = "{CALL add_appointment(?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try{
            connection = DBUtil.getConnection();
            callableStatement = connection.prepareCall(query);

            callableStatement.setString(1, customer.getName());
            callableStatement.setString(2, customer.getSurname());
            callableStatement.setString(3, customer.getEmail());
            callableStatement.setString(4, customer.getPhoneNumber());
            callableStatement.setInt(5, veterinarian.getIDVeterinarian());
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
            DBUtil.close(connection, preparedStatement, rs);
        }

        return true;
    }

    @Override
    public List<Appointment> getAppointmentsFrom(Veterinarian veterinarian, AppointmentsChoices choice){
        resetAll();
        List<Appointment> appointments = new ArrayList<>();
        String query;
        switch(choice){
            case Zakazani:
                query = "SELECT * FROM appointments_view av WHERE av.Veterinarian=? AND av.Scheduled=true";
                break;
            case Nezakazani:
                query = "SELECT * FROM appointments_view av WHERE av.Veterinarian=? AND av.Scheduled=false";
                break;
            default:
                query = "SELECT * FROM appointments_view av WHERE av.Veterinarian=?";
                break;
        }
        
        try{
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, (veterinarian != null) ? veterinarian.getIDVeterinarian() : null);
            rs = preparedStatement.executeQuery();

            while(rs.next()){
                Appointment appointment = new Appointment(rs.getInt(1),
                                            new PetOwner(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)),
                                            veterinarian, rs.getDate(7), rs.getString(8), rs.getBoolean(9));
                appointments.add(appointment);
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally{
            DBUtil.close(connection, preparedStatement, rs);
        }

        return appointments;
    }
    
}
