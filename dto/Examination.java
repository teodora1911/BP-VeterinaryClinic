package dto;

import java.sql.Date;
import java.sql.Time;

public class Examination {
    
    private Integer IDExamination;
    private Veterinarian veterinarian;
    private Pet pet;
    private Date date;
    private Time time;
    private String description;
    private Address address;
    private Boolean completed;
    private Appointment appointment;

    public Examination(Integer IDExamination, Veterinarian veterinarian, Pet pet, Date date, Time time, String description, Address address, Boolean completed, Appointment appointment){
        this.IDExamination = IDExamination;
        this.veterinarian = veterinarian;
        this.pet = pet;
        this.date = date;
        this.time = time;
        this.description = description;
        this.address = address;
        this.completed = completed;
        this.appointment = appointment;
    }

    public Examination(Integer IDExamination, Veterinarian veterinarian, Pet pet, Date date, Time time, String description, Address address, Boolean completed){
        this(IDExamination, veterinarian, pet, date, time, description, address, completed, null);
    }

    public Integer getIDExamination() {
        return IDExamination;
    }

    public void setIDExamination(Integer iDExamination) {
        this.IDExamination = iDExamination;
    }

    public Veterinarian getVeterinarian(){
        return veterinarian;
    }

    public void setVeterinarian(Veterinarian veterinarian){
        this.veterinarian = veterinarian;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public Time getTime(){
        return time;
    }

    public void setTime(Time time){
        this.time = time;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Appointment getAppointment(){
        return appointment;
    }

    public void setAppointment(Appointment appointment){
        this.appointment = appointment;
    }
}
