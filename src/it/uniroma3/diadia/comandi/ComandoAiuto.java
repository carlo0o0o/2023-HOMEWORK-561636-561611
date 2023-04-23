package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

public class ComandoAiuto implements Comando {

	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};
	static final private String[] elencoDirezioni = {"nord", "sud", "est", "ovest"};             /*risolto vai sud*/
	
	private IO io = new IOConsole();
	private String nome = "ComandoAiuto";
	
	@Override
	public void esegui(Partita partita){
		for(int i=0; i< elencoComandi.length; i++) 
			io.mostraMessaggio(elencoComandi[i]+" ");
		io.mostraMessaggio("\n");
		for(int i=0; i< elencoDirezioni.length;i++)
			io.mostraMessaggio(elencoDirezioni[i]+" ");
		io.mostraMessaggio("");
	}
	@Override
	public void setParametro(String parametro) {
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
		return null;
	}
}
