package servPattern;

import java.net.Socket;

public class Utilisateur {
	
	private Socket clientSocket;
	private String nom;
	
	public Utilisateur(String nom, Socket clientSocket) {
		this.nom = nom;
		this.clientSocket = clientSocket;
	}
	
	public Socket getClientSocket() {
		return clientSocket;
	}
	
	public String getnom() {
		return nom;
	}
	
	public String toString() {
		return "Le clientsocket est : " + clientSocket + " et le nom d'utilisateur est " + nom;
		
	}
}