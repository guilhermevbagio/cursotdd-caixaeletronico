package com.caixaeletronico;

public class CaixaEletronico {
    private ContaCorrente contaLogada;
    private ServicoRemoto servicoRemoto;
    private Hardware hardware;

    public String logar() {
        String numero = hardware.pegarNumeroDaContaCartao();
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
            servicoRemoto.persistirConta(contaLogada);
            hardware.entregarDinheiro();
        } catch (Exception e) {
            return e.getMessage();
        }

        return "Retire seu dinheiro";
    }

    public String depositar(double valor) {

        try {
            contaLogada.depositarValor(valor);
            hardware.lerEnvelope();
        } catch (Exception e) {
            return e.getMessage();
        }

        if(servicoRemoto.persistirConta(contaLogada))
            return "Deposito recebido com sucesso";
        else
            return "Erro ao receber o deposito";
    }


    public String saldo() {
        double saldo =  contaLogada.getSaldo();
        return "O saldo é R$" + saldo;
    }

    public void ConectarServicoRemoto(ServicoRemoto servicoRemoto) {
        this.servicoRemoto = servicoRemoto;
    }

    public void ConectarHardware(Hardware hardware) {
        this.hardware = hardware;
    }

}
