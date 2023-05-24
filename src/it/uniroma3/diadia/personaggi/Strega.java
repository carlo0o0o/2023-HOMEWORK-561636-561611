package it.uniroma3.diadia.personaggi;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio{

	private static final String MESSAGGIO_RISATA = "AHAHAHAHAHAH!!!!!!";
	private static final String MESSAGGIO_TRASFERIMENTO = "ti ho trasferito!!";

	private IO io = new IOConsole();
	private String nome = "strega";	
	
	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}
	
	@Override
	public String agisci(Partita partita) {
		if(this.haSalutato()!=false) {
			//trasferisci nella stanza con piu attrezzi;
			int numeroMax=0;
			Stanza nuova = null;
			for(String s : partita.getStanzaCorrente().getMapStanzeAdiacenti().keySet()) {
				int att = partita.getStanzaCorrente().getMapStanzeAdiacenti().get(s).getAttrezzi().size();
					if(att>numeroMax) {
						numeroMax = partita.getStanzaCorrente().getMapStanzeAdiacenti().get(s).getAttrezzi().size();
						nuova = partita.getStanzaCorrente().getMapStanzeAdiacenti().get(s);
					}
			}
			partita.setStanzaCorrente(nuova);
			
		}else{
			//trasferisci nella stanza con meno attrezzi;
			int numeroMin=500;
			Stanza nuova = null;
			for(String s : partita.getStanzaCorrente().getMapStanzeAdiacenti().keySet()) {
				int att = partita.getStanzaCorrente().getMapStanzeAdiacenti().get(s).getAttrezzi().size();
					if(att<numeroMin) {
						numeroMin = partita.getStanzaCorrente().getMapStanzeAdiacenti().get(s).getAttrezzi().size();
						nuova = partita.getStanzaCorrente().getMapStanzeAdiacenti().get(s);
					}
			}
			partita.setStanzaCorrente(nuova);
		}
		return MESSAGGIO_TRASFERIMENTO;
		 
	}
	
	@Override
	public String riceviRegalo(Attrezzo regalo, Partita partita) {
		io.mostraMessaggio(MESSAGGIO_RISATA);
		return MESSAGGIO_RISATA;
	}
	
	@Override
	public String getNome() {
		return this.nome;
	}

	
}
