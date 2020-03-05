package servPattern;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Authentification {
	
	FileReader file;
	BufferedReader lecteurAvecBuffer;
	String ligne;
	String id;
	String mdp;
	
	public Authentification(String id, String mdp) {
		this.id = id;
		this.mdp = mdp;
		lecteurAvecBuffer = null;
		
	}
	public boolean run() throws IOException
	  {
	    BufferedReader lecteurAvecBuffer = null;
	    String ligne;
	    
	    try
	      {
		lecteurAvecBuffer = new BufferedReader(new FileReader("bdd.txt"));
	      }
	    catch(FileNotFoundException exc)
	      {
		System.out.println("Erreur d'ouverture");
	      }
	    while ((ligne = lecteurAvecBuffer.readLine()) != null) {
	    	String chaines[] = ligne.split(":",2);
	    	if(chaines[0] == this.id && chaines[1] == this.mdp) {
	    		return true;
	    	}
	    }
	    lecteurAvecBuffer.close();
	    return false;
	  }

}
