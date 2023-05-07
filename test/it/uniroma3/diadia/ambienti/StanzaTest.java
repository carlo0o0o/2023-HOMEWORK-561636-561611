package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {
	
	private Attrezzo torcia;
	private Attrezzo martello;
	private Stanza s1;
	private Stanza s2;
	private Stanza s3;
	private Stanza s4;
	
	@BeforeEach
	public void setUp() {
		this.torcia = new Attrezzo("torcia", 3);
		this.s1 = new Stanza("s1");
		this.s2 = new Stanza("s2");
		this.s3 = new Stanza("s3");
		this.s4 = new Stanza("s4");
		
		this.martello = new Attrezzo("martello", 10);
		this.s1.addAttrezzo(martello);
	}
	
	@Test
	public void impostaStanzaAdiacenteTest() {
		s1.impostaStanzaAdiacente("nord", s2);
		assertTrue(s1.getStanzaAdiacente("nord").equals(s2));
	}
	@Test
	public void impostaStanzaAdiacentedueTest() {
		s1.impostaStanzaAdiacente("nord", s3);
		s1.impostaStanzaAdiacente("sud", s4);
		assertTrue(s1.getStanzaAdiacente("nord").getNome().equals(s3.getNome()));
	}
	
	@Test
	public void impostaAltraStanzaAdiacenteTest() {            //correggere lo scorrimento della collezione ritorna solo la prima stanza
		s1.impostaStanzaAdiacente("sud", s4);
		s1.impostaStanzaAdiacente("nord", s2);
		s1.impostaStanzaAdiacente("est", s3);
		assertTrue(s1.getStanzaAdiacente("est").equals(s3));  
	}

	@Test
	public void testAddAttrezzo() {
		assertTrue(s1.addAttrezzo(torcia));
		assertEquals(false, s1.addAttrezzo(torcia));
	}
	
	@Test
	public void testHasAttrezzo() {
		assertEquals(true, s1.hasAttrezzo("martello"));
	}
	
	@Test
	public void removeAttrezzoTest() {
		assertTrue(s1.removeAttrezzo(martello));
	}
	
	@Test
	public void testGetStanzaAdiacente() {
		assertNull(s1.getStanzaAdiacente("sud"));
	}
	
}
