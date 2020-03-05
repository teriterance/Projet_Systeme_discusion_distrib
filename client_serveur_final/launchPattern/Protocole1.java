package launchPattern;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

import servPattern.Authentification;
import servPattern.IContext;
import servPattern.IProtocole;


public class Protocole1 implements IProtocole {
	boolean etatconnexion;
	
	public Protocole1() {
		etatconnexion = false; //initialisation à false 
	}
	public void execute( IContext c , InputStream unInput , OutputStream unOutput ) {
		
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
					}
					else {
						valeurExpediee = "NOP";
						System.out.println(" Reponse serveur "	+ valeurExpediee);
					}
					os.println(valeurExpediee);
					etatconnexion = true;          // Le client est bien connecté
				}
			}
			if(etatconnexion) execute_connexion_OK(c, unInput, unOutput);
			
			
		} catch ( Exception e) {
			System.out.println(" Pb d'exception ");
		}			
	}
	
	public void execute_connexion_OK(IContext c, InputStream unInput , OutputStream unOutput) {
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
			if(!etatconnexion)execute( c ,unInput ,  unOutput );
			
		} catch ( Exception e) {
			System.out.println(" Pb d'exception ");
		}

		
		
		
		
	}
}