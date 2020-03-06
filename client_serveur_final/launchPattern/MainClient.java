package launchPattern;

import java.util.Scanner;

import client.ClientConsole;
import client.ClientTCP;

public class MainClient {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		ClientConsole myClt = new ClientConsole("localhost", 6666 );
		if ( myClt.connectionAuServeurBase())
		{
			System.out.println("connection au serveur reussi veuillez vous authentifier");
			do {
				// cette boucle ne se termine qu'apres une authentification reussie
				System.out.println("entrez votre nom");
				String nomUtilisateur = sc.nextLine();
				System.out.println("entrez votre mot de passe");
				String motDePasse = sc.nextLine();
				
				myClt.setUtilisateurInfos(nomUtilisateur, motDePasse);
				
				myClt.connectionAuServeur();
				
			}while( !myClt.getConnectionState() );
			
			//une fois faite l'authentification
			
			
		}else {
			System.out.println("echec de connection");
		}
	}

}
