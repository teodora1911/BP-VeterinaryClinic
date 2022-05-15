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

import application.AppUtil;
import constants.ExaminationChoices;
import dao.IExaminationDAO;
import dto.Address;
import dto.City;
import dto.Examination;
import dto.ExaminationHasService;
import dto.Medicine;
import dto.MedicineType;
import dto.Pet;
import dto.PetOwner;
import dto.Service;
import dto.SpentMedicine;
import dto.Treatment;
import dto.Veterinarian;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

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
        String query = "SELECT * FROM examination_view WHERE IDVeterinarian=?";
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
            rs = ps.executeQuery();

            while(rs.next()){
                /*
                 * 1.  IDExamination
                 * 2.  PetName
                 * 3.  OwnerName
                 * 4.  OwnerSurname
                 * 5.  Date
                 * 6.  Time
                 * 7.  Description
                 * 8.  Street
                 * 9.  StreetNumber
                 * 10. City
                 * 11. Completed
                 * 12. IDPet
                 * 13. IDOwner
                 * 14. IDVeterinarian
                 * 15. IDAddr
                 * 16. IDCity
                 */

                City city = new City(rs.getInt(16), rs.getString(10));
                Address address = new Address(rs.getInt(15), rs.getString(8), rs.getString(9), city);
                PetOwner owner = new PetOwner(rs.getInt(13), rs.getString(3), rs.getString(4), null, null);
                Pet pet = new Pet(rs.getInt(12), rs.getString(2), owner);
                Examination examination = new Examination(rs.getInt(1), veterinarian, pet, rs.getDate(5), rs.getTime(6), rs.getString(7), address, rs.getBoolean(11));
                examinations.add(examination);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, ps, rs);
        }

        return examinations;
    }

    @Override
    public List<Treatment> getTreatmentsFrom(Examination examination){
        resetAll();
        List<Treatment> treatments = new ArrayList<>();
        if(examination != null && examination.getIDExamination() != null) {
            final String query = "SELECT * FROM treatment_view WHERE IDExamination=?";
            try {
                connection = DBUtil.getConnection();
                ps = connection.prepareStatement(query);
                ps.setInt(1, examination.getIDExamination());
                rs = ps.executeQuery();
                /*
                 * treatment_view
                 *  1.  IDExamination
                 *  2.  IDMedicine
                 *  3.  MedicineName
                 *  4.  IDMedicineType
                 *  5.  MedicineType
                 *  6.  Name
                 *  7.  Dose
                 *  8.  Frequency
                 *  9.  StartDate
                 *  10. Duration
                 *  11. Instructions
                 */
                while(rs.next()){
                    MedicineType medicineType = new MedicineType(rs.getInt(4), rs.getString(5));
                    Medicine medicine = new Medicine(rs.getInt(2), rs.getString(3),  null, null, null, medicineType);
                    Treatment treatment = new Treatment(examination, medicine, rs.getString(6), rs.getInt(7), rs.getString(8), rs.getDate(9), rs.getInt(10), rs.getString(11));
                    treatments.add(treatment);
                }
            } catch (SQLException e) {
               e.printStackTrace();
            } finally {
                DBUtil.close(connection, ps, rs);
            }
        }

        return treatments;
    }

    @Override
    public List<SpentMedicine> getSpentMedicineFrom(Examination examination){
       resetAll();
       List<SpentMedicine> medicines = new ArrayList<>();
       if(examination != null && examination.getIDExamination() != null) {
           final String query = "SELECT * FROM spent_medicine_view WHERE IDExamination=?";
           try{
               connection = DBUtil.getConnection();
               ps = connection.prepareStatement(query);
               ps.setInt(1, examination.getIDExamination());
               rs = ps.executeQuery();
               /*
                * spent_medicine_view
                * 1. IDExamination
                * 2. IDMedicine
                * 3. MedicineName
                * 4. IDMedicineType
                * 5. MedicineType
                * 6. Quantity 
                */
               while(rs.next()){
                   MedicineType medicineType = new MedicineType(rs.getInt(4), rs.getString(5));
                   Medicine medicine = new Medicine(rs.getInt(2), rs.getString(3), null, null, null, medicineType);
                   SpentMedicine spentMedicine = new SpentMedicine(examination, medicine, rs.getInt(6));
                   medicines.add(spentMedicine);
               }
           } catch (SQLException e){
               e.printStackTrace();
           } finally {
               DBUtil.close(connection, ps, rs);
           }
       }
       return medicines;
    }

    @Override
    public List<ExaminationHasService> getServicesFrom(Examination examination){
        resetAll();
        List<ExaminationHasService> services = new ArrayList<>();
        if(examination != null && examination.getIDExamination() != null) {
            final String query = "SELECT * FROM examinationhasservice_view WHERE IDExamination=?";
            try{
                connection = DBUtil.getConnection();
                ps = connection.prepareStatement(query);
                ps.setInt(1, examination.getIDExamination());
                rs = ps.executeQuery();
                /*
                 * 1. IDExamination
                 * 2. IDService
                 * 3. ServiceName
                 * 4. ServiceCost
                 * 5. Quantity
                 * 6. Cost 
                 */
                while(rs.next()){
                    Service service = new Service(rs.getInt(2), rs.getString(3), rs.getDouble(4));
                    ExaminationHasService ehs = new ExaminationHasService(service, rs.getInt(5), rs.getDouble(6));
                    services.add(ehs);
                }
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                DBUtil.close(connection, ps, rs);
            }
        }
        return services;
    }

    @Override
    public void addService(Examination examination, Service service, Integer quantity) {
        if(examination != null && examination.getIDExamination() != null && service != null && service.getIDService() != null && quantity != null) {
            resetAll();
            final String query = "{CALL add_service_examination(?, ?, ?, ?, ?)}";

            try{
                connection = DBUtil.getConnection();
                cs = connection.prepareCall(query);
                cs.setInt(1, examination.getIDExamination());
                cs.setInt(2, service.getIDService());
                cs.setInt(3, quantity);
                cs.registerOutParameter(4, Types.DECIMAL);
                cs.registerOutParameter(5, Types.TINYINT);
                cs.execute();

                if(!cs.getBoolean(5)){
                    System.out.println("Usluga nije uspjesno dodata.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.close(connection, cs, rs);
            }
        }
    }

    @Override
    public void updateService(Examination examination, Service service, Integer quantity) {
        if(examination != null && examination.getIDExamination() != null && service != null && service.getIDService() != null && quantity != null) {
            resetAll();
            final String query = "{CALL add_service_examination(?, ?, ?, ?, ?)}";

            try{
                connection = DBUtil.getConnection();
                cs = connection.prepareCall(query);
                cs.setInt(1, examination.getIDExamination());
                cs.setInt(2, service.getIDService());
                cs.setInt(3, quantity);
                cs.registerOutParameter(4, Types.DECIMAL);
                cs.registerOutParameter(5, Types.TINYINT);
                cs.execute();

                if(!cs.getBoolean(5)){
                    System.out.println("Neuspjesna izmjena usluge.");
                }
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                DBUtil.close(connection, cs, rs);
            }
        }
    }

    @Override
    public void deleteService(Examination examination, Service service) {
        if(examination != null && examination.getIDExamination() != null && service != null && service.getIDService() != null) {
            resetAll();
            final String query = "{CALL delete_service_examination(?, ?)}";

            try {
                connection = DBUtil.getConnection();
                cs = connection.prepareCall(query);
                cs.setInt(1, examination.getIDExamination());
                cs.setInt(2, service.getIDService());
                cs.execute();
            } catch (SQLException e) {
               e.printStackTrace();
            } finally {
                DBUtil.close(connection, cs, rs);
            }
        }
    }

    @Override
    public void addTreatment(Treatment treatment) {
        if(treatment != null && treatment.getExamination() != null && treatment.getMedicine() != null) {
            resetAll();
            final String query = "{CALL add_treatment(?, ?, ?, ?, ?, ?, ?, ?, ?)}";

            try {
                connection = DBUtil.getConnection();
                cs = connection.prepareCall(query);
                cs.setInt(1, treatment.getExamination().getIDExamination());
                cs.setInt(2, treatment.getMedicine().getIDMedicine());
                cs.setString(3, treatment.getName());
                cs.setInt(4, treatment.getDose());
                cs.setString(5, treatment.getFrequency());
                cs.setDate(6, treatment.getStartDate());
                cs.setInt(7, treatment.getDuration());
                cs.setString(8, treatment.getInstructions());
                cs.registerOutParameter(9, Types.TINYINT);
                cs.execute();

                if(!cs.getBoolean(9)){
                    System.out.println("Nije dodato.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.close(connection, cs, rs);
            }
        }
    }

    @Override
    public void updateTreatment(Treatment treatment){
        if(treatment != null && treatment.getExamination() != null && treatment.getMedicine() != null) {
            resetAll();
            final String query = "{CALL update_treatment(?, ?, ?, ?, ?, ?, ?, ?, ?)}";

            try {
                connection = DBUtil.getConnection();
                cs = connection.prepareCall(query);
                cs.setInt(1, treatment.getExamination().getIDExamination());
                cs.setInt(2, treatment.getMedicine().getIDMedicine());
                cs.setString(3, treatment.getName());
                cs.setInt(4, treatment.getDose());
                cs.setString(5, treatment.getFrequency());
                cs.setDate(6, treatment.getStartDate());
                cs.setInt(7, treatment.getDuration());
                cs.setString(8, treatment.getInstructions());
                cs.registerOutParameter(9, Types.TINYINT);
                cs.execute();

                if(!cs.getBoolean(9)){
                    System.out.println("Nije izmjenjeno.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.close(connection, cs, rs);
            }
        }
    }

    @Override
    public void deleteTreatment(Treatment treatment) {
        if(treatment != null && treatment.getExamination() != null && treatment.getMedicine() != null) {
            resetAll();
            final String query = "DELETE FROM treatment WHERE Examination=? AND Medicine=?";
            try{
                connection = DBUtil.getConnection();
                ps = connection.prepareStatement(query);
                ps.setInt(1, treatment.getExamination().getIDExamination());
                ps.setInt(2, treatment.getMedicine().getIDMedicine());
                ps.execute();
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                DBUtil.close(connection, ps, rs);
            }
        }
    }

    @Override
    public void addSpentMedicine(Examination examination, Medicine medicine, int quantity) {
        if(examination != null && medicine != null) {
            resetAll();
            final String query = "{CALL add_spent_medicine(?, ?, ?, ?)}";
            try{
                connection = DBUtil.getConnection();
                cs = connection.prepareCall(query);
                cs.setInt(1, examination.getIDExamination());
                cs.setInt(2, medicine.getIDMedicine());
                cs.setInt(3, quantity);
                cs.registerOutParameter(4, Types.TINYINT);
                cs.execute();

                if(!cs.getBoolean(4)){
                    AppUtil.showAltert(AlertType.ERROR, "Nema dovoljno lijeka.", ButtonType.OK);
                }
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                DBUtil.close(connection, cs, rs);
            }
        } else {
            System.out.println("OOOOOOO");
        }
    }

    @Override
    public void updateSpentMedicine(SpentMedicine medicine, Integer quantity) {
        if(medicine != null && medicine.getExamination() != null && medicine.getMedicine() != null && quantity != null) {
            resetAll();
            final String query = "{CALL update_spent_medicine(?, ?, ?, ?)}";
            try{
                connection = DBUtil.getConnection();
                cs = connection.prepareCall(query);
                cs.setInt(1, medicine.getExamination().getIDExamination());
                cs.setInt(2, medicine.getMedicine().getIDMedicine());
                cs.setInt(3, quantity);
                cs.registerOutParameter(4, Types.TINYINT);
                cs.execute();

                if(!cs.getBoolean(4)){
                    AppUtil.showAltert(AlertType.ERROR, "Nema dovoljno lijeka.", ButtonType.OK);
                }
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                DBUtil.close(connection, cs, rs);
            }
        }
    }

    @Override
    public void deleteSpentMedicine(SpentMedicine medicine) {
        if(medicine != null && medicine.getExamination() != null && medicine.getMedicine() != null) {
            resetAll();
            final String query = "DELETE FROM spentmedicine WHERE Examination=? AND Medicine=?";
            try{
                connection = DBUtil.getConnection();
                ps = connection.prepareStatement(query);
                ps.setInt(1, medicine.getExamination().getIDExamination());
                ps.setInt(2, medicine.getMedicine().getIDMedicine());
                ps.execute();
            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                DBUtil.close(connection, ps, rs);
            }
        }
    }
}
