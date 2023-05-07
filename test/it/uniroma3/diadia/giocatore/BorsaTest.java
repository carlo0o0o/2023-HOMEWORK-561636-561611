package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {

	private Attrezzo torcia;
	private Attrezzo martello;
	private Attrezzo uno;
	private Attrezzo due;
	private Attrezzo tre;
	private Attrezzo quattro;
	private Attrezzo cinque;
	private Attrezzo sei;
	private Attrezzo sette;
	private Attrezzo otto;
	private Attrezzo attrezzoVerifica;
	private Borsa zaino;
	
	@BeforeEach
	public void setUp() {
		this.torcia = new Attrezzo("torcia",3);
		this.martello = new Attrezzo("martello", 2);
		this.uno = new Attrezzo("uno", 2);
		this.due = new Attrezzo("due", 2);
		this.tre = new Attrezzo("tre", 2);
		this.quattro = new Attrezzo("quattro", 2);
		this.cinque = new Attrezzo("cinque", 2);
		this.sei = new Attrezzo("sei", 2);
		this.sette = new Attrezzo("sette", 2);
		this.otto = new Attrezzo("otto", 2);
		this.attrezzoVerifica = new Attrezzo("attrezzoVerifica", 2);
		this.zaino = new Borsa();
	}
	@Test
	void addAttrezzoTest() {
		assertTrue(zaino.addAttrezzo(martello));   //che il metodo aggiunga l'attrezzo
	}
	
	@Test
	void getAttrezzoTest() {
		zaino.addAttrezzo(torcia);
		zaino.addAttrezzo(martello);
		zaino.addAttrezzo(due);
		assertEquals("due" , zaino.getAttrezzo(due.getNome()).getNome());
	}
	
	@Test
	void removeAttrezzoTest() {
		zaino.addAttrezzo(martello);
		assertEquals("martello", zaino.removeAttrezzo(martello.getNome()).getNome());
	}
	
	@Test
	void removeSenzaAttrezziTest() {
		assertEquals(null, zaino.removeAttrezzo(martello.getNome()));
	}
	
	
	@Test
	void addPiuDiDieciAttrezziTest() {        //11 attrezzi in borsa
		zaino.addAttrezzo(martello);
		zaino.addAttrezzo(torcia);
		zaino.addAttrezzo(uno);
		zaino.addAttrezzo(due);
		zaino.addAttrezzo(tre);
		zaino.addAttrezzo(quattro);
		zaino.addAttrezzo(cinque);
		zaino.addAttrezzo(sei);
		zaino.addAttrezzo(sette);
		zaino.addAttrezzo(otto);
		zaino.addAttrezzo(attrezzoVerifica);
		assertEquals("attrezzoVerifica", zaino.getAttrezzo(attrezzoVerifica.getNome()).getNome());
		
	}

}
