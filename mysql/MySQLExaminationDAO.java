package mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import constants.ExaminationChoices;
import dao.IExaminationDAO;
import dto.Address;
import dto.City;
import dto.Examination;
import dto.Pet;
import dto.PetOwner;
import dto.Veterinarian;

public class MySQLExaminationDAO implements IExaminationDAO {

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
    public List<City> getCities() {
        resetAll();
        List<City> cities = new ArrayList<>();
        final String query = "SELECT * FROM city";

        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next()){
                // IDCity, Name, ZIPCode
                cities.add(new City(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, ps, rs);
        }

        return cities;
    }

    @Override
    public List<Address> getAddresses(City city) {
        if(city != null){
            resetAll();
            List<Address> addresses = new ArrayList<>();
            final String query = "SELECT * FROM address addr WHERE addr.City=?";

            try {
                connection = DBUtil.getConnection();
                ps = connection.prepareStatement(query);
                ps.setInt(1, city.getIDCity());
                rs = ps.executeQuery();

                while(rs.next()){
                    // IDAddress, Street, Number, IDCity
                    Address address = new Address(rs.getInt(1), rs.getString(2), rs.getString(3));
                    addresses.add(address);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.close(connection, ps, rs);
            }

            return addresses;
        }

        return null;
    }

    @Override
    public boolean addExamination(Examination examination){
        resetAll();
        final String query = "{CALL add_examination(?, ?, ?, ?, ?, ?, ?, ?)}";

        try{
            connection = DBUtil.getConnection();
            cs = connection.prepareCall(query);

            cs.setInt(1, (examination.getVeterinarian() != null) ? examination.getVeterinarian().getIDVeterinarian() : 0);
            cs.setInt(2, (examination.getPet() != null) ? examination.getPet().getIDPet() : 0);
            cs.setDate(3, examination.getDate());
            cs.setTime(4, examination.getTime());
            cs.setString(5, examination.getDescription());
            cs.setInt(6, (examination.getAppointment() != null) ? examination.getAppointment().getIDAppointment() : 0);
            cs.registerOutParameter(7, Types.TINYINT);
            cs.registerOutParameter(8, Types.VARCHAR);

            cs.execute();
            if(!cs.getBoolean(7)){
                System.out.println(cs.getString(8));
                return false;
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, cs, rs);
        }

        return true;
    }

    @Override
    public List<Examination> getExaminationsFrom(Veterinarian veterinarian, ExaminationChoices choice, Date date, String name, String surname){
        resetAll();
        List<Examination> examinations = new ArrayList<>();
        String query = "SELECT * FROM simple_examination_view WHERE IDVeterinarian=?";
        query += " AND OwnerName LIKE ?";
        query += " AND OwnerSurname LIKE ?";

        switch(choice){
            case Zavrseni:
                query += " AND Completed=true";
                break;
            case Nedovrseni:
                query += " AND Completed=false";
                break;
            default:
                break;
        }
        if(date != null){
            query += " AND Date=?";
        }

        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, veterinarian.getIDVeterinarian());
            ps.setString(2, DBUtil.preparePattern(name));
            ps.setString(3, DBUtil.preparePattern(surname));
            if(date != null){
                ps.setDate(4, date);
            }
            System.out.println(query);
            rs = ps.executeQuery();

            while(rs.next()){
                /*
                 * 1. IDExamination
                 * 2. PetName
                 * 3. OwnerName
                 * 4. OwnerSurname
                 * 5. Date
                 * 6. Time
                 * 7. Completed
                 * 8. IDPet
                 * 9. IDOwner
                 * 10. IDVeterinarian
                 */
                Examination examination = new Examination(rs.getInt(1), veterinarian, 
                                                            new Pet(rs.getInt(8), rs.getString(2), new PetOwner(rs.getInt(9), rs.getString(3), rs.getString(4), null, null)),
                                                             rs.getDate(5), rs.getTime(6), null, null, rs.getBoolean(7));
                examinations.add(examination);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, ps, rs);
        }

        return examinations;
    }
}
