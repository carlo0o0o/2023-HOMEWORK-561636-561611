package it.uniroma3.diadia.personaggi;

import static it.uniroma3.diadia.Direzione.EST;
import static it.uniroma3.diadia.Direzione.NORD;
import static it.uniroma3.diadia.Direzione.OVEST;
import static it.uniroma3.diadia.Direzione.SUD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static it.uniroma3.diadia.Direzione.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class MagoTest {

	
	private Partita partita;
	private Mago mago;
	private Attrezzo regalo;
	private Labirinto labirinto;


	@BeforeEach
	public void setUp() {
		this.labirinto = new LabirintoBuilder()
				.addStanzaIniziale("atrio")
				.addAttrezzo("osso",1)
				.addAttrezzo("passepartout", 4)
				.addStanzaBuia("Aula N11","lanterna")
				.addStanzaBloccata("Aula N10", OVEST, "passepartout")
				.addAttrezzo("lanterna", 3)
				.addStanzaMagica("Laboratorio Campus", 1)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("atrio", "Biblioteca", NORD)
				.addAdiacenza("atrio", "Aula N11", EST)
				.addAdiacenza("atrio", "Aula N10", SUD)
				.addAdiacenza("atrio", "Laboratorio Campus", OVEST)
				.addAdiacenza("Aula N11", "Laboratorio Campus", EST)
				.addAdiacenza("Aula N11", "atrio", OVEST)
				.addAdiacenza("Aula N10", "atrio", NORD)
				.addAdiacenza("Aula N10", "Aula N11", EST)
				.addAdiacenza("Aula N10", "Laboratorio Campus", OVEST)
				.addAdiacenza("Laboratorio Campus", "atrio", EST)
				.addAdiacenza("Laboratorio Campus", "Aula N11",OVEST)
				.addAdiacenza("Biblioteca", "atrio", SUD)
				.getLabirinto();
		this.partita = new Partita(labirinto);
		this.regalo = new Attrezzo("regalo", 4);
		this.mago = new Mago("magoTest", "descrizioneTest");
	}
	
	@Test
	void agisciTest() {
		mago.agisci(partita);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("attrezzoRegalatoDaMago"));
	}
	
	@Test
	void riceviRegaloTest() {
		mago.riceviRegalo(regalo, partita);
		assertEquals(2 ,partita.getStanzaCorrente().getAttrezzo(regalo.getNome()).getPeso());
		assertTrue(!partita.getGiocatore().getBorsa().hasAttrezzo(regalo.getNome()));
		
		
	}

}
