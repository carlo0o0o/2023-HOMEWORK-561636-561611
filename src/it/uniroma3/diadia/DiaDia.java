package it.uniroma3.diadia;
//import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import static it.uniroma3.diadia.Direzione.EST;
import static it.uniroma3.diadia.Direzione.NORD;
import static it.uniroma3.diadia.Direzione.SUD;

import java.io.FileNotFoundException;
import java.util.Scanner;

import it.uniroma3.diadia.ambienti.CaricatoreLabirinto;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

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
			"I locali sono popolati da strani it.uniroma3.diadia.personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";


	private Partita partita;
	private IO io;


	public DiaDia(IO io) {
		this.partita = new Partita();
		this.io = io;
	}
	
	public DiaDia(Labirinto labirinto, IO io) {
		this.partita = new Partita(labirinto);
		this.io = io;
	}
	
	public Partita getPartita() {
		return partita;
	}

	public void gioca() {
		String istruzione; 

		io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		
		do		
			istruzione = io.leggiRiga();             /*levo gli spazi a dx e sx*/   
		while (!processaIstruzione(istruzione));
	}   
	
	

	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		FabbricaDiComandiRiflessiva factory = new FabbricaDiComandiRiflessiva();
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.vinta())
			io.mostraMessaggio("Hai vinto!");
		if (!this.partita.giocatoreIsVivo())
			io.mostraMessaggio("Hai esaurito i CFU...");

		return this.partita.isFinita();   
	}


	public static void main(String[] argc) {
		Scanner scannerDiLinee = new Scanner(System.in);
		IO io = new IOConsole(scannerDiLinee);
		
		try{
			Labirinto labirinto = creaMappaDaFile("testLabirinto.txt");
			DiaDia gioco = new DiaDia(labirinto,io);
			gioco.gioca();
			
		} catch(FileNotFoundException e) {
			io.mostraMessaggio("Errore nel caricamento del file del labirinto... non esiste!");
		} catch(FormatoFileNonValidoException e) {
			io.mostraMessaggio("Errore nel caricamento del file, il formato è errato!");
		} catch(Exception e) {
			io.mostraMessaggio("qualcosa è andato storto...");
		} finally {
			scannerDiLinee.close();
		}

		Labirinto labirinto=new Labirinto.LabirintoBuilder()   
				.addStanzaIniziale("atrio")
				.addAttrezzo("pala", 2)
				.addAttrezzo("croccantini", 0)
				.addAttrezzo("lanterna", 2)
				.addPersonaggio("strega", "artemisia" , "\nsono una strega ti trasferisco!")
				.addStanzaBloccata("bloccata", NORD, "lanterna")
				.addPersonaggio("mago", "lupino", "mago lupino se mi regali un attrezzo te lo modifico!!")
				.addAdiacenza("atrio", "stanzabloccata", NORD)
				.addStanzaVincente("cucina")
				.addAdiacenza("atrio", "cucina", SUD)
				.addStanzaBuia("bagno","lanterna")
				.addPersonaggio("cane", "rubber", "dammi croccantini o ti levo i cfu§!")
				.addAttrezzo("profumo", 1)
				.addAdiacenza("atrio", "bagno", EST)
				.getLabirinto();
		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
	}
	static public Labirinto creaMappaDaFile(String nomeFile) throws FormatoFileNonValidoException, FileNotFoundException {
		final CaricatoreLabirinto caricatoreLabirinto = new CaricatoreLabirinto(nomeFile);
		caricatoreLabirinto.carica();
		final Labirinto lab = caricatoreLabirinto.getLabirinto();
		lab.setNome(nomeFile);
		return lab;
	}
}