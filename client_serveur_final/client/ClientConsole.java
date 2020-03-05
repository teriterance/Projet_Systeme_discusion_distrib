package client;

public class ClientConsole extends ClientTCP {
	String nomUtilesateur;
	String motDePasse;
	
	//etat de connection 
	boolean etatConnection = false;
	
	///constante de connection reussie 
	final String Reussi = "Ok";
	
	public ClientConsole (String unNomServeur, int unNumero) {
		super( unNomServeur,  unNumero);
	}
	
	void envoiMessageConnection() {
		// essaie de connection au serveur 
		String recMessage = null;
		recMessage = super.transmettreChaine(nomUtilesateur + " " +motDePasse);
		if (recMessage == Reussi ) {
			etatConnection = true;
		}
	}
	
	boolean envoiMessage(String destinataire, String message) {
		// Structure d'envois de message au serveur a destination d'un client 
		String recMessage = null;
		recMessage = super.transmettreChaine(destinataire + " " + message);
		return (recMessage == Reussi);
	}
	
	String demandListeUtilisateur() {
		// Structure de demande de liste des utilisateurs connectees 
		String recMessage = null;
		recMessage = super.transmettreChaine("UserList");
		return recMessage;
	}
	
	void setUtilisateurInfos(String nomUtilisateur, String motDePasse) {
		// etabilsement des informations de l'utilisateur
		this.nomUtilesateur = nomUtilisateur;
		this.motDePasse = motDePasse;
	}
	
	boolean getConnectionState() {
		// pour savoir externement l'etat du client
		return etatConnection;
	}
	
	void connectionAuServeurBase() {
		//connection de l'application client au serveur
		super.connecterAuServeur();
	}
	
	void connectionAuServeur() {
		// connection du client sur son compte
		while( !etatConnection ) {
			this.envoiMessageConnection();
		}
	}
	
	void waitServerMessage() {
		String message = null;
		
		while ( (message = socIn.getInputStream().read() ) != 1 ) {
			
		}
	}
}
