package it.uniroma3.diadia;

public class IOSimulator implements IO{

	//private String ultimoMessaggio;
	//private IO io = new IOConsole();
	
	private String[] elencoComandi;
	private int indiceComando = 0;
	
	public IOSimulator(String[] elencoComandi) {
		this.elencoComandi = elencoComandi;
	}
	
	@Override
	public void mostraMessaggio(String messaggio) {
		//this.ultimoMessaggio = messaggio;
		System.out.println(messaggio);
	}

	@Override
	public String leggiRiga() {
		if(indiceComando>=elencoComandi.length) {
			return "fine";
		}
		return elencoComandi[indiceComando++];
	}
//	public String getUltimoMessaggio() {
//		return ultimoMessaggio;
//	}
}
