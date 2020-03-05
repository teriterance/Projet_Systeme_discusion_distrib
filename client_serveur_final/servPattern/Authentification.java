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
	boolean end;
	public Authentification(String id, String mdp) {
		this.id = id;
		this.mdp = mdp;
		lecteurAvecBuffer = null;
		this.end = false;
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
	    while ((ligne = lecteurAvecBuffer.readLine()) != null)/* || (!end) */ {
	    	String chaines[] = ligne.split(":");
	    	if((chaines[0].equals(this.id)) && (chaines[1].equals(this.mdp))) {
	    	    lecteurAvecBuffer.close();
	    	    System.out.println("la");
	    	//	this.end = true;
	    		return true;
	
	    	}
	    }
	    lecteurAvecBuffer.close();
	    return false;
	  }

}
