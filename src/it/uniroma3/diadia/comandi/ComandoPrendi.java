package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IO;

public class ComandoPrendi extends AbstractComando{
	
	private IO io = new IOConsole();
	private String attrezzo;
	private String nome = "ComandoPrendi";
	
	public ComandoPrendi() {
		
	}
	public ComandoPrendi(String attrezzo) {
		this.attrezzo = attrezzo;
	}
	@Override
	public void esegui(Partita partita) {
		Attrezzo a = partita.getStanzaCorrente().getAttrezzo(attrezzo);
		if(a!=null) {
			
			partita.getGiocatore().getBorsa().addAttrezzo(a);
			partita.getStanzaCorrente().removeAttrezzo(a);
			io.mostraMessaggio("attrezzo "+a.getNome()+" preso correttamente dalla stanza: " +partita.getStanzaCorrente().getNome());    //stampa per comunicare che l'attrezzo x è stato preso
		}
		else {
			io.mostraMessaggio("L'attrezzo "+this.attrezzo+" non esiste nella stanza: "+partita.getStanzaCorrente().getNome());         //comunicazione inesistenza
		}
	}
	
	@Override
	public void setParametro(String parametro) {
		this.attrezzo = parametro;
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
		return this.attrezzo;
	}
}
