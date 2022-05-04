package dto;

import java.sql.Date;
import java.sql.Time;

public class Examination {
    
    private Integer IDExamination;
    private Pet pet;
    private Date date;
    private Time time;
    private String description;
    private Address address;
    private Boolean completed;

    public Examination(Integer IDExamination, Pet pet, Date date, Time time, String description, Address address, Boolean completed){
        this.IDExamination = IDExamination;
        this.pet = pet;
        this.date = date;
        this.time = time;
        this.description = description;
        this.address = address;
        this.completed = completed;
    }

    public Integer getIDExamination() {
        return IDExamination;
    }

    public void setIDExamination(Integer iDExamination) {
        this.IDExamination = iDExamination;
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
}
