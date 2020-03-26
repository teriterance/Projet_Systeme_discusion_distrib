package client;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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
		System.out.println("un nouvel utilisateur se connecte " + nom);
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
			this.Nom = reader.nextLine();
			String motdepasse = reader.nextLine();
			
			//authetification
			serveurClient.setUtilisateurInfos(this.Nom, motdepasse);
			serveurClient.connectionAuServeur();
			
			System.out.println("entrer pour valider");
			reader.nextLine();
		}
		
		while ( this.serveurConnected() ) {
			System.out.println("envoyer un message");
			
			System.out.println("entrer la cible");
			String cible = reader.nextLine();
			System.out.println("entrer le message");
			String message = reader.nextLine();
			
			//envoi du message
			serveurClient.envoiMessage(cible, message);
		}
		
		reader.close();
	}

}
