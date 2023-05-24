package it.uniroma3.diadia.comandi;
import static it.uniroma3.diadia.Direzione.*;

import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {
	
	private IO io = new IOConsole();
	private Direzione direzione;
	private String nome = "ComandoVai";
	
	public ComandoVai() {	
	}
	
	public ComandoVai(Direzione direzione) {
		this.direzione = direzione;
	}

	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		
		if(direzione==null) {
			io.mostraMessaggio("Dove vuoi andare?  Devi specificare una direzione");
			return;
		}
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
		if(prossimaStanza==null) {
			io.mostraMessaggio("Direzione inesistente");
			return;
		}
		partita.setStanzaCorrente(prossimaStanza);
		io.mostraMessaggio(partita.getStanzaCorrente().getNome());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
	}
	
	@Override
	public void setParametro(String parametro) {
		if(parametro.equalsIgnoreCase("nord")) {
			direzione = NORD;
		}else if(parametro.equalsIgnoreCase("sud")) {
			direzione = SUD;
		}else if(parametro.equalsIgnoreCase("est")) {
			direzione = EST;
		}else if(parametro.equalsIgnoreCase("ovest")) {
			direzione = OVEST;
		}
	}
	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String getNome() {
		return this.nome;
	}
	
//	@Override
//	public String getParametro() {
//		return this.direzione;
//	}
}

