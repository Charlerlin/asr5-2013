package asr5.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 
 * @author Jean Carle
 *
 */

public class ClientEchoCarle {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String ligne, adrServ =  "localhost" ; //args[0]; 
		int portServ = ServeurEchoCarle.SERVICE; //Integer.parseInt(args[1]);
		BufferedReader in, userInput;
		PrintWriter out;
		Socket maSocket;

		// connexion avec le clavier
		userInput = new BufferedReader ( new InputStreamReader( System.in ) ); 
		try {
			// Ouverture socket, demande de connexion 
			maSocket = new Socket (adrServ, portServ); // connexion au canal de réception 
			in=new BufferedReader(new InputStreamReader(maSocket.getInputStream())); // connexion au canal d’écriture
			out = new PrintWriter(maSocket.getOutputStream());

			while(true){
				//	saisie clavier
				ligne = userInput.readLine();
				//	finie ? oui alors sortie
				if(ligne.equals("."))
					break;
				//	Non, => envoi (ecrtiture des données au serveur (out)
				out.println(ligne);

				//	lecture canal réception (in) et affichage à l'écran
				System.out.println(in.readLine());  //bloquant si canal vide 
			}
		}
		catch(UnknownHostException e){
			System.err.println(e);
		}
		catch(IOException e){
			System.err.println(e);
		}

	}

}
