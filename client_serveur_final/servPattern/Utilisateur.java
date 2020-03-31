package servPattern;

import java.net.Socket;

public class Utilisateur {
	
	private Socket clientSocket;
	private String nom;
	
	public Utilisateur(Socket clientSocket, String nom) {
		this.clientSocket = clientSocket;
		this.nom = nom;
	}
	
	public Socket getclientSocket() {
		return clientSocket;
	}
	
	public String getnom() {
		return nom;
	}
	
	public String toString() {
		return "Le clientsocket est : " + clientSocket + " et le nom d'utilisateur est " + nom;
		
	}
}