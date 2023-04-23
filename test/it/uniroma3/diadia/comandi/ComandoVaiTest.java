package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.comandi.ComandoVai;

class ComandoVaiTest {
	
	private Partita partita;
	
	@BeforeEach
	public void setUp() {
		this.partita=new Partita();
	}
	
	@Test
	void vaiNord() {
		String stanzaAttesa = this.partita.getStanzaCorrente().getStanzaAdiacente("nord").getNome();
		ComandoVai cmd = new ComandoVai("nord");
		cmd.esegui(this.partita);
		assertTrue(this.partita.getStanzaCorrente().getNome().equals(stanzaAttesa), "mi aspettavo di stare in "+stanzaAttesa+" ma mi trovo in "+this.partita.getStanzaCorrente().getNome());
	}
}
