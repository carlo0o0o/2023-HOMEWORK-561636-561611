package it.uniroma3.diadia;
import java.util.Scanner;


public class IOConsole implements IO{
	Scanner scannerDiLinee;
	
	public IOConsole(Scanner scannerDiLinee) {
		this.scannerDiLinee = scannerDiLinee;
	}

	public IOConsole() {
		this.scannerDiLinee = new Scanner(System.in);
	}
	
	@Override
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	@Override
	public String leggiRiga() {
		String riga = scannerDiLinee.nextLine();
		return riga;
	}
}
