package constants;

public enum AppointmentsChoices {
    Svi(0),
    Zakazani(1),
    Nezakazani(2);

    private int value;
    private AppointmentsChoices(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
