package com.caixaeletronico;

public class CaixaEletronico {
    private double saldo;

    public String logar(String login, String senha) {
        if(true) return "Usuário autenticado";
        return "Não foi possível autenticar o usuário";

    }
    public String sacar(double valor) {
        if(valor > saldo)
            return "Saldo insuficiente";
        this.saldo -= valor;
        return "Retire seu dinheiro";
    }

    public void depositar(double valor) {
    }


    public String saldo() {
        return "O saldo é R$" + saldo;
    }

}
