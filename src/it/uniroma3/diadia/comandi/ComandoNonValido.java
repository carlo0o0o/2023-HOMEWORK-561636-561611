package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

public class ComandoNonValido implements Comando {
	
	private IO io = new IOConsole();
	private String nome = "comandoNonValido";
	private String para;
	
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("comando non valido");
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
