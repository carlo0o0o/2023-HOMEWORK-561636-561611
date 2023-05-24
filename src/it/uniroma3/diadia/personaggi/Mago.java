package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio{

	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
			"con una mia magica azione, troverai un nuovo oggetto " +
			"per il tuo borsone!";
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	private static final String MESSAGGIO_RINGRAZIAMENTO = "grazie in bocca al lupo!";
	
	private Attrezzo attrezzo;
	private IO io = new IOConsole();
	
	public Mago(String nome, String presentazione) {
		super(nome, presentazione);
		this.attrezzo = new Attrezzo("attrezzoRegalatoDaMago", 0);
	}
	
	@Override
	public String agisci(Partita partita) {
		String msg = null;
		if (attrezzo != null) {
			partita.getStanzaCorrente().addAttrezzo(attrezzo);
			attrezzo = null;
			msg = MESSAGGIO_DONO;
		} else {
			msg = MESSAGGIO_SCUSE;
		}
		return msg;
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {   //riceve dimezza peso e buitta in stanza
		Attrezzo modificato = new Attrezzo(attrezzo.getNome(),attrezzo.getPeso()/2);
		partita.getStanzaCorrente().addAttrezzo(modificato);
		io.mostraMessaggio("ho ricevuto il tuo regalo...modifichero il peso e lo mettero nella stanza, poi potrai prenderlo");
		return MESSAGGIO_RINGRAZIAMENTO;
	}
}
