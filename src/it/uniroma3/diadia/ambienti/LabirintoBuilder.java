package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

//classe dedita alla creazione di oggetti labirinto
public class LabirintoBuilder {

	private Labirinto labirinto;
	private Stanza ultimaStanzaCreata;

	private Map<String,Stanza> mStanze;



	public LabirintoBuilder() {
		this.labirinto = new Labirinto();
		this.mStanze = new HashMap<>();
	}

	public LabirintoBuilder addStanzaIniziale(String nomeStanza)  {
		this.ultimaStanzaCreata = makeStanza(nomeStanza);
		labirinto.setIngresso(ultimaStanzaCreata);
		return this;
	}
	
	public Map<String,Stanza> getListaStanze(){
		
		return mStanze;
	}

	public LabirintoBuilder addStanzaVincente(String nomeStanza)  {
		this.ultimaStanzaCreata = makeStanza(nomeStanza);
		labirinto.setStanzaVincente(ultimaStanzaCreata);
		return this;
	}

	public LabirintoBuilder addAttrezzo(String nome,int peso){
		Attrezzo attrezzo = new Attrezzo(nome, peso);
		this.ultimaStanzaCreata.addAttrezzo(attrezzo);
		return this;
	}

	public LabirintoBuilder addAdiacenza(String stanza, String adiacente, Direzione direzione) {
		Stanza partenza = null;
		Stanza adiacente1 = null;

		if(mStanze.containsKey(stanza)) {
			partenza = mStanze.get(stanza);
		}else {
			return this;
		}
		if(mStanze.containsKey(adiacente)) {
			adiacente1 = mStanze.get(adiacente);
		}else {
			return this;
		}
//		if(partenza.getStanzaAdiacente(direzione) == null) {            
			partenza.impostaStanzaAdiacente(direzione, adiacente1);
//			if(direzione.equals("nord")) {
//				adiacente1.impostaStanzaAdiacente("sud", partenza);
//			}
//			if(direzione.equals("sud")) {
//				adiacente1.impostaStanzaAdiacente("nord", partenza);
//			}
//			if(direzione.equals("est")) {
//				adiacente1.impostaStanzaAdiacente("ovest", partenza);
//			}
//			if(direzione.equals("ovest")) {
//				adiacente1.impostaStanzaAdiacente("est", partenza);
//			}
//		}

		return this;
	}
	
	public LabirintoBuilder addPersonaggio(String tipo, String nome, String presentazione) {
		if(tipo.equalsIgnoreCase("strega")) {
			this.ultimaStanzaCreata.addPersonaggio(new Strega(nome,presentazione));
		}else if(tipo.equalsIgnoreCase("cane")) {
			this.ultimaStanzaCreata.addPersonaggio(new Cane(nome, presentazione));
		}else if(tipo.equalsIgnoreCase("mago")) {
			this.ultimaStanzaCreata.addPersonaggio(new Mago(nome, presentazione));
		}
		return this;
	}

	public LabirintoBuilder addStanza(String nomeStanza)  {
		this.ultimaStanzaCreata = makeStanza(nomeStanza);
		return this;
	}

	public LabirintoBuilder addStanzaBuia(String nomeStanza, String attrezzo)  {
		this.ultimaStanzaCreata = makeStanzaBuia(nomeStanza, attrezzo);
		return this;
	}
	public LabirintoBuilder addStanzaMagica(String nomeStanza, int soglia)  {
		this.ultimaStanzaCreata = makeStanzaMagica(nomeStanza, soglia);
		return this;
	}

	public LabirintoBuilder addStanzaBloccata(String nomeStanza, Direzione direzione, String attrezzo)  {
		this.ultimaStanzaCreata = makeStanzaBloccata(nomeStanza, direzione, attrezzo);
		return this;
	}

	public Labirinto getLabirinto() {
		return this.labirinto;
	}

	private Stanza makeStanza(String nome) {

		if(mStanze.containsKey(nome)) {
			return mStanze.get(nome);
		}
		Stanza stanza = new Stanza(nome);
		mStanze.put(nome, stanza);
		return stanza;

	}

	private Stanza makeStanzaBuia(String nome, String attrezzo) {

		if(mStanze.containsKey(nome)) {
			return mStanze.get(nome);
		}
		Stanza stanza = new StanzaBuia(nome,attrezzo);
		mStanze.put(nome, stanza);
		return stanza;
	}

	private Stanza makeStanzaBloccata(String nome, Direzione direzione, String attrezzo) {

		if(mStanze.containsKey(nome)) {
			return mStanze.get(nome);
		}
		Stanza stanza = new StanzaBloccata(nome,direzione,attrezzo);
		mStanze.put(nome, stanza);
		return stanza;
	}

	private Stanza makeStanzaMagica(String nome,int soglia) {

		if(mStanze.containsKey(nome)) {
			return mStanze.get(nome);
		}
		Stanza stanza = new StanzaMagica(nome, soglia);
		mStanze.put(nome, stanza);
		return stanza;
	}

}

