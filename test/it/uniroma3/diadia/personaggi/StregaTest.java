package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StregaTest {

	
	private Partita partita;
	//private Strega strega;
	private Labirinto labirinto;


	@BeforeEach
	public void setUp() {
		this.labirinto = new LabirintoBuilder()
				.addStanzaIniziale("atrio")
				.addAttrezzo("osso",1)
				.addAttrezzo("passepartout", 4)
				.addPersonaggio("strega", "stregaTest", "ciao")
				.addStanzaBuia("Aula N11","lanterna")
				.addStanzaBloccata("Aula N10", "ovest", "passepartout")
				.addAttrezzo("lanterna", 3)
				.addAttrezzo("prova", 0)
				.addStanzaMagica("Laboratorio Campus", 1)
				.addAttrezzo("computer", 0)
				.addStanzaVincente("Biblioteca")
				.addAttrezzo("att", 0)
				.addAdiacenza("atrio", "Biblioteca", "nord")     //bibl : 1    n11:0    n10:2    camp:1
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
		//this.strega = new Strega("stregaTest", "descrizioneTest");
		this.partita = new Partita(labirinto);
	}
	
	@Test                      
	void agisciConSalutoTest() {
		partita.getStanzaCorrente().getPersonaggio().setHaSalutato(true);    //sta a true
		partita.getStanzaCorrente().getPersonaggio().agisci(partita);
		assertEquals("Aula N10", partita.getStanzaCorrente().getNome());
	}
	
	@Test                      
	void agisciSenzaSalutoTest() {
		partita.getStanzaCorrente().getPersonaggio().agisci(partita);
		assertEquals("Aula N11", partita.getStanzaCorrente().getNome());
	}

}
