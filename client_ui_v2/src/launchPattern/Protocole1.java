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
import servPattern.Contexte;
import servPattern.IProtocole;
import servPattern.Utilisateur;

public class Protocole1 implements IProtocole {
	
	private Contexte contexte;
		
	public Protocole1() {
	}
	
	//Protocole do login
	public void execute( IContext c , InputStream unInput , OutputStream unOutput,Socket clientSocket ) {
		
		this.contexte = (Contexte) c;
		
		boolean etatLogin = false;
		
		String inputReq;
		BufferedReader is = new BufferedReader(new InputStreamReader(unInput));
		PrintStream os = new PrintStream(unOutput);
		
		try {
			//Boucles jusqu'à ce que le login soit réussie
			while(!etatLogin) {
								
				String valeurExpediee = "";
				
				//Lecture du message du client.
				if ((inputReq = is.readLine()) != null) {

					System.out.println("Identifiants Recus " + inputReq);
					String chaines[] = inputReq.split(":");
					System.out.println(chaines[0] + " et " + chaines[1]);
					Authentification authen = new Authentification(chaines[0], chaines[1]);
					
					//Login réussie
					if (authen.run()) 
					{
						valeurExpediee = "login:OK";
						System.out.println("Reponse serveur: "	+ valeurExpediee);
						etatLogin = true;
						this.contexte.addNewUser(chaines[0], clientSocket);
					}
					//Login échouée
					else 
					{
						valeurExpediee = "login:NO";
						System.out.println("Reponse serveur: "	+ valeurExpediee);
					}
					
					//Indique au client si le login a réussi ou pas
					os.println(valeurExpediee);
				}
			}
			
			//Méthode de communication entre le serveur et le client après un login réussie
			execute_connexion_OK(c, unInput, unOutput, clientSocket);
			
		} catch ( Exception e) {
			System.out.println("Pb d'exception: " + e);
			e.printStackTrace();
		}			
	}
	
	public void execute_connexion_OK(IContext c, InputStream unInput , OutputStream unOutput, Socket clientSocket) 
	{
		boolean etatLogin = true;
		
		String inputReq;
		BufferedReader is = new BufferedReader(new InputStreamReader(unInput));
		PrintStream os = new PrintStream(unOutput);

		try {
			while(etatLogin) {
				String valeurExpediee = "";
				
				if ((inputReq = is.readLine()) != null) 
				{
					
					System.out.println("Reçu du client: " + inputReq);
					
					String[] inputReqs  = inputReq.split(":");
					
					switch(inputReqs[0])
					{
						case "message":
							//A faire:  Envoyer le message au bon destinateur
							String emetteur = inputReqs[1];
							String destinateur = inputReqs[2];
							String message = inputReqs[3];
							
							Socket destinateurSocket = this.contexte.getUserSocket(destinateur);
							
							if(destinateurSocket == null)
							{
								System.out.println("destinateurSocket is null");
							}
							
							PrintStream destinateurStream = new PrintStream(destinateurSocket.getOutputStream());
							destinateurStream.println("message:" + emetteur + ":" + message);
		
							System.out.println(emetteur + "->" + destinateur + ":"+ message  );
							
							break;
						case "userslist":
							
							String onlineUsers = this.contexte.getOnlineUsers();
							valeurExpediee = "userslist:" + onlineUsers;
							os.println(valeurExpediee);
							break;
					}
				}
			}
			
			if(!etatLogin)execute( c ,unInput ,  unOutput, clientSocket );
			
		} catch ( Exception e) {
			System.out.println("Pb d'exception");
			e.printStackTrace();
		}	
	}
}