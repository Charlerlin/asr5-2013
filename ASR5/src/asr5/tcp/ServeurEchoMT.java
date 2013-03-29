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

public class ServeurEchoMT{
	protected final static int SERVICE = 9876;
	private ServerSocket servSocket;
	private int nbClients;
	private int nbConnected;

	public ServeurEchoMT() throws IOException{
		servSocket = new ServerSocket(SERVICE);
		nbClients = 0;
		nbConnected = 0;
	}

	private void awaiting() throws IOException{
		Socket client;
		System.out.println("En attente de connexion client");
		try {
			new Thread(new ThreadAnswer(servSocket.accept())).start();
		} catch (Exception e) {
			System.err.println("Erreur à la connexion du client");
		}
	}

	public void starten() throws IOException{
		while(true){
			awaiting();
		}
	}

	//classe interne ThreadAnswer
	class ThreadAnswer extends Thread {
		private Socket client;
		private BufferedReader in;
		private PrintWriter out;
		private int thisCli;
		
		ThreadAnswer(Socket cli) {
			nbClients++;
			client = cli;
			thisCli = nbClients;
			nbConnected++;
		}
		
		public void run(){
			try {
				doService();
			} catch (IOException e) {
				System.err.println("Erreur à l'exécution du service");
			}
			try {
				client.close();
			} catch (IOException e) {
				System.err.println("Erreur à la fermeture de la connexion");
			}
		}
		
		void doService() throws IOException{
			System.out.println("Client connecté, client n°"+thisCli);
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream(), true);
			String line = "";
			out.println("Bonjour <Nom du Sujet>\nVous êtes le client n°"+thisCli+"\nIl y a "+nbConnected+" clients connectés");
			while(true){
				line = in.readLine();
				if(line!=null){
					System.out.println("Recu ("+thisCli+") : "+line);
					if(line.equals(".") || line.toLowerCase().equals("fin")){
						System.out.println("Fin de connexion client");
						out.println("Fin de connexion, au revoir");
						break;
					}
					out.println(line);
					System.out.println("Envoyé ("+thisCli+") : "+line);
				}
				else{
					System.out.println("Fin de connexion client "+thisCli);
					out.println("Fin de connexion, au revoir");
					break;
				}
			}
			client.close();
			--nbConnected;
		}
	}//fin de ThreadAnswer


	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		new ServeurEchoMT().starten();
	}

}
