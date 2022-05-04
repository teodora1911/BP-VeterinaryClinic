package mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.IExaminationDAO;
import dto.Address;
import dto.City;

public class MySQLExaminationDAO implements IExaminationDAO {

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
    public List<City> getCities() {
        resetAll();
        List<City> cities = new ArrayList<>();
        final String query = "SELECT * FROM city";

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                // IDCity, Name, ZIPCode
                cities.add(new City(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
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
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, city.getIDCity());
                resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){
                    // IDAddress, Street, Number, IDCity
                    Address address = new Address(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
                    addresses.add(address);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.close(connection, preparedStatement, resultSet);
            }

            return addresses;
        }

        return null;
    }
    
}
