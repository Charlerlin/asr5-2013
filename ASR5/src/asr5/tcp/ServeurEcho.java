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
	
	public ServeurEcho() throws IOException{
		servSocket = new ServerSocket(SERVICE);
	}
	
	public Socket start() throws IOException{
		return servSocket.accept();
	}
	
	public void doService(Socket client) throws IOException{
		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		out = new PrintWriter(client.getOutputStream(), true);
		String line = "";
		out.println("Bonjour <Nom du Sujet>");
		while(true){
			line = in.readLine();
			System.out.println("Recu : "+line);
			if(line.equals(".") || line.toLowerCase().equals("fin")){
				System.out.println("Fin de connexion client");
				out.println("Fin de connexion, au revoir");
				break;
			}
			out.println(line);
			System.out.println("Envoyé : "+line);
		}
	}
	

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		ServeurEcho se = new ServeurEcho();
		Socket client = se.start();
		System.out.println("Client connecté");
		se.doService(client);
	}

}
