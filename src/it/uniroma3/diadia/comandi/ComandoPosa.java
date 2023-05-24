package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IO;


public class ComandoPosa extends AbstractComando {
	
	private IO io = new IOConsole();
	private String attrezzo;
	private String nome = "ComandoPosa";
	
	public ComandoPosa() {
		
	}
	
	public ComandoPosa(String attrezzo) {
		this.attrezzo = attrezzo;
	}
	
	@Override
	public void esegui(Partita partita) {
		Attrezzo a = partita.getGiocatore().getBorsa().getAttrezzo(attrezzo);
		if (a!=null) {
			partita.getStanzaCorrente().addAttrezzo(a);
			partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo);
			io.mostraMessaggio("attrezzo "+a.getNome()+" posato correttamente nella stanza: "+partita.getStanzaCorrente().getNome());
		}
		else {
			io.mostraMessaggio("L'attrezzo "+this.attrezzo+" non e' presente nella stanza: "+partita.getStanzaCorrente().getNome());  
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
