package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static it.uniroma3.diadia.Direzione.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class LabirintoBuilder1Test {
	

	Labirinto labirinto;

	@BeforeEach
	public void setUp() {
		this.labirinto = new LabirintoBuilder()
				.addStanzaIniziale("biblioteca")
				.addAttrezzo("pala", 10)
				.addAttrezzo("martello", 3)
				.addStanza("atrio")
				.addAdiacenza("biblioteca", "atrio", NORD)
				.addStanza("campus")
				.addAttrezzo("chiave", 2)
				.addStanzaBloccata("bloccata", OVEST, "chiave" )
				.addAttrezzo("lanterna", 6)
				.addAdiacenza("atrio", "campus", EST)
				.addStanzaBuia("buia", "lanterna")
				.addAttrezzo("lanterna", 1)
				.addAdiacenza("campus", "bloccata",NORD)
				.addAdiacenza("bloccata", "buia", OVEST)
				.getLabirinto();
	}
	
	@Test
	void getIngressoTest() {
		Attrezzo test = new Attrezzo("test", 1);
		assertEquals("biblioteca",labirinto.getIngresso().getNome());
		assertEquals("pala", labirinto.getIngresso().getAttrezzo("pala").getNome());
		assertTrue(labirinto.getIngresso().addAttrezzo(test));
		assertEquals("test", labirinto.getIngresso().getAttrezzo("test").getNome());
		assertEquals("martello", labirinto.getIngresso().getAttrezzo("martello").getNome());
	}
	
	@Test
	void adiacenzeTest() {
		assertEquals("biblioteca",labirinto.getIngresso().getNome());
		assertEquals("atrio", labirinto.getIngresso().getStanzaAdiacente(NORD).getNome());
		assertEquals("campus", labirinto.getIngresso().getStanzaAdiacente(NORD).getStanzaAdiacente(EST).getNome());
	}
	
	@Test
	void buiaBloccataTest() {
		Attrezzo chiave = new Attrezzo("chiave", 2);
		assertEquals("bloccata", labirinto.getIngresso().getStanzaAdiacente(NORD).getStanzaAdiacente(EST).getStanzaAdiacente(NORD).getStanzaAdiacente(OVEST).getNome());
		assertTrue(labirinto.getIngresso().getStanzaAdiacente(NORD).getStanzaAdiacente(EST).getStanzaAdiacente(NORD).addAttrezzo(chiave));
	}
	

}
