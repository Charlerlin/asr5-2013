package asr5.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author charlerlin
 *
 */

public class ServeurEcho {
	protected final static int SERVICE = 9876;
	private ServerSocket servSocket;
	private BufferedReader in;
	private PrintWriter out;
	private int nbClients; 

	public ServeurEcho() throws IOException{
		servSocket = new ServerSocket(SERVICE);
		nbClients = 0;
	}

	public Socket awaiting() throws IOException{
		return servSocket.accept();
	}

	public void doService(Socket client) throws IOException{
		nbClients++;
		System.out.println("Client connecté, client n°"+nbClients);
		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		out = new PrintWriter(client.getOutputStream(), true);
		String line = "";
		out.println("Bonjour <Nom du Sujet>\nVous êtes le client n°"+nbClients);
		while(true){
			line = in.readLine();
			if(line!=null){
				System.out.println("Recu : "+line);
				if(line.equals(".") || line.toLowerCase().equals("fin")){
					System.out.println("Fin de connexion client");
					out.println("Fin de connexion, au revoir");
					break;
				}
				out.println(line);
				System.out.println("Envoyé : "+line);
			}
			else{
				System.out.println("Fin de connexion client");
				out.println("Fin de connexion, au revoir");
				break;
			}
		}
		client.close();
	}
	
	public void starten() throws IOException{
		while(true){
			System.out.println("En attente de connexion client");
			Socket client = awaiting();
			doService(client);
		}
	}


	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		new ServeurEcho().starten();
	}

}
