package it.uniroma3.diadia.ambienti;

import static it.uniroma3.diadia.Direzione.EST;
import static it.uniroma3.diadia.Direzione.SUD;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.StringReader;

import org.junit.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;

public class CaricatoreLabirintoTest {
	
	private CaricatoreLabirinto caricatoreLabirinto;
	private Labirinto labirinto;
	private final static String MONOLOCALE = 
			 "Stanze: biblioteca\n"
			+ "Inizio: biblioteca\n"
			+ "Vincente: biblioteca";

	private final static String BILOCALE = 
			 "Stanze: biblioteca, atrio\n"
			+ "Inizio: biblioteca\n"
			+ "Vincente: atrio\n"
			+ "Magiche: \n"
			+ "Buie: \n"
			+ "Bloccate: \n"
			+ "Attrezzi: \n"
			+ "Uscite: biblioteca est atrio, atrio ovest biblioteca";
	
	private final static String TRILOCALE_CON_ATTREZZI = 
			 "Stanze: biblioteca, atrio, N11\n"
			+ "Inizio: biblioteca\n"
			+ "Vincente: atrio\n"
			+ "Magiche: \n"
			+ "Buie: \n"
			+ "Bloccate: \n"
			+ "Attrezzi: osso 3 atrio\n"
			+ "Uscite: biblioteca sud atrio, atrio nord biblioteca";
	
	private final static String MONOLOCALE_MAGICO = 
			  "Stanze: N18\n"
			+ "Inizio: N18\n"
			+ "Vincente: N18\n"
			+ "Magiche: N18 2\n"
			+ "Buie: \n"
			+ "Bloccate: \n"
			+ "Attrezzi: \n"
			+ "Uscite: \n";
	
	private final static String MONOLOCALE_BUIO = 
			  "Stanze: N18\n"
			+ "Inizio: N18\n"
			+ "Vincente: N18\n"
			+ "Magiche: \n"
			+ "Buie: N18 lampada\n"
			+ "Bloccate: \n"
			+ "Attrezzi: \n"
			+ "Uscite: \n";	
	private final static String MONOLOCALE_BLOCCATO = 
			  "Stanze: N18\n"
			+ "Inizio: N18\n"
			+ "Vincente: N18\n"
			+ "Magiche: \n"
			+ "Buie: \n"
			+ "Bloccate: N18 nord chiave\n"	
			+ "Attrezzi: \n"
			+ "Uscite: \n";
			
	private final static String MONOLOCALE_MAGO = 
			  "Stanze: N18\n"
			+ "Inizio: N18\n"
			+ "Vincente: N18\n"
			+ "Magiche: \n"
			+ "Buie: \n"
			+ "Bloccate: \n"
			+ "Attrezzi: \n"
			+ "Uscite: \n"
			+ "Maghi: Pancione Ciao-sono-il-mago-pancione bastone 3 N18\n";
	
	private final static String MONOLOCALE_CANE = 
			  "Stanze: N18\n"
			+ "Inizio: N18\n"
			+ "Vincente: N18\n"
			+ "Magiche: \n"
			+ "Buie: \n"
			+ "Bloccate: \n"
			+ "Attrezzi: \n"
			+ "Uscite: \n"
			+ "Maghi: \n"
			+ "Streghe: \n"
			+ "Cani: Bobby Wof-wof-bark-bork carne N18";
	
	@Test
	public void testCaricaDaFile() throws FileNotFoundException, FormatoFileNonValidoException {
		this.caricatoreLabirinto = new CaricatoreLabirinto("testLabirinto.txt");
		this.caricatoreLabirinto.carica();
		this.labirinto = this.caricatoreLabirinto.getLabirinto();
		assertEquals("N10", this.labirinto.getIngresso().getNome());
		assertEquals("N11", this.labirinto.getStanzaVincente().getNome());
	}
	
	@Test
	public void testCaricaMonolocaleStringa() throws FileNotFoundException, FormatoFileNonValidoException {
		this.caricatoreLabirinto = new CaricatoreLabirinto(new StringReader(MONOLOCALE));
		this.caricatoreLabirinto.carica();
		this.labirinto = this.caricatoreLabirinto.getLabirinto();
		
		assertEquals("biblioteca", this.labirinto.getIngresso().getNome());
	}
	
	@Test
	public void testCaricaBilocaleStringa() throws FileNotFoundException, FormatoFileNonValidoException {
		this.caricatoreLabirinto = new CaricatoreLabirinto(new StringReader(BILOCALE));
		this.caricatoreLabirinto.carica();
		this.labirinto = this.caricatoreLabirinto.getLabirinto();
		
		assertEquals("biblioteca", this.labirinto.getIngresso().getNome());
		assertEquals("atrio", this.labirinto.getStanzaVincente().getNome());
		assertEquals("atrio", this.labirinto.getIngresso().getStanzaAdiacente(EST).getNome());

	}
	
	@Test
	public void testCaricaTrilocaleConAttrezziStringa() throws FileNotFoundException, FormatoFileNonValidoException {
		this.caricatoreLabirinto = new CaricatoreLabirinto(new StringReader(TRILOCALE_CON_ATTREZZI));
		this.caricatoreLabirinto.carica();
		this.labirinto = this.caricatoreLabirinto.getLabirinto();
		
		assertEquals("osso", this.labirinto.getStanzaVincente().getAttrezzo("osso").getNome());
		assertEquals(3, this.labirinto.getStanzaVincente().getAttrezzo("osso").getPeso());
		assertEquals("atrio", this.labirinto.getIngresso().getStanzaAdiacente(SUD).getNome());
	}
	
	@Test
	public void testCaricaMonolocaleMagicoStringa() throws FileNotFoundException, FormatoFileNonValidoException {
		this.caricatoreLabirinto = new CaricatoreLabirinto(new StringReader(MONOLOCALE_MAGICO));
		this.caricatoreLabirinto.carica();
		this.labirinto = this.caricatoreLabirinto.getLabirinto();
		
		Stanza stanzaMagica = this.labirinto.getIngresso();
		assertEquals("N18", stanzaMagica.getNome());
	}
	
	@Test
	public void testCaricaMonolocaleBuioStringa() throws FileNotFoundException, FormatoFileNonValidoException {
		this.caricatoreLabirinto = new CaricatoreLabirinto(new StringReader(MONOLOCALE_BUIO));
		this.caricatoreLabirinto.carica();
		this.labirinto = this.caricatoreLabirinto.getLabirinto();
		
		Stanza stanzaBuia = this.labirinto.getIngresso();
		assertEquals("N18", stanzaBuia.getNome());
	}
	
	@Test
	public void testCaricaMonolocaleBloccatoStringa() throws FileNotFoundException, FormatoFileNonValidoException {
		this.caricatoreLabirinto = new CaricatoreLabirinto(new StringReader(MONOLOCALE_BLOCCATO));
		this.caricatoreLabirinto.carica();
		this.labirinto = this.caricatoreLabirinto.getLabirinto();
		
		Stanza stanzaBloccata = this.labirinto.getIngresso();
		assertEquals("N18", stanzaBloccata.getNome());
	}
	
	@Test
	public void testCaricaMagoMonolocaleStringa() throws FileNotFoundException, FormatoFileNonValidoException {
		this.caricatoreLabirinto = new CaricatoreLabirinto(new StringReader(MONOLOCALE_MAGO));
		this.caricatoreLabirinto.carica();
		this.labirinto = this.caricatoreLabirinto.getLabirinto();
		
		assertEquals("Pancione", this.labirinto.getIngresso().getPersonaggio().getNome());
		assertEquals("Ciao sono il mago pancione ", this.labirinto.getIngresso().getPersonaggio().getPresentazione());
	}
	
	@Test
	public void testCaricaCaneMonolocaleStringa() throws FileNotFoundException, FormatoFileNonValidoException {
		this.caricatoreLabirinto = new CaricatoreLabirinto(new StringReader(MONOLOCALE_CANE));
		this.caricatoreLabirinto.carica();
		this.labirinto = this.caricatoreLabirinto.getLabirinto();
		
		assertEquals("Bobby", this.labirinto.getIngresso().getPersonaggio().getNome());
		assertEquals("Wof wof bark bork ", this.labirinto.getIngresso().getPersonaggio().getPresentazione());
	}
}

