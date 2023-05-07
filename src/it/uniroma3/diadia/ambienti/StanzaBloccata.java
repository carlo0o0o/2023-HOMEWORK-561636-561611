package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccata extends Stanza{

	private String attrezzoSbloccante;
	private String direzioneBloccata;
	
	public StanzaBloccata(String nome,Attrezzo attrezzo, String direzione) {
		super(nome);
		this.attrezzoSbloccante = attrezzo.getNome();
		this.direzioneBloccata = direzione;
	}

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