package application;

public enum Choice {
    Svi(0),
    Zakazani(1),
    Nezakazani(2);

    private int value;
    private Choice(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}