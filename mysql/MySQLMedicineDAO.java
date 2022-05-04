package mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.IMedicineDAO;
import dto.Manufacturer;
import dto.Medicine;
import dto.MedicineType;

public class MySQLMedicineDAO implements IMedicineDAO {

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
    public List<MedicineType> getMedicineTypes() {
        resetAll();
        List<MedicineType> types = new ArrayList<>();
        final String query = "SELECT * FROM medicinetype";
        try{
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                // 1. Column (IDMedicineType), 2. Column (Type)
                types.add(new MedicineType(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
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
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                // 1. Column (IDManufacturer), 2. Column (Name), 3. Column(Description)
                manufacturers.add(new Manufacturer(resultSet.getInt(1), resultSet.getString(2)));
                // 3. kolona nam za sada ne treba
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
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
            callableStatement = connection.prepareCall(query);

            callableStatement.setInt(1, (manufacturer != null) ? manufacturer.getIDManufacturer() : 0);
            callableStatement.setInt(2, (type != null) ? type.getIDMedicineType() : 0);

            resultSet = callableStatement.executeQuery();
            while(resultSet.next()){
                // 1. IDMedicine; 2. Name; 3. Price; 4. Manufacturer; 5. Quantity; 6. MedicineType
                medicines.add(new Medicine(resultSet.getInt(1), resultSet.getString(2),
                                            resultSet.getDouble(3), new Manufacturer(resultSet.getString(4)),
                                            resultSet.getInt(5), new MedicineType(resultSet.getString(6))));
                // kolone 7 i 8 za sada ne koristimo
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, callableStatement, resultSet);
        }

        return medicines;
    }
}
