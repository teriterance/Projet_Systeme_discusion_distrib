package servPattern;

import java.net.Socket;
import java.util.LinkedList;

public class Contexte implements IContext {

	private LinkedList<Utilisateur> usersList;
	
	public Contexte() {
		this.usersList = new LinkedList<Utilisateur>();
	}
	
	public void addNewUser(String nom, Socket clientSocket)
	{
		Utilisateur utilisater = new Utilisateur(nom, clientSocket);
		usersList.add(utilisater);
		
	}
	
	public String getOnlineUsers()
	{
		StringBuilder onlineUsers = new StringBuilder("");
		
		for(Utilisateur user: usersList)
		{
			onlineUsers.append(user.getnom() + ":");
		}
		
		onlineUsers.deleteCharAt(onlineUsers.length() - 1);
		
		return onlineUsers.toString();
	}
	
	public Socket getUserSocket(String nom)
	{
		for(Utilisateur user: usersList)
		{
			if(user.getnom().equals(nom))
			{
				return user.getClientSocket();
			}
		}
		
		//Si l'utilisateur n'est pas en ligne ou n'existe pas
		return null;
	}

}