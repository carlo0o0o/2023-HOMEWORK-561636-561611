package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class IOSimulatorTest {

	//in questi test verifico che mi trovo nella stanza corretta al termine di una sequenza di istruzioini
	// va cambiato che scrive a video la stanza in cui si trova al termine 
	
	
	@Test
	void testBiblioteca() {      //dopo un piccolo giro mi ritrovo in biblioteca 
		IOSimulator io = new IOSimulator(new String[] {"vai sud", "vai nord","vai nord"});
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
		assertTrue(gioco.getPartita().getStanzaCorrente().getNome().equalsIgnoreCase("biblioteca"));
	}
	
	@Test
	void testAulaN10() {
		IOSimulator io = new IOSimulator(new String[] {"vai sud","vai est", "vai ovest","guarda","prendi osso","vai sud"});
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
		assertTrue(gioco.getPartita().getStanzaCorrente().getNome().equalsIgnoreCase("aula n10"));
	}
	
	@Test
	void testFine() {
		IOSimulator io = new IOSimulator(new String[] {"fine"});
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
		assertTrue(gioco.getPartita().getStanzaCorrente().getNome().equalsIgnoreCase("atrio"));
	}
	
	@Test
	void testN10() {
		IOSimulator io = new IOSimulator(new String[] {"aiuto", "vai est", "guarda", "vai nord", "vai est", "vai est", "vai sud", "prendi lanterna", "vai ovest" });
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
		assertTrue(gioco.getPartita().getStanzaCorrente().getNome().equalsIgnoreCase("aula n10"));
	}
	
	@Test
	void testStanzaBloccata() {
		IOSimulator io = new IOSimulator(new String[] {"aiuto", "prendi passepartout", "vai sud", "posa passepartout", "vai ovest", "guarda"});
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
		assertTrue(gioco.getPartita().getStanzaCorrente().getNome().equalsIgnoreCase("laboratorio campus"));
	} 
	
	@Test
	void testStanzaBuia() {
		IOSimulator io = new IOSimulator(new String[] {"aiuto", "prendi passepartout", "vai sud", "posa passepartout", "prendi lanterna", "guarda", "vai est", "posa lanterna"});
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
		assertTrue(gioco.getPartita().getStanzaCorrente().getNome().equalsIgnoreCase("aula n11"));
	} 
	

}
