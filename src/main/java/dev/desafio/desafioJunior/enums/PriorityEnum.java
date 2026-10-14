package dev.desafio.desafioJunior.enums;

public enum PriorityEnum {

    LOW("low"),
    AVERAGE("average"),
    HIGH("high"),
    URGENT("urgent");

    private String called;

    PriorityEnum(String called){
        this.called = called;
    }

    public String getCalled(){
        return called;
    }

}
