package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;
import static it.uniroma3.diadia.Direzione.*;
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
				.addStanzaBloccata("Aula N10", OVEST, "passepartout")
				.addAttrezzo("lanterna", 3)
				.addAttrezzo("prova", 0)
				.addStanzaMagica("Laboratorio Campus", 1)
				.addAttrezzo("computer", 0)
				.addStanzaVincente("Biblioteca")
				.addAttrezzo("att", 0)
				.addAdiacenza("atrio", "Biblioteca", NORD)     //bibl : 1    n11:0    n10:2    camp:1
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
