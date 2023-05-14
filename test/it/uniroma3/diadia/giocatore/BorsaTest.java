package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {

	private Attrezzo torcia;
	private Attrezzo martello;
	private Attrezzo uno;
	private Attrezzo due;
	private Attrezzo tre;
	private Attrezzo quattro;
	private Attrezzo cinque;
	private Attrezzo sei;
	private Attrezzo sette;
	private Attrezzo otto;
	private Attrezzo attrezzoVerifica;
	private Borsa zaino;
	
	@BeforeEach
	public void setUp() {
		this.torcia = new Attrezzo("torcia",3);
		this.martello = new Attrezzo("martello", 2);
		this.uno = new Attrezzo("uno", 2);
		this.due = new Attrezzo("due", 4);
		this.tre = new Attrezzo("tre", 2);
		this.quattro = new Attrezzo("quattro", 2);
		this.cinque = new Attrezzo("cinque", 2);
		this.sei = new Attrezzo("sei", 2);
		this.sette = new Attrezzo("sette", 2);
		this.otto = new Attrezzo("otto", 2);
		this.attrezzoVerifica = new Attrezzo("attrezzoVerifica", 2);
		this.zaino = new Borsa();
	}
	@Test
	void addAttrezzoTest() {
		assertTrue(zaino.addAttrezzo(martello));   //che il metodo aggiunga l'attrezzo
	}
	
	@Test
	void getAttrezzoTest() {
		zaino.addAttrezzo(torcia);
		zaino.addAttrezzo(martello);
		zaino.addAttrezzo(due);
		assertEquals("due" , zaino.getAttrezzo(due.getNome()).getNome());
	}
	
	@Test
	void removeAttrezzoTest() {
		zaino.addAttrezzo(torcia);
		zaino.addAttrezzo(martello);
		zaino.addAttrezzo(due);
		zaino.addAttrezzo(martello);
		assertEquals("due", zaino.removeAttrezzo(due.getNome()).getNome());
		assertFalse(zaino.hasAttrezzo("due"));
		
	}
	
	@Test
	void removeSenzaAttrezziTest() {
		assertEquals(null, zaino.removeAttrezzo(martello.getNome()));
	}
	
	
	@Test
	void addPiuDiDieciAttrezziTest() {        //11 attrezzi in borsa
		zaino.addAttrezzo(martello);
		zaino.addAttrezzo(torcia);
		zaino.addAttrezzo(uno);
		zaino.addAttrezzo(due);
		zaino.addAttrezzo(tre);
		zaino.addAttrezzo(quattro);
		zaino.addAttrezzo(cinque);
		zaino.addAttrezzo(sei);
		zaino.addAttrezzo(sette);
		zaino.addAttrezzo(otto);
		zaino.addAttrezzo(attrezzoVerifica);
		assertEquals("attrezzoVerifica", zaino.getAttrezzo(attrezzoVerifica.getNome()).getNome());
		
	}
	
	@Test
	void sortAttrezzoTest() {
		zaino.addAttrezzo(new Attrezzo("ultimo", 2));
		zaino.addAttrezzo(new Attrezzo("primo", 1));
		zaino.addAttrezzo(new Attrezzo("secondo", 2));

		List<Attrezzo> lis = zaino.getContenutoOrdinatoPerPeso();
		assertEquals(3, lis.size());
		assertEquals("primo", lis.get(0).getNome());
		assertEquals("secondo", lis.get(1).getNome());
		assertEquals("ultimo", lis.get(2).getNome());
	}
	
	@Test
	void sortAttrezzoNomeTest() {
		zaino.addAttrezzo(new Attrezzo("ultimo", 2));
		zaino.addAttrezzo(new Attrezzo("marco", 2));
		zaino.addAttrezzo(new Attrezzo("augusto", 1));

		SortedSet<Attrezzo> set = zaino.getContenutoOrdinatoPerNome();
		assertEquals(3, set.size());
		assertEquals("augusto", set.first().getNome());
		assertEquals("ultimo", set.last().getNome());
		
		
	}
	
	@Test
	void getContenutoRaggruppatoPerPesoTest() {
		zaino.addAttrezzo(new Attrezzo("ultimo", 2));
		zaino.addAttrezzo(new Attrezzo("marco", 2));
		zaino.addAttrezzo(new Attrezzo("ccc", 2));
		zaino.addAttrezzo(new Attrezzo("augusto", 1));

		Map<Integer,Set<Attrezzo>> m = zaino.getContenutoRaggruppatoPerPeso();
		assertEquals(2, m.keySet().size());
		assertEquals(3, m.get(2).size());
		assertEquals(1, m.get(1).size());		
	}
	
	@Test
	void getSortedSetOrdinatoPerPesoTest() {
		zaino.addAttrezzo(new Attrezzo("ultimo", 2));
		zaino.addAttrezzo(new Attrezzo("marco", 3));
		zaino.addAttrezzo(new Attrezzo("augusto", 1));
		zaino.addAttrezzo(new Attrezzo("antonio", 2));

		SortedSet<Attrezzo> set = zaino.getSortedSetOrdinatoPerPeso();
		assertEquals(4, set.size());
		assertEquals("augusto", set.first().getNome());
		assertEquals("marco", set.last().getNome());
	}
	
	@Test
	void getSortedSetOrdinatoPerPesoDueAttrezziStessoNomePesoDiversoTest() {
		zaino.addAttrezzo(new Attrezzo("antonio", 1));
		zaino.addAttrezzo(new Attrezzo("antonio", 2));

		SortedSet<Attrezzo> set = zaino.getSortedSetOrdinatoPerPeso();
		assertEquals(2, set.size());
		assertEquals("antonio", set.first().getNome());
		assertEquals("antonio", set.last().getNome());
	}

}
