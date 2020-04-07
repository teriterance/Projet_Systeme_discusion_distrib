package client;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import application.Main;

public class Client {
	private ClientTCP serveurClient;
	private String Nom;
	private String MotDePasse;
	private Recevoir threadRecevoir;
	private Main ClientGUI; 
	private String[] args;
	public int compteur;
	ArrayList<String> userList;
	
	public Client(String unNomServeur, int unNumero) {
		userList = new ArrayList<String>();
		serveurClient = new ClientTCP(unNomServeur, unNumero);
		compteur = 0;

	}
	
	public void addUser(String nom) {
		// ajoute un utilisateur a la liste des utilisateur 
		System.out.println("un nouvel utilisateur se connecte " + nom);
		userList.add(nom); 
	}
	
	public boolean etatconnection() {
		return serveurClient.getConnectionState();
	}
	
	public String updateMessage(String source, String unMessage) {
		System.out.println(source + " " + unMessage);
		compteur++;
		return source + " " + unMessage;
	}
	
	public String getClientName() {
		return this.Nom;
	}
	
	public void envoiMessage(String emetteur, String message, String cible) {
		serveurClient.envoiMessage("message:" + emetteur + ":" + cible, message);
	}
	
	public boolean serveurConnected() {
		return this.serveurClient.serveurConnected();
	}
	
	public void setConnectionState(boolean b) {
		serveurClient.setConnectionState(b);
	}
	
	
	public void setUtilisateurInfos(String id, String mdp) {
		
		serveurClient.setUtilisateurInfos(id, mdp);
		
	}
	
	public void connectionAuServeur(){
		
		serveurClient.connectionAuServeur();
	}
	
	public void connectionAuServeurBase() {
		
		serveurClient.connectionAuServeurBase();
		
	}
	public boolean getConnectionState() {
		
		return serveurClient.getConnectionState();
		
	}
	
	public BufferedReader getSocIn() {
		
		return serveurClient.getSocIn();
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
			
			//why is there no this here?
			String motdepasse = reader.nextLine();
			
			//authetification
			serveurClient.setUtilisateurInfos(this.Nom, motdepasse);
			serveurClient.connectionAuServeur();
			
			//This should be removed if possible
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
			serveurClient.envoiMessage("message:" + this.Nom + ":" + cible, message);
			//serveurClient.envoiMessage("userslist", "");
		}
		
		reader.close();
	}

}