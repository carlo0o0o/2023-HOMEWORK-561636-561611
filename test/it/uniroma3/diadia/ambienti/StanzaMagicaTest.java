package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StanzaMagicaTest {
	
	private Attrezzo martello;
	
	@BeforeEach
	public void setUp() {
		this.martello = new Attrezzo("martello",7);
	}
	
	@Test
	void modificaAttrezzoTest() {      //test che verifica la correttezza del metodo modificaattrezzo
		StanzaMagica stn = new StanzaMagica("test1");
		Attrezzo mod =stn.modificaAttrezzo(martello);
		assertTrue((mod.getNome().equals("olletram"))&&(mod.getPeso() == 14));   
	} 

	
}
