package it.uniroma3.diadia;

import java.util.List;

public class IOSimulator implements IO{

	//private String ultimoMessaggio;
	//private IO io = new IOConsole();
	
	private List<String> elencoComandi;
	private int indiceComando = 0;
	
	public IOSimulator(List<String> elencoComandi) {
		this.elencoComandi = elencoComandi;
	}
	
	@Override
	public void mostraMessaggio(String messaggio) {
		//this.ultimoMessaggio = messaggio;
		System.out.println(messaggio);
	}

	@Override
	public String leggiRiga() {
		if(indiceComando>=elencoComandi.size()) {
			return "fine";
		}
		return elencoComandi.get(indiceComando++);
	}
//	public String getUltimoMessaggio() {
//		return ultimoMessaggio;
//	}
}
