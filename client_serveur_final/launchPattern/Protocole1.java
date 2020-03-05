package launchPattern;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.LinkedList;

import servPattern.Authentification;
import servPattern.IContext;
import servPattern.IProtocole;
import servPattern.Utilisateur;

public class Protocole1 implements IProtocole {
	boolean etatconnexion;
	public LinkedList<Utilisateur> UsersList;
	public Protocole1() {
		etatconnexion = false; //initialisation à false 
		UsersList = new LinkedList<Utilisateur>(); 
	}
	public void execute( IContext c , InputStream unInput , OutputStream unOutput,Socket clientSocket ) {
		
		String inputReq;
		BufferedReader is = new BufferedReader(new InputStreamReader(unInput));
		PrintStream os = new PrintStream(unOutput);
		try {
			
			while(!etatconnexion) {
				String valeurExpediee = "";

				if ((inputReq = is.readLine()) != null) {

					System.out.println(" Identifiants Recus " + inputReq);
					String chaines[] = inputReq.split(":");
					System.out.println(chaines[0] + " et " + chaines[1]);
					Authentification authen = new Authentification(chaines[0], chaines[1]);
					if (authen.run()) {
						valeurExpediee = "OK";
						System.out.println(" Reponse serveur "	+ valeurExpediee);
						etatconnexion = true; 
						UsersList.add(new Utilisateur(clientSocket, chaines[0]));
						for(Utilisateur user : UsersList)
							System.out.print(user);
					}
					else {
						valeurExpediee = "NOP";
						System.out.println(" Reponse serveur "	+ valeurExpediee);
					}
					os.println(valeurExpediee);
					         // Le client est bien connecté
				}
			}
			if(etatconnexion) execute_connexion_OK(c, unInput, unOutput, clientSocket);
			
			
		} catch ( Exception e) {
			System.out.println(" Pb d'exception " + e);
			e.printStackTrace();
		}			
	}
	
	public void execute_connexion_OK(IContext c, InputStream unInput , OutputStream unOutput, Socket clientSocket) {
		String inputReq;
		BufferedReader is = new BufferedReader(new InputStreamReader(unInput));
		PrintStream os = new PrintStream(unOutput);

		try {
			while(etatconnexion) {
				String valeurExpediee = "";
				if ((inputReq = is.readLine()) != null) {
				
				System.out.println(" Message Recu " + inputReq);
				String chaines[] = inputReq.split(":",2);
				if (chaines[0].contentEquals("stop")) {
					valeurExpediee = "OK";
					System.out.println(" Reponse serveur "	+ valeurExpediee);
					etatconnexion = false;
				}
				else {  //on transmet le message du client vers le destinataire en chaines[0]
					
				//	if(ledistinataireexisteetquilestconnecte) {
				//		toledestinataire.envoieMSG;
				//	}
				//	if()
					valeurExpediee = "";
					System.out.println(" Reponse serveur "	+ valeurExpediee);
				}
				os.println(valeurExpediee);
				}
			}
			if(!etatconnexion)execute( c ,unInput ,  unOutput, clientSocket );
			
		} catch ( Exception e) {
			System.out.println(" Pb d'exception ");
		}

		
		
		
		
	}
}