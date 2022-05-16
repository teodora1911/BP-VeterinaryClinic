package mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import application.AppUtil;
import constants.AppointmentsChoices;
import dao.IAppointmentDAO;
import dto.Appointment;
import dto.PetOwner;
import dto.Veterinarian;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class MySQLAppointmentDAO  implements IAppointmentDAO {

    private Connection connection = null;
    private PreparedStatement ps = null;
    private CallableStatement cs = null;
    private ResultSet rs = null;

    private void resetAll(){
        connection = null;
        ps = null;
        cs = null;
        rs = null;
    }

    @Override
    public boolean addNewAppointment(PetOwner customer, Veterinarian veterinarian, String date, String entryReason) {
        resetAll();
        String query = "{CALL add_appointment(?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try{
            connection = DBUtil.getConnection();
            cs = connection.prepareCall(query);

            cs.setString(1, customer.getName());
            cs.setString(2, customer.getSurname());
            cs.setString(3, customer.getEmail());
            cs.setString(4, customer.getPhoneNumber());
            cs.setInt(5, veterinarian.getIDVeterinarian());
            cs.setString(6, date);
            cs.setString(7, entryReason);
            cs.registerOutParameter(8, Types.TINYINT);
            cs.registerOutParameter(9, Types.VARCHAR);

            cs.execute();
            if(!cs.getBoolean(8)){
                System.out.println(cs.getString(9));
                return false;
            }
        } catch (SQLException e){
            AppUtil.showAltert(AlertType.ERROR, String.valueOf(e.getErrorCode()), ButtonType.OK);
        } finally {
            DBUtil.close(connection, cs, rs);
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
            ps = connection.prepareStatement(query);
            ps.setInt(1, (veterinarian != null) ? veterinarian.getIDVeterinarian() : null);
            rs = ps.executeQuery();

            while(rs.next()){
                Appointment appointment = new Appointment(rs.getInt(1),
                                            new PetOwner(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)),
                                            veterinarian, rs.getDate(7), rs.getString(8), rs.getBoolean(9));
                appointments.add(appointment);
            }

        } catch (SQLException e){
            AppUtil.showAltert(AlertType.ERROR, String.valueOf(e.getErrorCode()), ButtonType.OK);
        } finally{
            DBUtil.close(connection, ps, rs);
        }

        return appointments;
    }
    
}
