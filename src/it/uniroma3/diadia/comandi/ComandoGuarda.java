package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;


public class ComandoGuarda implements Comando{
	
	private IO io = new IOConsole();
	private String nome = "ComandoGuarda";
	private String para;

	@Override
	public void esegui(Partita partita) {
		
		io.mostraMessaggio(" DESCRIZIONE STANZA: \n" + partita.getStanzaCorrente().getDescrizione());
		io.mostraMessaggio("\n INFORMAZIONI GIOCATORE:");
		io.mostraMessaggio("il numero dei cfu è: " + partita.getGiocatore().getCfu());
		io.mostraMessaggio("la borsa contiene: "+ partita.getGiocatore().getBorsa().toString());
	}
	@Override
	public void setParametro(String parametro) {
		this.para = parametro;
		parametro = null;
	}
	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String getNome() {
		return this.nome;
	}
	
	@Override
	public String getParametro() {
		return this.para;
	}
}
