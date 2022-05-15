package dto;

import java.sql.Date;

public class Treatment {
    
    private Examination examination;
    private Medicine medicine;
    private String name;
    private Integer dose;
    private String frequency;
    private Date startDate;
    private Integer duration;
    private String instructions;

    public Treatment(Examination examination, Medicine medicine, String name, Integer dose, String frequency, Date startDate, Integer duration, String instructions){
        this.examination = examination;
        this.medicine = medicine;
        this.name = name;
        this.dose = dose;
        this.frequency = frequency;
        this.startDate = startDate;
        this.duration = duration;
        this.instructions = instructions;
    }

    public Examination getExamination() {
        return examination;
    }

    public void setExamination(Examination examination){
        this.examination = examination;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }


    public String getInstructions() {
        return instructions;
    }


    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Integer getDose() {
        return dose;
    }

    public void setDose(Integer dose) {
        this.dose = dose;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
