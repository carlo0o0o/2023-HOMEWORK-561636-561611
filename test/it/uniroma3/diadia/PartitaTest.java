package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.validator.PublicClassValidator;

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
	
	
//	@Test 
//	public void testIsFinita() {
//		assertEquals(false, partita.isFinita());
//	}

}
