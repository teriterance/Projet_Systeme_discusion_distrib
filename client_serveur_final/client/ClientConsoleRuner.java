package client;

import java.util.Scanner;

public class ClientConsoleRuner {
	private ClientConsole myClt;
	
	public ClientConsoleRuner(ClientConsole clt){
		myClt = clt;
	}
	
	public void run() {
		
		Scanner sc = new Scanner(System.in);
		
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
		
	}
}
