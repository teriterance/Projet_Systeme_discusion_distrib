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
	
	boolean etatLogin;
	public LinkedList<Utilisateur> UsersList;
	
	public Protocole1() {
		etatLogin = false;
		UsersList = new LinkedList<Utilisateur>(); 
	}
	
	//Protocole do login
	public void execute( IContext c , InputStream unInput , OutputStream unOutput,Socket clientSocket ) {
		
		String inputReq;
		BufferedReader is = new BufferedReader(new InputStreamReader(unInput));
		PrintStream os = new PrintStream(unOutput);
		
		try {
			//Boucles jusqu'à ce que le login soit réussie
			while(!etatLogin) {
				
				System.out.println("----dans le boucle");
				
				String valeurExpediee = "";
				
				//Lecture du message du client.
				if ((inputReq = is.readLine()) != null) {

					System.out.println(" Identifiants Recus " + inputReq);
					String chaines[] = inputReq.split(":");
					System.out.println(chaines[0] + " et " + chaines[1]);
					Authentification authen = new Authentification(chaines[0], chaines[1]);
					
					//Login réussie
					if (authen.run()) 
					{
						valeurExpediee = "OK";
						System.out.println("Reponse serveur: "	+ valeurExpediee);
						etatLogin = true; 
						UsersList.add(new Utilisateur(clientSocket, chaines[0]));
						/*
						for(Utilisateur user : UsersList)
							System.out.print(user);*/
					}
					//Login échouée
					else 
					{
						valeurExpediee = "NOP";
						System.out.println("Reponse serveur: "	+ valeurExpediee);
					}
					
					System.out.println("before send");
					//Indique au client si le login a réussi ou pas
					os.println(valeurExpediee);
					System.out.println("After send");

				}
			}
			
			//Méthode de communication entre le serveur et le client après un login réussie
			execute_connexion_OK(c, unInput, unOutput, clientSocket);
			
		} catch ( Exception e) {
			System.out.println(" Pb d'exception: " + e);
			e.printStackTrace();
		}			
	}
	
	public void execute_connexion_OK(IContext c, InputStream unInput , OutputStream unOutput, Socket clientSocket) 
	{
		System.out.println("execute_connexion_OK");
		
		String inputReq;
		BufferedReader is = new BufferedReader(new InputStreamReader(unInput));
		PrintStream os = new PrintStream(unOutput);

		try {
			while(etatLogin) {
				String valeurExpediee = "";
				if ((inputReq = is.readLine()) != null) 
				{
					System.out.println("Message Recu: " + inputReq);
					String chaines[] = inputReq.split(":",2);
					
					//A faire, protocole de communication
					if (chaines[0].contentEquals("stop")) 
					{
						valeurExpediee = "OK";
						System.out.println("Reponse serveur: "	+ valeurExpediee);
						etatLogin = false;
					}
					else 
					{   //on transmet le message du client vers le destinataire en chaines[0]
						//if(ledistinataireexisteetquilestconnecte) {
						//toledestinataire.envoieMSG;
						//}
						valeurExpediee = inputReq;
						System.out.println(" Reponse serveur: "	+ valeurExpediee);
					}
					os.println(valeurExpediee);
				}
			}
			
			if(!etatLogin)execute( c ,unInput ,  unOutput, clientSocket );
			
		} catch ( Exception e) {
			System.out.println("Pb d'exception");
		}	
	}
}