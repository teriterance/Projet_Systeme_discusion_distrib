package launchPattern;

import java.util.Scanner;

import client.Client;
import client.ClientTCP;

public class MainClient {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		Client myClt = new Client("localhost", 6666 );
		
		myClt.launch();
	}

}
