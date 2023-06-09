package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

//	static final private int CFU_INIZIALI = 20;

	private Labirinto labirinto;
	private Giocatore giocatore;
	private boolean finita;
	private Stanza stanzaCorrente;
	
	
	public Partita(){
		labirinto = new Labirinto();                     //inv labirinto
		giocatore = new Giocatore(); 
		this.stanzaCorrente = this.labirinto.getIngresso();
		this.finita = false;

	}
	
	public Partita(Labirinto labirinto) {
		this.labirinto=labirinto;
		giocatore = new Giocatore(); 
		this.stanzaCorrente = this.labirinto.getIngresso();
		this.finita = false;
	}

	public Labirinto getLabirinto() {
		return labirinto;
	}
	
	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;                     
	}
	
	public Giocatore getGiocatore() {                 //GIOCATORE
		return giocatore;
	}
	
	public void setGiocatore(Giocatore giocatore) {    //GIOCATORE
		this.giocatore = giocatore;
	}
	
	
	public boolean vinta() {
		return this.getStanzaCorrente()== this.labirinto.getStanzaVincente();
	}
	
	public boolean isFinita() {
		return finita || vinta() || (giocatore.getCfu() == 0);
	}
	
	public void setFinita() {
		this.finita = true;
	}
	
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	public boolean giocatoreIsVivo() {
		if((giocatore.getCfu())>0)
			return true;
		return false;
	}
}
