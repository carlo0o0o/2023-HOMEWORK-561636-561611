package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class CaneTest {

	private Partita partita;
	private Cane cane;
	private Attrezzo cibo;
	private Labirinto labirinto;


	@BeforeEach
	public void setUp() {
		this.labirinto = new LabirintoBuilder()
				.addStanzaIniziale("atrio")
				.addAttrezzo("osso",1)
				.addAttrezzo("passepartout", 4)
				.addStanzaBuia("Aula N11","lanterna")
				.addStanzaBloccata("Aula N10", "ovest", "passepartout")
				.addAttrezzo("lanterna", 3)
				.addStanzaMagica("Laboratorio Campus", 1)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("atrio", "Biblioteca", "nord")
				.addAdiacenza("atrio", "Aula N11", "est")
				.addAdiacenza("atrio", "Aula N10", "sud")
				.addAdiacenza("atrio", "Laboratorio Campus", "ovest")
				.addAdiacenza("Aula N11", "Laboratorio Campus", "est")
				.addAdiacenza("Aula N11", "atrio", "ovest")
				.addAdiacenza("Aula N10", "atrio", "nord")
				.addAdiacenza("Aula N10", "Aula N11", "est")
				.addAdiacenza("Aula N10", "Laboratorio Campus", "ovest")
				.addAdiacenza("Laboratorio Campus", "atrio", "est")
				.addAdiacenza("Laboratorio Campus", "Aula N11", "ovest")
				.addAdiacenza("Biblioteca", "atrio", "sud")
				.getLabirinto();
		this.partita = new Partita(labirinto);
		this.cane = new Cane("test", "presentazioneTest");
		this.cibo = new Attrezzo("croccantini", 0);
	}

	@Test
	void agisciTest() {
		this.cane.agisci(partita);
		assertEquals(19, partita.getGiocatore().getCfu());
	}

	@Test
	void riceviRegaloTest() {
		this.cane.riceviRegalo(cibo, partita);
		assertEquals(20, partita.getGiocatore().getCfu());
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("attRegalatoDaCane"));
	}


}
