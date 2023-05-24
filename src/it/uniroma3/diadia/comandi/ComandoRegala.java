package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoRegala extends AbstractComando{
	private IO io = new IOConsole();
	private String attrezzo;
	private String nome = "ComandoRegala";
	
	//per ora funziona come posa, lo leva dalla borsa e lo mette nella stanza
	//penso andrà levato dalla borsa del giocatore e messo in quella del personaggio nella stanza >>
	
	public ComandoRegala() {
	}
	
	public ComandoRegala(String attrezzo) {
		this.attrezzo = attrezzo;
	}
	
	@Override
	public void esegui(Partita partita) {
		Attrezzo a = partita.getGiocatore().getBorsa().getAttrezzo(attrezzo);
		if (a!=null) {
			partita.getStanzaCorrente().getPersonaggio().riceviRegalo(a, partita);  
			partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo);
		}
		else {
			io.mostraMessaggio("L'attrezzo "+this.attrezzo+" non e' presente nella borsa, NON puoi regalarlo");  
		}
	}
	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public void setParametro(String parametro) {
		this.attrezzo = parametro;
	}
	@Override
	public String getNome() {
		return this.nome;
	}
	
	@Override
	public String getParametro() {
		return this.attrezzo;
	}

}
