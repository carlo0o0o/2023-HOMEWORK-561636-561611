


/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	static final private int CFU_INIZIALI = 20;

	private Labirinto labirinto;
	private boolean finita;
	private int cfu;
	
	public Partita(){
		labirinto = new Labirinto();                     //inv labirinto
		labirinto.creaStanze();
		this.finita = false;
		this.cfu = CFU_INIZIALI;
	}

	public Labirinto getLabirinto() {
		return labirinto;
	}
	
	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;                     
	}
	
	
	public boolean vinta() {
		return this.labirinto.getStanzaCorrente()== this.labirinto.getStanzaVincente();
	}
	
	public boolean isFinita() {
		return finita || vinta() || (cfu == 0);
	}
	
	public void setFinita() {
		this.finita = true;
	}

	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}	
	
}
