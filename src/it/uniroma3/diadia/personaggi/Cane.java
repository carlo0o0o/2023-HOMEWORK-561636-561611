package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio{
	private static final String MESSAGGIO_AVVERTIMENTO = "Se ti mordo ti levo un cfu, " +
			"sta a te non farti mordere dandomi il mio cibo preferito, " +
			"se mi dai il mio cibo preferito butto un attrezzo a terra ";
	private static final String MESSAGGIO_MORSO = "ti ho morso ho levato un cfu";
	private static final String MESSAGGIO_RINGRAZIAMENTO = "grazie dei croccantini ";
	
	private String ciboPreferito = "croccantini";
	
	private IO io = new IOConsole();
	
	public Cane(String nome, String presentazione) {
		super(nome, presentazione);
	}
								
	@Override                
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		io.mostraMessaggio("che mi hai dato, io ti mordo!\n");
		return MESSAGGIO_MORSO;
	}
	
	@Override
	public String riceviRegalo(Attrezzo regalo, Partita partita) {   //riceve dimezza peso e buitta in stanza
		if(regalo.getNome().equals(ciboPreferito)) {
			partita.getStanzaCorrente().addAttrezzo(new Attrezzo("attRegalatoDaCane", 2));
			io.mostraMessaggio("grazie bellomio");
			return MESSAGGIO_RINGRAZIAMENTO;
		}
		
		return this.agisci(partita);
	}
	


}
