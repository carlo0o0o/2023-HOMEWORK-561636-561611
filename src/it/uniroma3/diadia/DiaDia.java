package it.uniroma3.diadia;


import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};
	static final private String[] elencoDirezioni = {"nord", "sud", "est", "ovest"};        /*risolto vai sud*/

	private Partita partita;
	private IOConsole io;


	public DiaDia(IOConsole io) {
		this.partita = new Partita();
		this.io = io;
	}

	public void gioca() {
		String istruzione; 
		Scanner scannerDiLinee;

		io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		scannerDiLinee = new Scanner(io.leggiRiga());		
		do		
			istruzione = scannerDiLinee.nextLine().trim();             /*levo gli spazi a dx e sx*/   
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	/**
	 * @param istruzione
	 * @return
	 */
	private boolean processaIstruzione(String istruzione) {
		if (istruzione.length()>0) {
			Comando comandoDaEseguire = new Comando(istruzione);

			if (comandoDaEseguire.getNome().equalsIgnoreCase("fine")) {        /*risolto null pointer*/
				this.fine(); 
				return true;
			} else if (comandoDaEseguire.getNome().equalsIgnoreCase("vai"))
				this.vai(comandoDaEseguire.getParametro());
			else if (comandoDaEseguire.getNome().equalsIgnoreCase("aiuto"))
				this.aiuto();
			else if (comandoDaEseguire.getNome().equalsIgnoreCase("prendi"))
				if (comandoDaEseguire.getParametro()!=null) {
					this.prendi(comandoDaEseguire.getParametro());
				}else {
					io.mostraMessaggio("Devi inserire l'attrezzo");
				}
			else if (comandoDaEseguire.getNome().equalsIgnoreCase("posa"))
				if (comandoDaEseguire.getParametro()!=null) {
					this.posa(comandoDaEseguire.getParametro());
				}else {
					io.mostraMessaggio("Devi posare l'attrezzo");
				}
			else
				io.mostraMessaggio("Comando sconosciuto");
			if (this.partita.vinta()) {
				io.mostraMessaggio("Hai vinto!");
				return true;

			} else
				return false;
		}
		return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			io.mostraMessaggio(elencoComandi[i]+" ");
		io.mostraMessaggio("\n");
		for(int i=0; i< elencoDirezioni.length;i++)
			io.mostraMessaggio(elencoDirezioni[i]+" ");
		io.mostraMessaggio("");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			io.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			io.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu--);
		}
		io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	/*
	 * Comando "Prendi"
	 */
	private void prendi(String nomeAttrezzo) {
		Attrezzo a = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		if(a!=null) {
			this.partita.getGiocatore().getBorsa().addAttrezzo(a);
			this.partita.getStanzaCorrente().removeAttrezzo(a);
		}
		else {
			io.mostraMessaggio("L'attrezzo non esiste");
		}
	}



	/*
	 * Comando "Posa"
	 */
	private void posa(String nomeAttrezzo) {
		Attrezzo a = this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
		if (a!=null) {
			this.partita.getStanzaCorrente().addAttrezzo(a);
			this.partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
		}
		else {
			io.mostraMessaggio("L'attrezzo non esiste");
		}
	}


	/**
	 * Comando "Fine".
	 */
	private void fine() {
		io.mostraMessaggio("Grazie di aver giocato!");         // si desidera smettere
	}

	public static void main(String[] argc) {
		
		IOConsole ioConsole = new IOConsole();
		DiaDia gioco = new DiaDia(ioConsole);
		
		gioco.gioca();
	}
}