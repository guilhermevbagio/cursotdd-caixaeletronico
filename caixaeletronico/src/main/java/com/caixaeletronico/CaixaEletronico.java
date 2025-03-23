package com.caixaeletronico;

public class CaixaEletronico {
    private ContaCorrente contaLogada;
    private ServicoRemoto servicoRemoto;
    private Hardware hardware;

    public String logar(String numero) {
        try {
            contaLogada = servicoRemoto.recuperarConta(numero);
        } catch (Exception e) {
            return "Não foi possível autenticar o usuário";
        }

        return "Usuário autenticado";
    }

    public String sacar(double valor) {
        try {
            contaLogada.sacarValor(valor);
        } catch (Exception e) {
            return e.getMessage();
        }

        return "Retire seu dinheiro";
    }

    public String depositar(double valor) {
        contaLogada.depositarValor(valor);
        if(servicoRemoto.persistirConta(contaLogada))
            return "Deposito recebido com sucesso";
        else
            return "Erro ao receber o deposito";
    }


    public String saldo() {
        return "O saldo é R$" + contaLogada.getSaldo();
    }

    public void ConectarServicoRemoto(ServicoRemoto servicoRemoto) {
        this.servicoRemoto = servicoRemoto;
    }

    public void ConectarHardware(Hardware hardware) {
        this.hardware = hardware;
    }

}
