package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.Direzione;

public class StanzaBloccata extends Stanza{

	private String attrezzoSbloccante;
	private Direzione direzioneBloccata;
	
	public StanzaBloccata(String nome,Direzione direzione, String attrezzo) {
		super(nome);
		this.attrezzoSbloccante = attrezzo;
		this.direzioneBloccata = direzione;

	}

//	@Override
//	public void setPersonaggio(AbstractPersonaggio personaggio) {
//		this.personaggio = cane;
//	}
	
	@Override
	public Stanza getStanzaAdiacente(Direzione dir) {
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
