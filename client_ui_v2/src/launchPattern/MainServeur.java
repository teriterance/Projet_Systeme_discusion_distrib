package launchPattern;

import servPattern.ServeurTCP;
import servPattern.Contexte;

public class MainServeur {

	public static void main(String[] args) {
		
		ServeurTCP myServ = new ServeurTCP(new Contexte() , new Protocole1() , 6666 );

		myServ.start();
	}
}