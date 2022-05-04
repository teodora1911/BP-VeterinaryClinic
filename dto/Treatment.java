package dto;

public class Treatment {
    
    private Integer IDmedicine;
    private String name;
    private Integer dose;
    private String frequency;
    private String startDate;
    private Integer duration;
    private String instructions;

    public Treatment(Integer IDmedicine, String name, Integer dose, String frequency, String startDate, Integer duration, String instructions){
        this.IDmedicine = IDmedicine;
        this.name = name;
        this.dose = dose;
        this.frequency = frequency;
        this.startDate = startDate;
        this.duration = duration;
        this.instructions = instructions;
    }

    public Integer getIDMedicine() {
        return IDmedicine;
    }

    public void setMedicine(Integer IDmedicine) {
        this.IDmedicine = IDmedicine;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
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
