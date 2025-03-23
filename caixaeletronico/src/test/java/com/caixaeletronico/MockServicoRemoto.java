package com.caixaeletronico;

public class MockServicoRemoto implements ServicoRemoto {
    @Override
    public ContaCorrente recuperarConta(String numero) {
        if(numero.equals("12")) //SIMULA FALHA
            throw new RuntimeException("Conta inexistente");
        return new ContaCorrente(numero);
    }

    @Override
    public boolean persistirConta(ContaCorrente conta) {
        return true;
    }
}
