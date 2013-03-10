package asr5.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author Jean Carle
 *
 */


public class ServeurEchoCarle {

	protected static final int SERVICE = 9876;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int portMonService = SERVICE ;//Integer.parseInt(args[0]);
		ServerSocket socketEcoute; try {
			// Creation connexion passive
			socketEcoute = new ServerSocket(portMonService); 
			// Attendre et réalisez le service pour chaque connexion // accès séquentiel (un client servit à la fois)
			while(true){
				doService(socketEcoute.accept());	// bloquant
			}
		} catch(IOException e) {System.err.println(e);}

	}

	static void doService(Socket clientSocket) throws IOException { 
		BufferedReader in; 
		PrintWriter out;

		// Connexion au flux en lecture 
		in =new BufferedReader(new InputStreamReader(clientSocket.getInputStream()) ); // Connexion au flux en écriture
		out = new PrintWriter(clientSocket.getOutputStream());
		// Ici, on renvoie ce que l’on reçoit
		// ATTENTION : readLine est bloquant
		while(true){
//			String texte = in.readLine();
//			out.println(texte);
			System.out.println(in.readLine());
		}
	}
}

