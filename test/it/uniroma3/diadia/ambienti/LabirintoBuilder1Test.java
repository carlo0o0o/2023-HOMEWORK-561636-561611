package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
				.addAdiacenza("biblioteca", "atrio", "nord")
				.addStanza("campus")
				.addAttrezzo("chiave", 2)
				.addStanzaBloccata("bloccata", "ovest", "chiave" )
				.addAttrezzo("lanterna", 6)
				.addAdiacenza("atrio", "campus", "est")
				.addStanzaBuia("buia", "lanterna")
				.addAttrezzo("lanterna", 1)
				.addAdiacenza("campus", "bloccata","nord")
				.addAdiacenza("bloccata", "buia", "ovest")
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
		assertEquals("atrio", labirinto.getIngresso().getStanzaAdiacente("nord").getNome());
		assertEquals("campus", labirinto.getIngresso().getStanzaAdiacente("nord").getStanzaAdiacente("est").getNome());
	}
	
	@Test
	void buiaBloccataTest() {
		Attrezzo chiave = new Attrezzo("chiave", 2);
		assertEquals("bloccata", labirinto.getIngresso().getStanzaAdiacente("nord").getStanzaAdiacente("est").getStanzaAdiacente("nord").getStanzaAdiacente("ovest").getNome());
		assertTrue(labirinto.getIngresso().getStanzaAdiacente("nord").getStanzaAdiacente("est").getStanzaAdiacente("nord").addAttrezzo(chiave));
	}
	

}
