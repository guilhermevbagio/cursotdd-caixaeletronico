package com.caixaeletronico;

public class MockHardware implements Hardware {
    private String numero = "1234";
    private boolean quebrado = false;

    public MockHardware(String numero) {
        this.numero = numero;
    }

    @Override
    public String pegarNumeroDaContaCartao() {
        if(quebrado) avisarQuebrado();
        return numero;
    }

    @Override
    public void entregarDinheiro() {
        if(quebrado) avisarQuebrado();
    }

    @Override
    public void lerEnvelope() {
        if(quebrado) avisarQuebrado();
    }

    public void quebrar(){
        this.quebrado = true;
    }

    public void avisarQuebrado(){
        throw new RuntimeException("Caixa eletronico com defeito, solicite ajuda ao operador");
    }
}
