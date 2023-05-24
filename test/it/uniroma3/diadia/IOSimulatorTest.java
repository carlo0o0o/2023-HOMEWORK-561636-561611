package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import static it.uniroma3.diadia.Direzione.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

class IOSimulatorTest {

	private Labirinto labirinto = null;
	@BeforeEach
	public void setUp() {
		this.labirinto = new LabirintoBuilder()
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
				
				
				
				
				
				
	}
	
	
//	@Test
//	void testBiblioteca() {      //dopo un piccolo giro mi ritrovo in biblioteca 
//		labirinto.addStanza("uno").addStanza("due").addStanza("tre").addAdiacenza("uno", "due", "nord")
//		.addAdiacenza("due", "tre", "nord").getLabirinto();
//		List<String> list = new ArrayList<>();
//		list.add("vai nord");
//		list.add("vai nord");
//		IOSimulator io = new IOSimulator(list);
//		DiaDia gioco = new DiaDia(labirinto, io);
//		gioco.gioca();
//		assertTrue(gioco.getPartita().getStanzaCorrente().getNome().equalsIgnoreCase("tre"));
//	}
	
	@Test
	void testAulaN10() {
		IOSimulator io = new IOSimulator(Arrays.asList(new String[] {"vai sud","vai est", "vai ovest","guarda","prendi osso","vai sud"}));
		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
		assertTrue(gioco.getPartita().getStanzaCorrente().getNome().equalsIgnoreCase("aula n10"));
	}
	
	@Test
	void testFine() {
		IOSimulator io = new IOSimulator(Arrays.asList(new String[] {"fine"}));
		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
		assertTrue(gioco.getPartita().getStanzaCorrente().getNome().equalsIgnoreCase("atrio"));
	}
	
	@Test
	void testN10() {
		IOSimulator io = new IOSimulator(Arrays.asList(new String[] {"aiuto", "vai est", "guarda", "vai nord", "vai est", "vai est", "vai sud", "prendi lanterna", "vai ovest" }));
		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
		assertTrue(gioco.getPartita().getStanzaCorrente().getNome().equalsIgnoreCase("aula n10"));
	}
	
	@Test
	void testStanzaBloccata() {
		IOSimulator io = new IOSimulator(Arrays.asList(new String[] {"aiuto", "prendi passepartout", "vai sud", "posa passepartout", "vai ovest", "guarda"}));
		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
		assertTrue(gioco.getPartita().getStanzaCorrente().getNome().equalsIgnoreCase("Laboratorio Campus"));
	} 
	
	@Test
	void testStanzaBuia() {
		IOSimulator io = new IOSimulator(Arrays.asList(new String[] {"aiuto", "prendi passepartout", "vai sud", "posa passepartout", "prendi lanterna", "guarda", "vai est", "posa lanterna"}));
		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
		assertTrue(gioco.getPartita().getStanzaCorrente().getNome().equalsIgnoreCase("aula n11"));
	} 
	
}
