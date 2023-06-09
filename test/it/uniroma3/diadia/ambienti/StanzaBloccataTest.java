 package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import static org.junit.jupiter.api.Assertions.*;
import static it.uniroma3.diadia.Direzione.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StanzaBloccataTest {
	
	private StanzaBloccata stanza;
	private Stanza stanza2;
	private Attrezzo chiave;

	@BeforeEach
	public void setUp() {
		this.chiave = new Attrezzo("chiave", 3);
		this.stanza = new StanzaBloccata("stanza", OVEST, "chiave");
		this.stanza2 = new Stanza("stanza2");
		
	}

	@Test     //caso in cui ho la chiave per la direzione bloccata
	void getStanzaAdiacenteTest() {	
		stanza.impostaStanzaAdiacente(OVEST, stanza2);
		stanza.addAttrezzo(chiave);   
		assertTrue(stanza.getStanzaAdiacente(OVEST).getNome().equals(stanza2.getNome()));
	}
	
	@Test    //caso in cui non ho la chiave per la direzione bloccata
	void getStanzaAdiacenteTest1() {		  
		assertTrue(stanza.getStanzaAdiacente(OVEST).getNome().equals(stanza.getNome()));
	}
}
