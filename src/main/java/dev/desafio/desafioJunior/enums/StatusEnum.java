package dev.desafio.desafioJunior.enums;

public enum StatusEnum {

    ABERTO("aberto"),
    EM_ATENDIMENTO("atendimento"),
    RESOLVIDO("resolvido"),
    CANCELADO("canceladp");

    private String called;

    StatusEnum(String called){
        this.called = called;
    }

    public String getCalled(){
        return called;
    }

}
