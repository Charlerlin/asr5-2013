package asr5.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class ClientMul {
	protected DatagramSocket socket;
	protected DatagramPacket packet;
	protected BufferedReader inUser;
	protected byte[] bufR;
	protected byte[] bufS;
	
	public ClientMul() {
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			System.err.println("Fail creating socket");
			System.exit(1);
		}
		bufR = new byte[512];
		try {
			packet = new DatagramPacket(bufR, bufR.length, new InetSocketAddress("localhost", ServeurDayTime.SERVICE));
		} catch (SocketException e) {
			System.err.println("Fail creating or bounding packet");
			e.printStackTrace();
		}
		System.out.println("Client prêt !");
	}
	
	public String typePhrase(){
		inUser = new BufferedReader(new InputStreamReader(System.in));
		String phrase = "";
		try {
			phrase = inUser.readLine();
		} catch (IOException e) {
			System.err.println("Fail getting String from user input");
		}
		return phrase;
	}
	
	public void sendPacket(String phrase){
		bufS = phrase.getBytes();
		packet.setData(bufS, 0, bufS.length);
		try {
			socket.send(packet);
		} catch (IOException e) {
			System.err.println("Fail sending packet");
			System.err.println(e);
		}
	}
	
	public void receivePacket(){
		packet.setLength(bufR.length);
		try {
			socket.receive(packet);
		} catch (IOException e) {
			System.err.println("Fail receiving packet");
			e.printStackTrace();
		}
		
		System.out.println(new String(bufR, 0,packet.getLength()));
		
		socket.close();
	}
	
	public void doReceive(){
		String phrase = typePhrase();
		sendPacket(phrase);
		receivePacket();
	}

	public static void main(String[] args) {
		new ClientMul().doReceive();
	}

}
