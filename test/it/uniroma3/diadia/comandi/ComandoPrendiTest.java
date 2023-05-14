package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
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
	private Labirinto labirinto = null;
	
	@BeforeEach
	public void setUp() {
		this.labirinto = new LabirintoBuilder()
				.addStanzaIniziale("atrio")
				.addAttrezzo("osso",1)
				.addAttrezzo("passepartout", 4)
				.addStanzaBuia("Aula N11","lanterna")
				.addStanzaBloccata("Aula N10", "passepartout", "ovest")
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
	
		this.partita = new Partita(this.labirinto);
		this.test1 = new Attrezzo("test1",3);
		this.partita.getLabirinto().getIngresso().addAttrezzo(test1);
		this.nomeAttrezzo = test1.getNome();
	}
	
	@Test    //se l'attrezzo nel caso base si trova all'interno della stanza 
	void esistenzaTest() {
		assertTrue(this.partita.getLabirinto().getIngresso().hasAttrezzo(nomeAttrezzo), "mi aspettavo che l'attrezzo stesse nella stanza");
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
