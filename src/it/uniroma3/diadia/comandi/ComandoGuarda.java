package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;


public class ComandoGuarda extends AbstractComando{
	
	private IO io = new IOConsole();
	private String nome = "ComandoGuarda";

	@Override
	public void esegui(Partita partita) {
		
		io.mostraMessaggio(" DESCRIZIONE STANZA: \n" + partita.getStanzaCorrente().getDescrizione());
		io.mostraMessaggio("\n INFORMAZIONI GIOCATORE:");
		io.mostraMessaggio("il numero dei cfu è: " + partita.getGiocatore().getCfu());
		io.mostraMessaggio("la borsa contiene: "+ partita.getGiocatore().getBorsa().toString());
		if(partita.getStanzaCorrente().getPersonaggio()!=null) {
			io.mostraMessaggio("il personaggio è : "+partita.getStanzaCorrente().getPersonaggio().getNome());
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
}
