package launchPattern;

import java.util.Scanner;

import client.ClientTCP;

public class MainClient {

	public static void main(String[] args) {
		ClientTCP myClt = new ClientTCP("localhost", 6666 );
		
		Scanner sc = new Scanner(System.in);
		
		int i = 0;
		if ( myClt.connecterAuServeur() ) {
			while(i!=10) {
			System.out.println("Veuillez saisir un mot :");
			String str = sc.nextLine();
			i++;
			myClt.transmettreChaine(str);
			//myClt.deconnecterDuServeur();
			}
		}
	
	}

}
