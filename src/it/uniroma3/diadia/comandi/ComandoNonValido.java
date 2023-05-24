package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando {
	
	private IO io = new IOConsole();
	private String nome = "comandoNonValido";
	
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("comando non valido");
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
