package com.caixaeletronico;

public class ContaCorrente {
    private double saldo = 0.0;
    private final String numero;

    public ContaCorrente(String numero) {
        this.numero = numero;
    }

    public double getSaldo(){
        return this.saldo;
    }
    public String getNumero(){
        return this.numero;
    }

    public void depositarValor(double valor){
        if(valor < 0.0)
            throw new RuntimeException("Valor inválido");
        this.saldo += valor;
    }

    public void sacarValor(double valor){
        if(this.saldo < valor)
            throw new RuntimeException("Saldo insuficiente");
        this.saldo -= valor;
    }

}
