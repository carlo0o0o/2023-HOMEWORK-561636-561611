package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoSaluta extends AbstractComando{
	private String nome = "ComandoSaluta";

	private IO io = new IOConsole();

	@Override
	public void esegui(Partita partita) {
		if(partita.getStanzaCorrente().getPersonaggio() != null) {    //il personaggio ce

			io.mostraMessaggio("GIOCATORE: ciao personaggio ");
			io.mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().saluta());
			partita.getStanzaCorrente().getPersonaggio().setHaSalutato(true);
		}
	}
	@Override
	public String getNome() {
		return this.nome;
	}
}
