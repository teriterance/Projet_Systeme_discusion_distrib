package client;

import java.util.ArrayList;

public class Client {
	private ClientTCP serveurClient;
	private String Nom;
	private String MotDePasse;
	private Recevoir threadRecevoir; 
	ArrayList<String> userList;
	
	public Client(String unNomServeur, int unNumero) {
		userList = new ArrayList<String>();
		serveurClient = new ClientTCP(unNomServeur, unNumero);
	}
	
	public void addUser(String nom) {
		// ajoute un utilisateur a la liste des utilisateur 
		userList.add(nom);
	}
	
	public boolean etatconnection() {
		return serveurClient.getConnectionState();
	}
	
	public void updateMessage(String source, String unMessage) {
		System.out.println(source + " " + unMessage);
		
	}
	
	public String getClientName() {
		return this.Nom;
	}
	
	public void setConnectionState(boolean b) {
		serveurClient.setConnectionState(b);
	}
	
	public void launch() {
		
		this.serveurClient.connectionAuServeurBase();
		
		threadRecevoir = new Recevoir(serveurClient.getSocIn(), this);
		threadRecevoir.start();
		
		while( !serveurClient.getConnectionState() ) {
			serveurClient.setUtilisateurInfos("toto", "1234");
			serveurClient.connectionAuServeur();
		}
		
		boolean end = false;
		
		while ( !end ) {
			serveurClient.envoiMessage("toto", "bonjour");
		}
	}

}
