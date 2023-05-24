package it.uniroma3.diadia.ambienti;
//import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */


public class Stanza {

	static final private int NUMERO_MASSIMO_DIREZIONI = 4;

	protected String nome;
	protected IOConsole io = new IOConsole();
	protected ArrayList<Attrezzo> attrezzi;
	protected int numeroAttrezzi;
	protected HashMap<String, Stanza> stanzeAdiacenti;
	protected int numeroStanzeAdiacenti;
	protected AbstractPersonaggio personaggio1 = null;

	//	protected List<String> direzioni;


	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */

	public Stanza(String nome) {
		this.nome = nome;
		this.numeroStanzeAdiacenti = 0;
		this.numeroAttrezzi = 0;
		this.stanzeAdiacenti = new HashMap<>();
		this.attrezzi = new ArrayList<>();
	}






	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
		if(direzione.equalsIgnoreCase("nord") || direzione.equalsIgnoreCase("sud") || direzione.equalsIgnoreCase("est") || direzione.equalsIgnoreCase("ovest")) {
			this.stanzeAdiacenti.put(direzione, stanza);
		}


	}
	public HashMap<String, Stanza> getMapStanzeAdiacenti(){
		
		HashMap<String, Stanza> ordinata = new HashMap<>();
		 for(String s: stanzeAdiacenti.keySet()) {
			
		}
		
		return stanzeAdiacenti;
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		if(direzione.equalsIgnoreCase("nord") || direzione.equalsIgnoreCase("sud") || direzione.equalsIgnoreCase("est") || direzione.equalsIgnoreCase("ovest")) {
			return this.stanzeAdiacenti.get(direzione);
		}
		return null;
	}

	/**
	 * Restituisce la nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public ArrayList<Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {

		if(attrezzi.contains(attrezzo)) {
			return false;
		}
		else {
			this.attrezzi.add(attrezzo);
			return true;
		}
	}
	
	public boolean addPersonaggio(AbstractPersonaggio personaggio) {
		
		if(personaggio1 == null) {
			this.personaggio1 = personaggio;
			return true;
		}
		return false;
	}
	
	

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		risultato.append(" " + this.stanzeAdiacenti.keySet());
		risultato.append("\nAttrezzi nella stanza: ");
		for(Attrezzo a : this.attrezzi) {
			risultato.append(a.getNome() + " ");
		}
		//risultato.append(this.attrezzi.values()+" ");

		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {

		for(Attrezzo a : this.attrezzi) {
			if(a.getNome().equals(nomeAttrezzo)) {
				return true;
			}
		}
		return false;
		//return attrezzi.containsKey(nomeAttrezzo);
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {

		for(Attrezzo a : this.attrezzi) {
			if(a.getNome().equals(nomeAttrezzo)) {
				return a;
			}
		}
		return null;
		//return attrezzi.get(nomeAttrezzo);	
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		for(Attrezzo a : this.attrezzi) {
			if(a.getNome().equals(attrezzo.getNome())) {
				attrezzi.remove(a);
				return true;
			}
		}
		return false;

	}
	
	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio1 = personaggio;
	}
	
	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio1;
	}


	public List<String> getDirezioni() {
		return new ArrayList(stanzeAdiacenti.keySet());
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Stanza other = (Stanza) obj;
		return Objects.equals(nome, other.nome);
	}



}