package servPattern;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class Authentification {
	
	String id;
	String mdp;
	
	public Authentification()
	{
		this.id = null;
		this.mdp = null;
	}
	
	public Authentification(String id, String mdp) 
	{
		this.id = id;
		this.mdp = mdp;
	}
	
	public String run() throws IOException
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
	    	
	    	if((chaines[1].equals(this.id)) && (chaines[2].equals(this.mdp))) {
	    	    lecteurAvecBuffer.close();
	    	    
	    	    if(chaines[0].equals("admin"))
	    	    {
	    	    	return "admin";
	    	    }
	    	    else
	    	    {
	    	    	return "normal";
	    	    }
	    	}
	    }
	    lecteurAvecBuffer.close();
	    return "false";
	  }
	
	public void newUser(String type, String nom, String mdp )
	{
		try {
			 PrintWriter out =new PrintWriter(new BufferedWriter(new FileWriter("bdd.txt", true)));
			 out.append("\n" + type + ":" + nom + ":" + mdp);
			 out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}