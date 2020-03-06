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
	
	public void setUtilisateurInfos(String nomUtilisateur, String motDePasse) {
		// etabilsement des informations de l'utilisateur
		this.nomUtilesateur = nomUtilisateur;
		this.motDePasse = motDePasse;
	}
	
	public boolean getConnectionState() {
		// pour savoir externement l'etat du client
		return etatConnection;
	}
	
	public boolean connectionAuServeurBase() {
		//connection de l'application client au serveur
		return super.connecterAuServeur();
	}
	
	public void connectionAuServeur() {
		// connection du client sur son compte
		while( !etatConnection ) {
			this.envoiMessageConnection();
		}
	}
}
