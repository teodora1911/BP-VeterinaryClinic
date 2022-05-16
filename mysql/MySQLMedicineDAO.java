package mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.AppUtil;
import dao.IMedicineDAO;
import dto.Manufacturer;
import dto.Medicine;
import dto.MedicineType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class MySQLMedicineDAO implements IMedicineDAO {

    private Connection connection = null;
    private PreparedStatement ps = null;
    private CallableStatement cs = null;
    private ResultSet resultSet = null;

    private void resetAll(){
        connection = null;
        ps = null;
        cs = null;
        resultSet = null;
    }

    @Override
    public List<MedicineType> getMedicineTypes() {
        resetAll();
        List<MedicineType> types = new ArrayList<>();
        final String query = "SELECT * FROM medicinetype";
        try{
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(query);
            resultSet = ps.executeQuery();

            while(resultSet.next()){
                types.add(new MedicineType(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (SQLException e){
            AppUtil.showAltert(AlertType.ERROR, String.valueOf(e.getErrorCode()), ButtonType.OK);
        } finally {
            DBUtil.close(connection, ps, resultSet);
        }

        return types;
    }

    @Override
    public List<Manufacturer> getManufacturers() {
        resetAll();
        List<Manufacturer> manufacturers = new ArrayList<>();
        final String query = "SELECT * FROM manufacturer";
        try{
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(query);
            resultSet = ps.executeQuery();

            while(resultSet.next()){
                manufacturers.add(new Manufacturer(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (SQLException e){
            AppUtil.showAltert(AlertType.ERROR, String.valueOf(e.getErrorCode()), ButtonType.OK);
        } finally {
            DBUtil.close(connection, ps, resultSet);
        }

        return manufacturers;
    }

    @Override
    public List<Medicine> getMedicineBy(Manufacturer manufacturer, MedicineType type){
        resetAll();
        List<Medicine> medicines = new ArrayList<>();
        final String query = "{CALL search_medicine(?, ?)}";

        try{
            connection = DBUtil.getConnection();
            cs = connection.prepareCall(query);

            cs.setInt(1, (manufacturer != null) ? manufacturer.getIDManufacturer() : 0);
            cs.setInt(2, (type != null) ? type.getIDMedicineType() : 0);

            resultSet = cs.executeQuery();
            while(resultSet.next()){
                // 1. IDMedicine; 2. Name; 3. Price; 4. Manufacturer; 5. Quantity; 6. MedicineType
                medicines.add(new Medicine(resultSet.getInt(1), resultSet.getString(2),
                                            resultSet.getDouble(3), new Manufacturer(resultSet.getString(4)),
                                            resultSet.getInt(5), new MedicineType(resultSet.getString(6))));
            }
        } catch (SQLException e){
            AppUtil.showAltert(AlertType.ERROR, String.valueOf(e.getErrorCode()), ButtonType.OK);
        } finally {
            DBUtil.close(connection, cs, resultSet);
        }

        return medicines;
    }
}
