package com.caixaeletronico;

public class MockHardware implements Hardware {
    @Override
    public String pegarNumeroDaContaCartao() {
        return "1234";
    }

    @Override
    public void entregarDinheiro() {
        // TODO Auto-generated method stub

    }

    @Override
    public void lerEnvelope() {
        // TODO Auto-generated method stub

    }
}
