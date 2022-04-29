package dto;

public enum Gender {

    FEMALE(1),
    MALE(2);

    private int value;
    private Gender(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
