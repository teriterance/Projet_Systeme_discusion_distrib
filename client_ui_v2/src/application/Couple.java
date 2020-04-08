package application;

public class Couple {
	
	   private String pseudo;
	   private String message;
	   
	 
	    public Couple(String pseudo, String message) { 
	        this.pseudo = pseudo; 
	        this.message  = message;
	    } 
	 
	    public String getPSEUDO() { 
	        return pseudo; 
	    } 
	 
	    public void setPSEUDO(String pseudo) { 
	        this.pseudo = pseudo; 
	    } 

	    
	    public String getMESSAGE() { 
	        return message; 
	    } 
	 
	    public void setMESSAGE(String message) { 
	        this.message = message; 
	    } 

}
