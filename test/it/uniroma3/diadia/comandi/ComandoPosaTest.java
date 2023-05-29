package it.uniroma3.diadia.comandi;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static it.uniroma3.diadia.Direzione.*;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPosaTest {

	private Attrezzo pala;
	private Partita partita;
	
	
	@BeforeEach
	public void setUp() {
		Labirinto labirinto = new Labirinto.LabirintoBuilder()
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
				.addAdiacenza("Laboratorio Campus", "Aula N11", OVEST)
				.addAdiacenza("Biblioteca", "atrio", SUD)
				.getLabirinto();
	
		this.partita = new Partita(labirinto);
		this.pala = new Attrezzo("pala", 8);
		this.partita.getGiocatore().getBorsa().addAttrezzo(pala);
	}
	@Test
	void assenzaInBorsaTest() {  	 //verifica che l'attrezzo sia stato levato dalla borsa correttamente
		ComandoPosa cmd = new ComandoPosa("pala");
		cmd.esegui(this.partita);
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("pala"),"mi aspettavo che l'attrezzo non fosse presente nella borsa");
	}
	
	@Test
	void presenzaInStanzaTest() {     //verifica che l'attrezzo sia stato riposto correttamente nella stanza 
		ComandoPosa cmd = new ComandoPosa(pala.getNome());
		cmd.esegui(this.partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(pala.getNome()),"mi aspettavo che l'attrezzo fosse presente nella stanza");
	}

}
