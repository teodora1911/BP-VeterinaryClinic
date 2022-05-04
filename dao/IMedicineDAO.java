package dao;

import java.util.List;

import dto.Manufacturer;
import dto.Medicine;
import dto.MedicineType;

public interface IMedicineDAO {
    public List<MedicineType> getMedicineTypes();
    public List<Manufacturer> getManufacturers();
    public List<Medicine> getMedicineBy(Manufacturer manufacturer, MedicineType type);
}
