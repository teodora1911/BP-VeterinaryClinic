package dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import constants.ExaminationChoices;
import dto.City;
import dto.Examination;
import dto.ExaminationHasService;
import dto.Medicine;
import dto.Payment;
import dto.Service;
import dto.SpentMedicine;
import dto.Treatment;
import dto.Veterinarian;

public interface IExaminationDAO {

    List<City> getCities();
    // List<Address> getAddresses(City city);
    boolean addExamination(Examination examination);
    boolean updateExamination(Examination examination);
    List<Examination> getExaminationsFrom(Veterinarian veterinarian, ExaminationChoices choice, Date date, String name, String surname);

    List<Treatment> getTreatmentsFrom(Examination examination);
    List<SpentMedicine> getSpentMedicineFrom(Examination examination);
    List<ExaminationHasService> getServicesFrom(Examination examination);

    void addService(Examination examination, Service service, Integer quantity);
    void updateService(Examination examination, Service service, Integer quantity);
    void deleteService(Examination examination, Service service);

    void addTreatment(Treatment treatment);
    void updateTreatment(Treatment treatment);
    void deleteTreatment(Treatment treatment);
    
    void addSpentMedicine(Examination e, Medicine m, int q);
    void updateSpentMedicine(SpentMedicine medicine, Integer quantity);
    void deleteSpentMedicine(SpentMedicine medicine);

    BigDecimal getTotalCost(Examination examination);
    List<Payment> getPayments();
    void createBill(BigDecimal charge, LocalDateTime timestamp, Payment payment, Examination examination);
}
