package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.giocatore.Giocatore;

class PartitaTest {
	
	private Labirinto labirinto;
	private Giocatore giocatore;
	private Partita partita;
	
	@BeforeEach
	public void setUp() {
		this.labirinto=new Labirinto();
		this.giocatore=new Giocatore();
		this.partita=new Partita();
		
	}
	
	
	@Test 
	public void testIsFinita() {
		assertEquals(true, partita.isFinita());
	}

}
