package asr5.ctp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * 
 * @author charlerlin
 * 
 */

public class ServeurCesar {
	// numéros de ports
	private static final int noUDP = 12345;

	// attributs généraux
	private int decalage;
	private int noTCP;

	// attributs TCP
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	// attributs UDP
	protected DatagramPacket uPacket;
	protected DatagramSocket uSocket;
	protected byte[] uBuf;

	// constructeur
	public ServeurCesar() {
		System.out.println("ServeurCesar, démarrage");
		creerServUDP();
	}

	// méthodes UDP
	public void creerServUDP() {
		uBuf = new byte[30];
		uPacket = new DatagramPacket(uBuf, uBuf.length);
		try {
			uSocket = new DatagramSocket(noUDP);
		} catch (SocketException e) {
			System.err.println("Fail creating UDP socket");
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("Serveur UDP prêt");
	}

	public void recevoirPaqUDP() {
		try {
			uSocket.receive(uPacket);
		} catch (IOException e) {
			System.err.println("Fail recieving UDP packet");
			e.printStackTrace();
		}
		System.out.println("Reçu de : " + uPacket.getAddress() + ":"
				+ uPacket.getPort());
		decalage = uBuf[0];
		noTCP = uBuf[1]*256+(uBuf[2]&0xFF);
		System.out.println("Le décalage est de " + decalage+" à envoyer sur le port TCP : "+noTCP);
	}

	// méthodes TCP
	public void connexionSocketTCP(){
		try {
			socket = new Socket(InetAddress.getByName("localhost"), noTCP);
		} catch (UnknownHostException e) {
			System.err.println("Fail bounding to TCP socket");
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Fail bounding to TCP socket");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void doTCP(){
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			System.err.println("Fail getting client inputStream");
			e.printStackTrace();
			System.exit(1);
		}

		try {
			out = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			System.err.println("Fail getting client outputStream");
			e.printStackTrace();
			System.exit(1);
		}
		String lineIn, lineOut;

		try {
			do{
				lineIn = in.readLine();
				lineOut = new String();
				for(int i=0; i!=lineIn.length(); i++){
					char c = lineIn.charAt(i);
					lineOut += transform(c, decalage);
				}
				out.println(lineOut);
			}while(!lineIn.isEmpty());
		} catch (IOException e) {
			System.err.println("Fail reading line from client");
			System.err.println(e);
			System.exit(1);
		}
		catch (Exception e){
			System.err.println("Error while reading next line from client");
			e.printStackTrace();
			System.exit(1);
		}
	}



	// méthodes générales
	
	public int transform(int c, int shift){
		int startVal = 0;
		if(('a'<=c) && (c<='z'))
			startVal = 'a';
		else if(('A'<=c) && (c<='Z'))
			startVal = 'A';
		else return c;
		return (c - startVal +shift)%26+startVal;
	}

	public void doService(){
		recevoirPaqUDP();
		connexionSocketTCP();
		doTCP();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ServeurCesar().doService();
	}

}
