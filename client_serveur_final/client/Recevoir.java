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
		
		while( true) {
			System.out.println("bonjour");
			try {
				String msg = socIn.readLine();
				String[] mesage  = msg.split("*");
				if (mesage[0] == client.getClientName()){
					//le mesage est pour nous
					
					if ( isUser(mesage[1]) ) {
						//c'est un message d'un autre utilisateur 
						client.updateMessage(mesage[1], mesage[2]);
					
					}else {
						if (mesage[0] == "userList") {
							//ajout de client a la liste des clients
							for (int i= 1; i < mesage.length; i++) {
								client.addUser(mesage[i]);
							}
						}else {
							if(mesage[0] == "ConnectWin") {
								client.setConnectionState(true);
							}
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public boolean isUser(String name) {
		
		return true;
	}

}
