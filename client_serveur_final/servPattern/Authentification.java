package servPattern;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Authentification {
	
	String id;
	String mdp;
	boolean end;
	
	public Authentification(String id, String mdp) 
	{
		this.id = id;
		this.mdp = mdp;
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
	    
	    while ((ligne = lecteurAvecBuffer.readLine()) != null) 
	    {
	    	String chaines[] = ligne.split(":");
	    	
	    	if((chaines[0].equals(this.id)) && (chaines[1].equals(this.mdp))) {
	    	    lecteurAvecBuffer.close();
	    		return true;
	    	}
	    }
	    lecteurAvecBuffer.close();
	    return false;
	  }
}