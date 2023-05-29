package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static it.uniroma3.diadia.Direzione.*;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;

class ComandoVaiTest {
	
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
		this.partita=new Partita(labirinto);
	}
	
	@Test
	void vaiNord() {
		String stanzaAttesa = this.partita.getStanzaCorrente().getStanzaAdiacente(NORD).getNome();
		ComandoVai cmd = new ComandoVai();
		cmd.setParametro("nord");
		cmd.esegui(this.partita);
		assertTrue(this.partita.getStanzaCorrente().getNome().equals(stanzaAttesa), "mi aspettavo di stare in "+stanzaAttesa+" ma mi trovo in "+this.partita.getStanzaCorrente().getNome());
	}
}
