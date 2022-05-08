package constants;

public enum ExaminationChoices {
    Svi(0),
    Zavrseni(1),
    Nedovrseni(2);

    private int value;
    private ExaminationChoices(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
