package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
//import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando{

	private String parametro;
	private String nome;

	public void esegui(Partita partita) {

	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getParametro() {
		return this.parametro;
	}

	public String getNome() {
		return this.nome;
	}
	




}
