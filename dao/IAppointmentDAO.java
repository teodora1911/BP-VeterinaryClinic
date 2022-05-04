package dao;

import java.util.List;

import application.Choice;
import dto.Appointment;
import dto.PetOwner;
import dto.Veterinarian;

public interface IAppointmentDAO {
    
    public boolean addNewAppointment(PetOwner customer, Veterinarian veterinarian, String date, String entryReason);
    // public List<Appointment> getAllAppointments();
    public List<Appointment> getAppointmentsFromVeterinarian(Integer id, Choice choice);
}
