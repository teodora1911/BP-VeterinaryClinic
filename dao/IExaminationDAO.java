package dao;

import java.sql.Date;
import java.util.List;

import constants.ExaminationChoices;
import dto.Address;
import dto.City;
import dto.Examination;
import dto.Veterinarian;

public interface IExaminationDAO {

    List<City> getCities();
    List<Address> getAddresses(City city);
    boolean addExamination(Examination examination);
    List<Examination> getExaminationsFrom(Veterinarian veterinarian, ExaminationChoices choice, Date date, String name, String surname);
}
