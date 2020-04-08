package application;

import client.Client;
import client.Recevoir;
import client.RecevoirGUI;

import java.awt.Desktop.Action;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SampleController{
	public Client client;
	@FXML private TextField id;
	@FXML private TextField mdp;
	@FXML private Button connecter;
	@FXML private TextField destinataire;
	@FXML private TextField message;
	@FXML private Button envoyer;

	@FXML private Text desti;
	@FXML private Text msg;
	@FXML private Text issou;
	@FXML private Text authen;
	@FXML private Text id_text;
	@FXML private Text mdp_text;
	@FXML private Text texte;
	@FXML private Text enligne;
	
	@FXML private AnchorPane login;
	@FXML private AnchorPane normalUser;
	@FXML private AnchorPane adminUser;	
	@FXML private ChoiceBox<String> newUserType;
	@FXML private TextField newUserID;
	@FXML private TextField newUserMdp;

	private String nom_utilisateur; 
	public SampleController() {
		client = new Client("localhost", 6666 );
		
		System.out.println("Bonjour Bienvenu sur la version console de notre client \ntentative de connection au serveur");
		client.connectionAuServeurBase();
		System.out.println("connection reussie");
		
		//initialisation du thread de reception 
		RecevoirGUI threadRecevoirGUI = new RecevoirGUI(client.getSocIn(), client,this);
		 //lancement du thread 
		Platform.runLater( () -> threadRecevoirGUI.start() );
	}
	
	public void changeScreenButtonPushed(ActionEvent event) throws IOException {
		
		Parent ChatView = FXMLLoader.load(getClass().getResource("Chat.fxml"));
		Scene ChatViewScene = new Scene(ChatView);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(ChatViewScene);
		window.show();
	}
	
	@FXML
	private void getCoord(ActionEvent event) {
	               //Retourne les identifiants sous la forme (id/mdp)
		nom_utilisateur = id.getText();
		client.setUtilisateurInfos(id.getText(),mdp.getText());
		client.connectionAuServeur();
		if(client.getConnectionState()) {		
			
			this.login.setVisible(false);
			
			if(client.isAdmin())
			{
				this.adminUser.setVisible(true);
			}
			else
			{
				this.normalUser.setVisible(true);
			}

			Platform.runLater(new Runnable() {
			      @Override public void run() {
			    	  enligne.setText("Welcome "+nom_utilisateur );
			      }
			});
			
			
		}
		
	}

	@FXML
	private void send(ActionEvent event) {
	               //Envoie le message sous la forme (destinataire/message)

		client.envoiMessage(nom_utilisateur,message.getText(), destinataire.getText());
		String msg_envoi = "A "+ destinataire.getText()+ ": " + message.getText();
		Platform.runLater(new Runnable() {
		      @Override public void run() {
		    	  texte.setText(texte.getText()+"\n"+msg_envoi);
		      }
		});

	}
	
	@FXML
	private void newUser(){
		String userType = this.newUserType.getSelectionModel().getSelectedItem();
		String ID = this.newUserID.getText();
		String MDP = this.newUserMdp.getText();
		
		String msg = "newUser:" + userType + ":" + ID + ":" + MDP;
		
		client.send(msg);
	}
	

	public void messagerecu(String msg_recu) {
		Platform.runLater(new Runnable() {
		      @Override public void run() {
		    	  texte.setText(texte.getText()+"\n"+msg_recu);
		      }
		});
		
	}

}
