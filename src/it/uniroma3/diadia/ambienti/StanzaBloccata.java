package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;

public class StanzaBloccata extends Stanza{

	private String attrezzoSbloccante;
	private String direzioneBloccata;
	
	public StanzaBloccata(String nome,String direzione, String attrezzo) {
		super(nome);
		this.attrezzoSbloccante = attrezzo;
		this.direzioneBloccata = direzione;

	}

//	@Override
//	public void setPersonaggio(AbstractPersonaggio personaggio) {
//		this.personaggio = cane;
//	}
	
	@Override
	public Stanza getStanzaAdiacente(String dir) {
		if((dir.equals(direzioneBloccata)) && !hasAttrezzo(attrezzoSbloccante)) {
			io.mostraMessaggio("direzione bloccata sei rimasto io stanza: "+this.getNome());
			return this;
		}
		return super.getStanzaAdiacente(dir);
	}
	
	@Override
	public String getDescrizione() {
		return ("l'attrezzo sbloccante e': "+attrezzoSbloccante+"\n"+super.toString());
    }
	
	 
	
}
