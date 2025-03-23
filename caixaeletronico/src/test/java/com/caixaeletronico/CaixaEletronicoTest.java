package com.caixaeletronico;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;;


public class CaixaEletronicoTest {
    public CaixaEletronico caixaEletronico = new CaixaEletronico();

    @BeforeEach
    public void setup(){
        caixaEletronico = new CaixaEletronico();
    }

    @Test
    public void autenticacao(){
        assertEquals("Usuário autenticado", caixaEletronico.logar("a", "a"));
    }
}
