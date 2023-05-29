package it.uniroma3.diadia.ambienti;

import static it.uniroma3.diadia.Direzione.EST;
import static it.uniroma3.diadia.Direzione.NORD;
import static it.uniroma3.diadia.Direzione.OVEST;
import static it.uniroma3.diadia.Direzione.SUD;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilderTestProfessore {
	private Labirinto.LabirintoBuilder labirintoBuilder;
	private String nomeStanzaIniziale = "Atrio";
	private String nomeStanzaVincente = "Uscita";

	@Before
	public void setUp() throws Exception {
		labirintoBuilder = new Labirinto.LabirintoBuilder();
	}

	@Test
	public void testMonolocale() {
		Labirinto monolocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaIniziale)
				.getLabirinto();
	assertEquals(nomeStanzaIniziale,monolocale.getIngresso().getNome());
	assertEquals(nomeStanzaIniziale,monolocale.getStanzaVincente().getNome());
	}
	
	@Test
	public void testMonolocaleConAttrezzo() {
		Labirinto monolocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale).addAttrezzo("spada",1)
				.addStanzaVincente(nomeStanzaIniziale).addAttrezzo("spadina", 3)
				.getLabirinto();
		assertEquals(nomeStanzaIniziale,monolocale.getIngresso().getNome());
		assertEquals(nomeStanzaIniziale,monolocale.getStanzaVincente().getNome());
		assertEquals("spada",monolocale.getIngresso().getAttrezzo("spada").getNome());
		assertEquals("spadina",monolocale.getStanzaVincente().getAttrezzo("spadina").getNome());
	}
	
	@Test
	public void testMonolocaleConAttrezzoSingoloDuplicato() {
		Labirinto monolocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addAttrezzo("spada",1)
				.addAttrezzo("spada",1)
				.getLabirinto();
		int size = monolocale.getIngresso().getAttrezzi().size();
		assertTrue(size==1);
		assertEquals(Arrays.asList(new Attrezzo("spada",1)),monolocale.getIngresso().getAttrezzi());
	}
	
	@Test
	public void testBilocale() {
		Labirinto bilocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza(nomeStanzaIniziale, nomeStanzaVincente, NORD)
				.addAdiacenza(nomeStanzaVincente, nomeStanzaIniziale, SUD)
				.getLabirinto();
		assertEquals(bilocale.getStanzaVincente(),bilocale.getIngresso().getStanzaAdiacente(NORD));
		assertEquals(Collections.singletonList(NORD),bilocale.getIngresso().getDirezioni());
		assertEquals(Collections.singletonList(SUD),bilocale.getStanzaVincente().getDirezioni());
	}
	
	@Test
	public void testTrilocale(){
		Labirinto trilocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale).addAttrezzo("sedia", 1)
				.addStanza("biblioteca")
				.addAdiacenza(nomeStanzaIniziale, "biblioteca", SUD)
				.addAdiacenza("biblioteca", nomeStanzaIniziale, NORD)
				.addAttrezzo("libro antico", 5)
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza("biblioteca", nomeStanzaVincente, EST)
				.addAdiacenza(nomeStanzaVincente,"biblioteca" , OVEST)
				.getLabirinto();	
		assertEquals(nomeStanzaIniziale, trilocale.getIngresso().getNome());
		assertEquals(nomeStanzaVincente, trilocale.getStanzaVincente().getNome());
		assertEquals("biblioteca",trilocale.getIngresso().getStanzaAdiacente(SUD).getNome());
	}
	
	@Test
	public void testTrilocaleConStanzaDuplicata() {
				labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanza("stanza generica")
				.addStanza("stanza generica")
				.addAdiacenza(nomeStanzaIniziale, "stanza generica", NORD)
				.getLabirinto();
		assertTrue(labirintoBuilder.getListaStanze().size()<=2);
	}
	
	@Test
	public void testPiuDiQuattroAdiacenti() {
		Labirinto maze = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanza("stanza 1")
				.addStanza("stanza 2")
				.addStanza("stanza 3")
				.addStanza("stanza 4")
				.addStanza("stanza 5")
				.addAdiacenza(nomeStanzaIniziale, "stanza 1", NORD)
				.addAdiacenza(nomeStanzaIniziale, "stanza 2", OVEST)
				.addAdiacenza(nomeStanzaIniziale, "stanza 3", SUD)
				.addAdiacenza(nomeStanzaIniziale, "stanza 4", EST)
				//.addAdiacenza(nomeStanzaIniziale, "stanza 5", NORD-EST) // non dovrebbe essere aggiunta
				.getLabirinto();
				Stanza test = new Stanza("stanza 5");
		//assertNull(maze.getIngresso().getStanzaAdiacente(NORD-EST));
		assertTrue(maze.getIngresso().getMapStanzeAdiacenti().size()<=4);
		assertTrue(!maze.getIngresso().getMapStanzeAdiacenti().containsValue(test));
		Map<Direzione,Stanza> mappa = new HashMap<>();
		mappa.put(NORD, new Stanza("stanza 1"));
		mappa.put(OVEST, new Stanza("stanza 2"));
		mappa.put(SUD, new Stanza("stanza 3"));
		mappa.put(EST, new Stanza("stanza 4"));
		assertEquals(mappa,maze.getIngresso().getMapStanzeAdiacenti());
	}
	
	@Test
	public void testImpostaStanzaInizialeCambiandola() {
		Labirinto maze = labirintoBuilder
				.addStanzaIniziale(this.nomeStanzaIniziale)
				.addStanza("nuova iniziale")
				.addStanzaIniziale("nuova iniziale")
				.getLabirinto();
		assertEquals("nuova iniziale",maze.getIngresso().getNome());
	}
	
	@Test
	public void testAggiuntaAttrezziAStanze_Iniziale() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		Labirinto maze = this.labirintoBuilder
				.addStanzaIniziale(this.nomeStanzaIniziale)
				.addAttrezzo(nomeAttrezzo, peso)
				.getLabirinto();
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo,peso);
		assertEquals(attrezzo,maze.getIngresso().getAttrezzo(nomeAttrezzo));
	}
	
//	@Test
//	public void testAggiuntaAttrezziAStanze_AppenaAggiunte() {
//		String nomeAttrezzo = "attrezzo";
//		int peso = 1;
//		String nomeStanza = "stanza 1";
//		labirintoBuilder
//				.addStanzaIniziale(nomeStanzaIniziale)
//				.addStanza(nomeStanza)
//				.addAttrezzo(nomeAttrezzo, peso)
//				.getLabirinto();
//		assertTrue(this.labirintoBuilder.getListaStanze().get(nomeStanza).getAttrezzi().contains(new Attrezzo (nomeAttrezzo,peso)));
//		assertEquals(new Attrezzo(nomeAttrezzo,peso),this.labirintoBuilder.getListaStanze().get(nomeStanza).getAttrezzo(nomeAttrezzo));
//	}
	
//	@Test
//	public void testAggiuntaAttrezzoAStanze_AppenaAggiunteMultiple() {
//		String nomeAttrezzo = "attrezzo";
//		int peso = 1;
//		String nomeStanza = "stanza 1";
//		this.labirintoBuilder
//				.addStanzaIniziale(nomeStanzaIniziale)
//				.addStanza(nomeStanza)
//				.addAttrezzo(nomeAttrezzo, peso)
//				.getLabirinto();
//		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo,peso);
//		List<Attrezzo> attrezzi = labirintoBuilder.getListaStanze().get(nomeStanza).getAttrezzi();
//		assertEquals(attrezzo,attrezzi.get(attrezzi.indexOf(attrezzo)));
//	}
	
	@Test
	public void testAggiuntaAttrezzoAStanze_MoltepliciAttrezziStessaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		this.labirintoBuilder
		.addStanza(nomeStanza1)
		.addAttrezzo(nomeAttrezzo1, peso1)
		.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = labirintoBuilder.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo2,peso2),listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo2));
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1),listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo1));
	}
	
	
	@Test  //verifico che gli attrezzi vengano aggiunti all'ultima stanza aggiunta
	public void testAggiuntaAttrezzoAStanze_AttrezzoAggiuntoAllaSecondaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		String nomeStanza2 = "Stanza 2";
		this.labirintoBuilder
		.addStanza(nomeStanza1)
		.addStanza(nomeStanza2)
		.addAttrezzo(nomeAttrezzo1, peso1)
		.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = labirintoBuilder.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1),listaStanze.get(nomeStanza2).getAttrezzo(nomeAttrezzo1));
		assertEquals(new Attrezzo(nomeAttrezzo2,peso2),listaStanze.get(nomeStanza2).getAttrezzo(nomeAttrezzo2));
	}
	
	@Test
	public void testAggiuntaAttrezzoAStanze_PrimoAttrezzoInUnaStanzaSecondoAttrezzoSecondaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		String nomeStanza2 = "Stanza 2";
		this.labirintoBuilder
		.addStanza(nomeStanza1)
		.addAttrezzo(nomeAttrezzo1, peso1)
		.addStanza(nomeStanza2)
		.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = labirintoBuilder.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1),listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo1));
		assertEquals(new Attrezzo(nomeAttrezzo2,peso2),listaStanze.get(nomeStanza2).getAttrezzo(nomeAttrezzo2));
	}
	
//	@Test
//	public void testLabirintoConStanzaMagica() {
//		int sogliaMagica = 1;
//		String nomeStanzaMagica = "Stanza Magica";
//		this.labirintoBuilder
//		.addStanzaMagica(nomeStanzaMagica, sogliaMagica);
//		StanzaMagica stanzaMagica = (StanzaMagica)labirintoBuilder.getListaStanze().get(nomeStanzaMagica);
//		assertTrue(stanzaMagica.isMagica());
//	}
	
	@Test
	public void testLabirintoConStanzaMagica_AggiuntaElementoOltreSogliaMagica() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		String nomeAttrezzo2Inv = "2 ozzertta";
		int sogliaMagica = 1;
		int peso1 = 1;
		int peso2 = 2;
		int peso2_x2 = peso2*2;
		String nomeStanzaMagica = "Stanza Magica";
		this.labirintoBuilder
		.addStanzaMagica(nomeStanzaMagica, sogliaMagica)
		.addAttrezzo(nomeAttrezzo1, peso1)
		.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = labirintoBuilder.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo2Inv,peso2_x2), listaStanze.get(nomeStanzaMagica).getAttrezzo(nomeAttrezzo2Inv));
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1), listaStanze.get(nomeStanzaMagica).getAttrezzo(nomeAttrezzo1));
	}
	
	
	@Test
	public void testLabirintoConStanzaBloccata_ConPassepartout() {
		this.labirintoBuilder
		.addStanzaIniziale(nomeStanzaIniziale)
		.addStanzaBloccata("stanza bloccata", NORD, "chiave").addAttrezzo("chiave", 1)
		.addAdiacenza(nomeStanzaIniziale, "stanza bloccata", NORD)
		.addAdiacenza("stanza bloccata", nomeStanzaIniziale, SUD)
		.addStanzaVincente(nomeStanzaVincente)
		.addAdiacenza("stanza bloccata", nomeStanzaVincente, NORD)
		.addAdiacenza(nomeStanzaVincente, "stanza bloccata", SUD);
		Stanza stanzaVincente = new Stanza(nomeStanzaVincente);
		//Asserisce che in presenza di passepartout, invocato il metodo getStanzaAdiacente(), la stanza bloccata ritorna la corretta adiacenza
		assertEquals(stanzaVincente,labirintoBuilder.getListaStanze().get("stanza bloccata").getStanzaAdiacente(NORD));	
	}
	
	@Test
	public void testLabirintoConStanzaBloccata_SenzaPassepartout() {
		this.labirintoBuilder
		.addStanzaIniziale(nomeStanzaIniziale)
		.addStanzaBloccata("stanza bloccata", NORD, "chiave")
		.addAdiacenza(nomeStanzaIniziale, "stanza bloccata", NORD)
		.addAdiacenza("stanza bloccata", nomeStanzaIniziale, SUD)
		.addStanzaVincente(nomeStanzaVincente)
		.addAdiacenza("stanza bloccata", nomeStanzaVincente, NORD)
		.addAdiacenza(nomeStanzaVincente, "stanza bloccata", SUD);
		Stanza stanzaBloccata = new StanzaBloccata("stanza bloccata", NORD, "chiave");
		//Asserisce che in caso di mancanza di passepartout, invocato il metodo getStanzaAdiacente(), la stanza bloccata ritorna se stessa
		assertEquals(stanzaBloccata,labirintoBuilder.getListaStanze().get("stanza bloccata").getStanzaAdiacente(NORD));
	}
	
	@Test
	public void testLabirintoCompletoConTutteLeStanze() {
		
		Labirinto labirintoCompleto = this.labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaVincente)
				.addStanza("corridoio")
				.addAttrezzo("chiave", 1)
				.addAttrezzo("lanterna", 1)
				.addStanzaBloccata("corridoio bloccato",NORD,"chiave")
				.addStanzaMagica("stanza magica", 1)
				.addStanzaBuia("stanza buia","lanterna")
				.addStanza("Aula 1")
				.addAdiacenza(nomeStanzaIniziale, "corridoio", NORD)
				.addAdiacenza("corridoio", nomeStanzaIniziale, SUD)
				.addAdiacenza("corridoio", "corridoio bloccato", NORD)
				.addAdiacenza("corridoio bloccato", "corridoio", SUD)
				.addAdiacenza("corridoio bloccato", "Aula 1", NORD)
				.addAdiacenza("Aula 1", "corridoio bloccato", SUD)
				.addAdiacenza("Aula 1", nomeStanzaVincente, NORD)
				.addAdiacenza(nomeStanzaVincente, "Aula 1", SUD)
				.addAdiacenza("corridoio", "stanza magica", EST)
				.addAdiacenza("stanza magica", "corridoio", OVEST)
				.addAdiacenza("corridoio", "stanza buia", OVEST)
				.addAdiacenza("stanza buia", "corridoio", EST)
				.getLabirinto();
		assertEquals(nomeStanzaIniziale, labirintoCompleto.getIngresso().getNome());
		assertEquals(nomeStanzaVincente, labirintoCompleto.getStanzaVincente().getNome());
		Stanza corridoio = labirintoCompleto.getIngresso().getStanzaAdiacente(NORD);
		assertEquals("corridoio",corridoio.getNome());
		assertTrue(corridoio.getDirezioni().containsAll(Arrays.asList(OVEST,EST,NORD,SUD)));
		Map<Direzione,Stanza> mapAdiacenti = new HashMap<>();
		mapAdiacenti.put(NORD,new Stanza("corridoio bloccato"));
		mapAdiacenti.put(SUD,new Stanza(nomeStanzaIniziale));
		mapAdiacenti.put(EST,new Stanza("stanza magica"));
		mapAdiacenti.put(OVEST,new Stanza("stanza buia"));
		assertEquals(mapAdiacenti,corridoio.getMapStanzeAdiacenti());
		Attrezzo a1 = new Attrezzo("chiave",1);
		Attrezzo a2 = new Attrezzo("lanterna",1);
		assertEquals(Arrays.asList(a1,a2),corridoio.getAttrezzi());
	}
}
