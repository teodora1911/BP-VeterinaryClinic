package dto;

import java.util.Date;

public class Appointment {

    private Integer IDAppointment;
    private PetOwner petOwner;
    private Veterinarian veterinarian;
    private Date date;
    private String entryReason;
    private Boolean scheduled;

    public Appointment(Integer IDAppointment, PetOwner petOwner, Veterinarian veterinarian, Date date, String entryReason, Boolean scheduled){
        this.IDAppointment = IDAppointment;
        this.petOwner = petOwner;
        this.veterinarian = veterinarian;
        this.date = date;
        this.entryReason = entryReason;
        this.scheduled = scheduled;
    }

    public Integer getIDAppointment() {
        return IDAppointment;
    }

    public void setIDAppointment(Integer IDAppointment) {
        this.IDAppointment = IDAppointment;
    }

    public String getEntryReason() {
        return entryReason;
    }

    public void setEntryReason(String entryReason) {
        this.entryReason = entryReason;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Veterinarian getVeterinarian() {
        return veterinarian;
    }

    public void setVeterinarian(Veterinarian veterinarian) {
        this.veterinarian = veterinarian;
    }

    public PetOwner getPetOwner() {
        return petOwner;
    }

    public void setPetOwner(PetOwner petOwner) {
        this.petOwner = petOwner;
    }

    public Boolean isScheduled(){
        return scheduled;
    }

    public void setScheduled(Boolean scheduled){
        this.scheduled = scheduled;
    }

    // TODO: Override equals and hashCode methods

    @Override
    public String toString(){
        return "[ " + IDAppointment + ", " + petOwner.getName() + ", " + petOwner.getSurname() + ", " + veterinarian.getName() + ", " + veterinarian.getSurname() + date +" ]";
    }
}
