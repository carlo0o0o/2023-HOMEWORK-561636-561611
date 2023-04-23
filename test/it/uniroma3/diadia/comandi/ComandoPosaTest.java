package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.Partita;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ComandoPosaTest {

	private Attrezzo pala;
	private Partita partita;
	
	@BeforeEach
	public void setUp() {
		this.partita = new Partita();
		this.pala = new Attrezzo("pala", 8);
		this.partita.getGiocatore().getBorsa().addAttrezzo(pala);
	}
	@Test
	void assenzaInBorsaTest() {   //verifica che l'attrezzo sia stato levato dalla borsa correttamente
		ComandoPosa cmd = new ComandoPosa(pala.getNome());
		cmd.esegui(this.partita);
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo(pala.getNome()),"mi aspettavo che l'attrezzo non fosse presente nella borsa");
	}
	
	@Test
	void presenzaInStanzaTest() {     //verifica che l'attrezzo sia stato riposto correttamente nella stanza 
		ComandoPosa cmd = new ComandoPosa(pala.getNome());
		cmd.esegui(this.partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(pala.getNome()),"mi aspettavo che l'attrezzo fosse presente nella stanza");
	}

}
