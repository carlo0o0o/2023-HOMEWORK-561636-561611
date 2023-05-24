package it.uniroma3.diadia.comandi;

import java.util.Scanner;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {
	//private IO io ;

	@Override
	public AbstractComando costruisciComando(String istruzione) {
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		AbstractComando comando = null;
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next();//prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next();//seconda parola: eventuale parametro


		if(nomeComando != null) {    // ho messo quest' if per risolvere il problema "INVIO", non va bene, dovrebbe far parte di un'eccezione!!!!!!!!!!!
			StringBuilder nomeClasse = new StringBuilder("it.uniroma3.diadia.comandi.Comando");
			nomeClasse.append(Character.toUpperCase(nomeComando.charAt(0)));
			nomeClasse.append(nomeComando.substring(1));
			try {
				comando =(AbstractComando) Class.forName(nomeClasse.toString()).getConstructor().newInstance();
			} catch (Exception e) {
				comando = new ComandoNonValido();
			}
			comando.setParametro(parametro);
		}else {
			comando = new ComandoNonValido();
		}
		return comando;
	}
}
