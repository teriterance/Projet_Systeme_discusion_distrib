package unitTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import servPattern.Authentification;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class authentificationTests {

	String filename = "bdd.txt";
	Authentification authentification;
	
	@BeforeClass
	public static void beforeClass()
	{
		System.out.println("tests d'authentification:");
		System.out.println("-------------------------");
	}
	
	@Test
	public void testeBonIdBonMotDePasse()
	{
		System.out.print("Teste bon id, bon mot de passe: ");
		
		String ligne = null;
		BufferedReader lecteurAvecBuffer = null;
		try {
			lecteurAvecBuffer = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			ligne = lecteurAvecBuffer.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    	String id = ligne.split(":",2)[0];
    	String mdp = ligne.split(":",2)[1];
    	
    	authentification = new Authentification(id, mdp);
    	try {
			assertTrue(authentification.run());
			System.out.print("ok\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testeBonIdMauvaisMotDePasse()
	{
		System.out.print("Teste bon id, mauvais mot de passe: ");
		
		String ligne = null;
		BufferedReader lecteurAvecBuffer = null;
		try {
			lecteurAvecBuffer = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			ligne = lecteurAvecBuffer.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    	String id = ligne.split(":",2)[0];
    	String mdp = ligne.split(":",2)[1];
    	mdp = mdp.concat("§");
    	
    	authentification = new Authentification(id, mdp);
    	try {
			assertFalse(authentification.run());
    		System.out.print("ok\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    	
    @Test
    public void testeMauvaisIdBonMotDePasse()
    {
		System.out.print("Teste mauvais id, bon mot de passe: ");
    	
    	String ligne = null;
    	BufferedReader lecteurAvecBuffer = null;
    	try {
    		lecteurAvecBuffer = new BufferedReader(new FileReader(filename));
    	} catch (FileNotFoundException e1) {
    		e1.printStackTrace();
    	}
    	try {
    		ligne = lecteurAvecBuffer.readLine();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    		
        String id = ligne.split(":",2)[0];
        String mdp = ligne.split(":",2)[1];
        id = mdp.concat("§");
        	
        authentification = new Authentification(id, mdp);
        try {
    		assertFalse(authentification.run());
    		System.out.print("ok\n");
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    @Test
    public void testeMauvaisIdMauvaisMotDePasse()
    {
		System.out.print("Teste mauvais id, mauvais mot de passe: ");
    	
    	String ligne = null;
    	BufferedReader lecteurAvecBuffer = null;
    	try {
    		lecteurAvecBuffer = new BufferedReader(new FileReader(filename));
    	} catch (FileNotFoundException e1) {
    		e1.printStackTrace();
    	}
    	try {
    		ligne = lecteurAvecBuffer.readLine();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    		
        String id = ligne.split(":",2)[0];
        String mdp = ligne.split(":",2)[1];
        id = id.concat("§");
        mdp = mdp.concat("§");
        	
        authentification = new Authentification(id, mdp);
        try {
    		assertFalse(authentification.run());
    		System.out.print("ok\n");
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
}