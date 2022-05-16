package mysql;

import java.sql.SQLException;
import java.sql.Types;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import application.AppUtil;
import dao.IVeterinarianDAO;
import dto.Service;
import dto.Veterinarian;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class MySQLVeterinarianDAO implements IVeterinarianDAO {

    private Connection connection = null;
    private PreparedStatement ps = null;
    private CallableStatement cs = null;
    private ResultSet rs = null;

    private static Veterinarian currentVeterinarian = null;

    private void resetAll(){
        connection = null;
        ps = null;
        cs = null;
        rs = null;
    }
    
    @Override
    public List<Veterinarian> getVeterinarians(){
        List<Veterinarian> veterinariansDetails = new ArrayList<>();
        resetAll();

        final String query = "SELECT IDVeterinarian, Name, Surname FROM veterinarian";
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next()){
                // 1. Column (IDVeterinarian), 2. Column (Name), 3. Column (Surname)
                veterinariansDetails.add(new Veterinarian(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException e) {
            AppUtil.showAltert(AlertType.ERROR, String.valueOf(e.getErrorCode()), ButtonType.OK);
        } finally {
            DBUtil.close(connection, ps, rs);
        }

        return veterinariansDetails;
    }

    @Override
    public boolean authenticateVeterinarian(String username, String password){
        resetAll();
        final String query = "{CALL veterinarian_authentication(?, ?, ?, ?, ?, ?)}";

        try{
            connection = DBUtil.getConnection();
            cs = connection.prepareCall(query);

            cs.setString(1, username);
            cs.setString(2, password);
            cs.registerOutParameter(3, Types.TINYINT);
            cs.registerOutParameter(4, Types.INTEGER);
            cs.registerOutParameter(5, Types.VARCHAR);
            cs.registerOutParameter(6, Types.VARCHAR);

            cs.execute();
            if(!cs.getBoolean(3)){
                System.out.println("Nevalidni korisnicko ime i/ili lozinka.");
                return false;
            }
            
            currentVeterinarian = new Veterinarian(cs.getInt(4), 
                                                    cs.getString(5), 
                                                      cs.getString(6));
        } catch (SQLException e){
            AppUtil.showAltert(AlertType.ERROR, String.valueOf(e.getErrorCode()), ButtonType.OK);
        } finally {
            DBUtil.close(connection, cs, rs);
        }

        return true;
    }

    @Override
    public List<Service> getServices(){
        resetAll();
        List<Service> services = new ArrayList<>();
        final String query = "SELECT * FROM veterinarian_services_view WHERE IDVeterinarian=?";

        if(currentVeterinarian != null && currentVeterinarian.getIDVeterinarian() != null){
            try {
                connection = DBUtil.getConnection();
                ps = connection.prepareStatement(query);
                ps.setInt(1, currentVeterinarian.getIDVeterinarian());
                rs = ps.executeQuery();
                /*
                 * 1. IDVeterinarian
                 * 2. IDService
                 * 3. ServiceName
                 * 4. ServiceCost
                 * 5. ServiceDesc
                 */
                while(rs.next()){
                    Service service = new Service(rs.getInt(2), rs.getString(3), rs.getDouble(4), rs.getString(5));
                    services.add(service);
                }
            } catch (SQLException e) {
                AppUtil.showAltert(AlertType.ERROR, String.valueOf(e.getErrorCode()), ButtonType.OK);
            } finally {
                DBUtil.close(connection, ps, rs);
            }
        }
        return services;
    }

    public static Veterinarian getCurrentVeterinarian(){
        return currentVeterinarian;
    }
}
