package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ComandoPrendiTest {
	
	private Partita partita;
	private Attrezzo test1;
	private String nomeAttrezzo;

	
	@BeforeEach
	public void setUp() {
		this.partita = new Partita();
		this.test1 = new Attrezzo("test1",3);
		this.partita.getStanzaCorrente().addAttrezzo(test1);
		this.nomeAttrezzo = test1.getNome();
	}
	
	@Test    //se l'attrezzo nel caso base si trova all'interno della stanza 
	void esistenzaTest() {
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo), "mi aspettavo che l'attrezzo stesse nella stanza");
	}
	
	@Test    //se l'attrezzo è stato rimosso correttamente dalla stanza test
	void eseguiTest() {  
		ComandoPrendi cmd= new ComandoPrendi(nomeAttrezzo);
		cmd.esegui(this.partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo));
	}
	
	@Test    //se l'attrezzo è stato posizionato all'interno della borsa del giocatore
	void esegui1Test() {
		ComandoPrendi cmd= new ComandoPrendi(nomeAttrezzo);
		cmd.esegui(this.partita);
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo),"mi aspettavo che l'attrezzo stesse nella borsa");
	}

}
