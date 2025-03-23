package com.caixaeletronico;

public interface ServicoRemoto {
    public ContaCorrente recuperarConta(String numero);
    public boolean persistirConta(ContaCorrente conta);
}
