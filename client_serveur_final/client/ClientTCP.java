package client;

import java.io.*;
import java.net.*;

public class ClientTCP {

	private int numeroPort;

	private String nomServeur;

	private Socket socketServeur;

	private PrintStream socOut;

	private BufferedReader socIn;	

	private String nomUtilesateur;
	
	private String motDePasse;
	 
	boolean etatConnection;
	
	/** Un client se connecte a un serveur identifie par un nom (unNomServeur), sur un port unNumero */
	public  ClientTCP(String unNomServeur, int unNumero) {        
		numeroPort = unNumero;
		nomServeur = unNomServeur;
		etatConnection = false;
	} 

	public boolean connecterAuServeur() {        
		boolean ok = false;
		try {
			System.out.println("Tentative : " + nomServeur + " -- " + numeroPort);
			socketServeur = new Socket( nomServeur, numeroPort);
			socOut = new PrintStream(socketServeur.getOutputStream());
			socIn = new BufferedReader ( 
					new InputStreamReader (socketServeur.getInputStream()));
			ok = true;
		} catch (UnknownHostException e) {
			System.err.println("Serveur inconnu : " + e);

		} catch (ConnectException e) {
			System.err.println("Exception lors de la connexion:" + e);
			e.printStackTrace();

		} catch (IOException e) {
			System.err.println("Exception lors de l'echange de donnees:" + e);
		}
		System.out.println("Connexion faite");
		return ok;
	} 	
	
	public void deconnecterDuServeur() {        
		try {
			System.out.println("[ClientTCP] CLIENT : " + socketServeur);
			socOut.close();
			socIn.close();
			socketServeur.close();
		} catch (Exception e) {
			System.err.println("Exception lors de la deconnexion :  " + e);
		}
	} 	
	
	public BufferedReader getSocIn() {
		return this.socIn;
	}
	
	public boolean transmettreChaine(String uneChaine) {        
		socOut.println( uneChaine );
		socOut.flush();
		return true;
	} 
	

	/* A utiliser pour ne pas deleguer la connexion aux interfaces GUI */
	public String transmettreChaineConnexionPonctuelle(String uneChaine) {
		String msgServeur = null;
		String chaineRetour = "";
		if (connecterAuServeur() == true) {
			try {
				socOut.println(uneChaine);
				socOut.flush();
				msgServeur = socIn.readLine();
				while( msgServeur != null && msgServeur.length() >0) {
					chaineRetour += msgServeur + "\n";
					msgServeur = socIn.readLine();
				}
				deconnecterDuServeur();
			} catch (Exception e) {
				System.err.println("Exception lors de la connexion client:  " + e);
			}
		}
		else
		{	
			System.err.println("Connexion echouee");
		}
		return chaineRetour;
	}
	
	public void envoiMessageConnection() {
		// essaie de connection au serveur 
		this.transmettreChaine(nomUtilesateur + ":" +motDePasse);
	}
	
	public boolean envoiMessage(String destinataire, String message) {
		// Structure d'envois de message au serveur a destination d'un client 
		return this.transmettreChaine(destinataire + " " + message);
	}
	
	public boolean demandListeUtilisateur() {
		// Structure de demande de liste des utilisateurs connectees 
		return this.transmettreChaine("UserList");
	}
	
	public void setUtilisateurInfos(String nomUtilisateur, String motDePasse) {
		// etabilsement des informations de l'utilisateur
		this.nomUtilesateur = nomUtilisateur;
		this.motDePasse = motDePasse;
	}
	
	public boolean getConnectionState() {
		// pour savoir externement l'etat du client
		return etatConnection;
	}
	
	public void setConnectionState(boolean state) {
		this.etatConnection = state;
	}
	
	public boolean connectionAuServeurBase() {
		//connection de l'application client au serveur
		return this.connecterAuServeur();
	}
	
	public void connectionAuServeur() {
		// connection du client sur son compte
		while( !etatConnection ) {
			this.envoiMessageConnection();
		}
	}
}
