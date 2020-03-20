package client;

import java.util.ArrayList;
import java.util.Scanner;

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
	
	public boolean serveurConnected() {
		return this.serveurClient.serveurConnected();
	}
	
	public void setConnectionState(boolean b) {
		serveurClient.setConnectionState(b);
	}
	
	public void launch() {
		
		Scanner reader = new Scanner(System.in);
		
		System.out.println("Bonjour Bienvenu sur la version console de notre client \ntentative de connection au serveur");
		this.serveurClient.connectionAuServeurBase();
		System.out.println("connection reussie");
		
		//initialisation du thread de reception 
		threadRecevoir = new Recevoir(serveurClient.getSocIn(), this);
		threadRecevoir.start(); //lancement du thread 
		
		while( !serveurClient.getConnectionState() ) {
			System.out.println("veuillez vous authentifier");
			String nom = reader.nextLine();
			String motdepasse = reader.nextLine();
			
			//authetification
			serveurClient.setUtilisateurInfos(nom, motdepasse);
			serveurClient.connectionAuServeur();
		}
		
		while (this.serveurConnected()) {
			String cible = reader.nextLine();
			String message = reader.nextLine();
			
			serveurClient.envoiMessage(cible, message);
		}
	}

}
