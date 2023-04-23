package it.uniroma3.diadia.ambienti;
import static org.junit.jupiter.api.Assertions.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StanzaBuiaTest {

	private StanzaBuia stanzaTest;
	private Attrezzo lanterna;
	
	@BeforeEach
	public void setUp() {
		this.lanterna = new Attrezzo("lanterna", 4);
		this.stanzaTest = new StanzaBuia("stanzaTest",lanterna);
	}
	@Test   //quando la stanza è buia
	void getDescrizione() {
		assertTrue(stanzaTest.getDescrizione().equals("\nATTENZIONE STANZA BUIA!\nla stanza non contiene l'attrezzo: "+lanterna.getNome()));
	}
	
	@Test   //quando la stanza e illuminata
	void getDescrizione1() {
		stanzaTest.addAttrezzo(lanterna);
		assertTrue(stanzaTest.getDescrizione().equals("La stanza è illuminata ecco a te la descrizione \n"+stanzaTest.toString()));
	}

}
