package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuia extends Stanza{
	
    private Attrezzo attrezzoIlluminatore;     //attrezzo che fa vedere la descrizione della stanza
    private String stringa;

    public StanzaBuia(String nome, Attrezzo attrezzo) {
        super(nome);
        this.attrezzoIlluminatore = new Attrezzo(attrezzo.getNome(),attrezzo.getPeso());    //li prende correttamente
    }
    
    
    @Override
    public String getDescrizione() {
    	this.stringa = "\nATTENZIONE STANZA BUIA!\nla stanza non contiene l'attrezzo: "+ attrezzoIlluminatore.getNome();
    	if(hasAttrezzo(attrezzoIlluminatore.getNome())) {
    		return ("La stanza è illuminata ecco a te la descrizione \n"+toString());
    	}
    	return stringa;     //quando e buia stampa uguale i cfu, perche fanno parte del giocatore non della stanza ovviamente
    }
}   
