package com.caixaeletronico;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;;


public class CaixaEletronicoTest {
    public CaixaEletronico caixaEletronico = new CaixaEletronico();

    @BeforeEach
    public void setup(){
        caixaEletronico = new CaixaEletronico();
        caixaEletronico.ConectarServicoRemoto(new MockServicoRemoto());
        caixaEletronico.ConectarHardware(new MockHardware("11"));
    }

    public Hardware hardwareQuebrado(){
        MockHardware hardwareQuebrado = new MockHardware("12");
        hardwareQuebrado.quebrar();
        return hardwareQuebrado;
    }

    @Test
    public void autenticacao(){
        assertEquals("Usuário autenticado", caixaEletronico.logar());
    }

    @Test
    public void autenticacaoFalha(){
        caixaEletronico.ConectarHardware(new MockHardware("12"));
        assertEquals("Não foi possível autenticar o usuário", caixaEletronico.logar());
    }

    @Test
    public void saldoVazio(){
        caixaEletronico.logar();
        assertEquals("O saldo é R$0.0", caixaEletronico.saldo());
    }

    @Test
    public void depositar(){
        caixaEletronico.logar();
        assertEquals("Deposito recebido com sucesso", caixaEletronico.depositar(100));
        assertEquals("O saldo é R$100.0", caixaEletronico.saldo());
    }

    @Test
    public void depositarNumeroNegativo(){
        caixaEletronico.logar();
        assertEquals("Valor inválido", caixaEletronico.depositar(-100));
    }

    @Test
    public void sacarComSucesso(){
        caixaEletronico.logar();
        caixaEletronico.depositar(100);
        assertEquals("Retire seu dinheiro", caixaEletronico.sacar(100));
        assertEquals("O saldo é R$0.0", caixaEletronico.saldo());
    }

    @Test
    public void sacarComSaldoInsuficiente(){
        caixaEletronico.logar();
        assertEquals("O saldo é R$0.0", caixaEletronico.saldo());
        assertEquals("Saldo insuficiente",  caixaEletronico.sacar(100));
    }


    @Test
    public void testarHardwareQuebradoNoLogin(){
        caixaEletronico.ConectarHardware(hardwareQuebrado());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> caixaEletronico.logar());
        assertEquals("Caixa eletronico com defeito, solicite ajuda ao operador", exception.getMessage());
    }

    @Test
    public void testarHardwareQuebradoNoDeposito(){
        caixaEletronico.logar();
        caixaEletronico.ConectarHardware(hardwareQuebrado());
        assertEquals("Caixa eletronico com defeito, solicite ajuda ao operador", caixaEletronico.depositar(100));
    }

    @Test
    public void testarHardwareQuebradoNoSaque(){
        caixaEletronico.logar();
        caixaEletronico.depositar(100);
        caixaEletronico.ConectarHardware(hardwareQuebrado());
        assertEquals("Caixa eletronico com defeito, solicite ajuda ao operador", caixaEletronico.sacar(100));
    }


}
