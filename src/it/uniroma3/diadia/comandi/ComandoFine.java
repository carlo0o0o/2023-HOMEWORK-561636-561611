package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IO;

public class ComandoFine extends AbstractComando {
	
	private IO io = new IOConsole();
	private String nome = "ComandoFine";
	
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("Grazie di aver giocato!");         // si desidera smettere
		partita.setFinita();
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
