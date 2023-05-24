package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

public class ComandoAiuto extends AbstractComando {

	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa","interagisci","saluta","regala"};
	static final private String[] elencoDirezioni = {"nord", "sud", "est", "ovest"};             
	
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
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String getNome() {
		return this.nome;
	}
}
