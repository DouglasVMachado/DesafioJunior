package dev.desafio.desafioJunior.enums;

public enum CalledEnum {

    ABERTO("aberto"),
    EM_ATENDIMENTO("atendimento"),
    RESOLVIDO("resolvido"),
    CANCELADO("canceladp");

    private String called;

    CalledEnum(String called){
        this.called = called;
    }

    public String getCalled(){
        return called;
    }

}
