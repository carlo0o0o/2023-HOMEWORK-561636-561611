package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
