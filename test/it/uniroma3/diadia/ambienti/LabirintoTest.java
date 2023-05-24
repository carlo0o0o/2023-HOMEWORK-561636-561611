package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import static it.uniroma3.diadia.Direzione.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabirintoTest {
	
	private Stanza stanza1;
	private Stanza stanza2;
	private Stanza stanzaInizialeTest;
	private Stanza stanzaVincenteTest;
	
	@BeforeEach
	public void setUp() {
		stanza1 = new Stanza("stanza1");
		stanza2 = new Stanza("stanza2");
		stanza1.impostaStanzaAdiacente(NORD, stanza2);
		stanza2.impostaStanzaAdiacente(SUD, stanza1);
		stanzaInizialeTest = stanza2;
		stanzaVincenteTest = stanza1;
	}
	
	@Test
	void getIngressoTest() {
		assertEquals(true, stanzaInizialeTest.equals(stanza2));
	}
	
	@Test
	void getStanzaVincenteTest() {
		assertEquals(true, stanzaVincenteTest.equals(stanza1));
	}
	

}
