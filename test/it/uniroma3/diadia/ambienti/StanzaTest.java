package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import javax.sound.midi.VoiceStatus;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {
	
	private Attrezzo torcia;
	private Attrezzo martello;
	private Stanza s1;
	
	
	@BeforeEach
	public void setUp() {
		this.torcia = new Attrezzo("torcia", 3);
		this.s1 = new Stanza("s1");
		
		this.martello = new Attrezzo("martello", 10);
		this.s1.addAttrezzo(martello);
	}
	

	@Test
	public void testAddAttrezzo() {
		assertEquals(true, s1.addAttrezzo(torcia));
	}
	
	@Test
	public void testHasAttrezzo() {
		assertEquals(true, s1.hasAttrezzo("martello"));
	}
	
	@Test
	public void testGetStanzaAdiacente() {
		assertNull(s1.getStanzaAdiacente("sud"));
	}
	
}
