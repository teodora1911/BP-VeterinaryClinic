package dao;

import java.util.List;

import dto.Address;
import dto.City;

public interface IExaminationDAO {

    List<City> getCities();
    List<Address> getAddresses(City city);
}
