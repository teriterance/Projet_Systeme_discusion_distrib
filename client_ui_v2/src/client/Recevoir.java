package client;

import java.io.BufferedReader;
import java.io.IOException;

public class Recevoir extends Thread {
	private BufferedReader socIn;
	private Client client;
	
	public Recevoir(BufferedReader unsocIn, Client unClient) {
		/*initialisation de la fonction de reception de message 
		 * entre socIn: le socket sur le quel sera fait la lecture 
		 * sortie : rien, void 
		 * traitement majeure: affectation */
		this.socIn = unsocIn;
		this.client = unClient;
	}
	
	@Override
	public void run() {
		
		String msg;
		
		while( this.client.serveurConnected()) 
		{
			try 
			{
				//il y a quelque chose à lire
				if( (msg = socIn.readLine()) != null)
				{
					System.out.println("Reçu du serveur: " + msg);
					String[] message  = msg.split(":");
					
					switch(message[0])
					{
						case "login":
							if (message[1].equals("OK"))
							{
								client.setConnectionState(true);
							}
							else
							{
								System.out.println("Nom d'utilisateur ou mot de passe erroné");
							}
							break;
							
						case "userslist":
							
							for (int i= 1; i < message.length; i++) 
							{
								client.addUser(message[i]);
							}
							
							break;
							
						case "message":
							
							client.updateMessage(message[1], message[2]);
							
							break;
					}
				}

			}
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}