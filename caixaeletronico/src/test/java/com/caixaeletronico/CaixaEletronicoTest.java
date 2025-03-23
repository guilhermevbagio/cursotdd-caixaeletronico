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
    }

    @Test
    public void autenticacao(){
        assertEquals("Usuário autenticado", caixaEletronico.logar("11"));
    }

    @Test
    public void autenticacaoFalha(){
        assertEquals("Não foi possível autenticar o usuário", caixaEletronico.logar("12"));
    }

    @Test
    public void saldoVazio(){
        caixaEletronico.logar("11");
        assertEquals("O saldo é R$0.0", caixaEletronico.saldo());
    }

    @Test
    public void depositar(){
        caixaEletronico.logar("11");
        assertEquals("Deposito recebido com sucesso", caixaEletronico.depositar(100));
        assertEquals("O saldo é R$100.0", caixaEletronico.saldo());
    }

    @Test
    public void depositarNumeroNegativo(){
        caixaEletronico.logar("11");
        RuntimeException exception = assertThrows(RuntimeException.class, () -> caixaEletronico.depositar(-100));
        assertEquals("Valor inválido", exception.getMessage());
    }

    @Test
    public void sacarComSucesso(){
        caixaEletronico.logar("11");
        caixaEletronico.depositar(100);
        assertEquals("Retire seu dinheiro", caixaEletronico.sacar(100));
        assertEquals("O saldo é R$0.0", caixaEletronico.saldo());
    }

    @Test
    public void sacarComSaldoInsuficiente(){
        caixaEletronico.logar("11");
        assertEquals("O saldo é R$0.0", caixaEletronico.saldo());
        assertEquals("Saldo insuficiente",  caixaEletronico.sacar(100));
    }
}
