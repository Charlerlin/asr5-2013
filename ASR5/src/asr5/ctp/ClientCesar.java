package asr5.ctp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * 
 * @author charlerlin
 *
 */
public class ClientCesar {

	// numéros de ports
	private static final int noUDP = 12345;
	private static final int noTCP = 9876;

	// attributs généraux
	private int decalage;

	// attibuts UDP
	protected DatagramPacket uPacket;
	protected DatagramSocket uSocket;
	protected byte[] uBuf;

	// attributs TCP
	private ServerSocket servSocket;
	private BufferedReader inSrv, brFileIn;
	private File fIn, fOut;
	private FileReader frIn;
	private PrintWriter outSrv;
	private FileWriter fW;

	// constructeur	
	public ClientCesar(){
		System.out.println("ClientCesar, démarrage");
		creerSocketUDP();
		creerServTCP();
	}


	// méthodes UDP
	public void creerSocketUDP(){
		uBuf = new byte[3];
		try {
			uSocket = new DatagramSocket();
		} catch (SocketException e) {
			System.err.println("Fail creating UDP socket");
			e.printStackTrace();
			System.exit(1);
		}
		try {
			uPacket = new DatagramPacket(uBuf, uBuf.length, new InetSocketAddress("localhost", noUDP));
		} catch (SocketException e) {
			System.err.println("Fail creating or bounding UDP packet");
			e.printStackTrace();
		}
		System.out.println("Socket UDP prêt");
	}

	public void envoiPaqUDP(){
		uBuf[0] = (byte) decalage;
		uBuf[1] = (byte) (noTCP/256);
		uBuf[2] = (byte) (noTCP%256);
		uPacket.setData(uBuf, 0, uBuf.length);
		try {
			uSocket.send(uPacket);
		} catch (IOException e) {
			System.err.println("Fail sending UDP packet");
			e.printStackTrace();
		}
		System.out.println("Paquet envoyé. Décalage : "+decalage+" port : "+noTCP);
	}

	// méthodes TCP

	public void creerServTCP(){
		try {
			servSocket = new ServerSocket(noTCP);
		} catch (IOException e) {
			System.err.println("Fail creating TCP socket");
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("Serveur TCP pret");
	}

	public Socket attendClient(){
		try {
			return	servSocket.accept();
		} catch (IOException e) {
			System.err.println("Fail welcoming client");
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
	public void ouvrirFluxTCP(Socket socket){
		try {
			inSrv = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			System.err.println("Fail creating input Stream");
			e.printStackTrace();
			System.exit(1);
		}

		try {
			outSrv = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			System.err.println("Fail creating outputStream");
			e.printStackTrace();
			System.exit(1);
		} 
		System.out.println("Flux TCP ouverts");
	}
	
	public void lireFichier(){
		fIn = new File("texteCTP.txt");
		try {
			frIn = new FileReader(fIn);
		} catch (FileNotFoundException e) {
			System.err.println("Fail reading file");
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("lecture fichier succès");
		brFileIn = new BufferedReader(frIn);
	}

	public void envoiF(){
		String line;
		try {
			do
			{
				line = brFileIn.readLine();
				outSrv.println(line);
			}while (line != null);
		} catch (IOException e) {
			System.err.println("Error while reading next line");
			e.printStackTrace();
			System.exit(1);
		}
		catch(Exception e){
			System.err.println("Error while reading next line");
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("Envoi fichier avec succès");
	}
	
	public void recevoirF(){
		fOut = new File("EncodeCesar.txt");
		String line;
		try {
			fW = new FileWriter(fOut);
			do{
				line = inSrv.readLine();
				fW.write(line);
			}while(!line.isEmpty());
		} catch (IOException e) {
			System.err.println("Fail creating fileWriter");
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("Fichier reçu et écrit sur le disque");
	}

	public void fermerCo(){
		try {
			servSocket.close();
		} catch (IOException e) {
			System.err.println("Cannot close socket");
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("Connexions fermées");
	}
	
	
	// méthodes générales
	public void doService(){
		envoiPaqUDP();
		ouvrirFluxTCP(attendClient());
		envoiF();
		recevoirF();
		fermerCo();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ClientCesar().doService();
	}

}
