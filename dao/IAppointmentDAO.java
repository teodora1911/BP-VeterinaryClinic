package dao;

import java.util.List;

import dto.Appointment;
import dto.PetOwner;

public interface IAppointmentDAO {
    
    public boolean addNewAppointment(PetOwner customer, Integer idVeterinarian, String date, String entryReason);
    public List<Appointment> getAllAppointments();
}
