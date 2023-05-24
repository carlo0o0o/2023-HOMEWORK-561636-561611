package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;

class AbstractComandoTest {

	private comandoTest comandoTest;
	private Partita partita;
	
	@BeforeEach
	public void setUp() {
		this.comandoTest = new comandoTest();
		this.partita = new Partita();
	}
	@Test
	void testNome() {
		assertEquals("comandoTest", comandoTest.getNome());
	}
	@Test
	void testParametro() {
		assertEquals("parametro", comandoTest.getParametro());
	}
	@Test
	void testEsegui() {
		comandoTest.esegui(partita);
		assertTrue(partita.isFinita());
	}
}



//****************************COMANDO DI TEST********************************************

class comandoTest extends AbstractComando{
	String nome = "comandoTest";
	String parametro = "parametro";
	
	@Override
	public void esegui(Partita partita) {
		partita.setFinita();
	}

	@Override
	public String getParametro() {
		return this.parametro;
	}

	@Override
	public String getNome() {
		return this.nome;
	}
	
}