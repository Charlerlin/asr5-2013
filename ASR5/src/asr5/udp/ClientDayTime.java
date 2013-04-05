package asr5.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class ClientDayTime {
	protected DatagramSocket socket;
	protected DatagramPacket packet;
	protected byte[] buf;
	
	public ClientDayTime(){
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			System.err.println("Fail creating socket");
			e.printStackTrace();
		}
		buf = new byte[30];
		try {
			packet = new DatagramPacket(buf, buf.length, new InetSocketAddress("localhost", ServeurDayTime.SERVICE));
		} catch (SocketException e) {
			System.err.println("Fail creating or bounding packet");
			e.printStackTrace();
		}
	}
	
	public void doReceive(){
		//envoi d'un paquet vide de demande
		packet.setLength(0);
		try {
			socket.send(packet);
		} catch (IOException e) {
			System.err.println("Fail sending empty packet");
			e.printStackTrace();
		}
		
		//réception du daytime
		packet.setLength(buf.length);
		try {
			socket.receive(packet);
		} catch (IOException e) {
			System.err.println("Fail receiving packet");
			e.printStackTrace();
		}
		
		System.out.println("daytime from "+packet.getAddress()+" is "+new String(buf, 0,packet.getLength()));
		
		socket.close();
	}
	
	public static void main(String[] args){
		new ClientDayTime().doReceive();
	}
}
