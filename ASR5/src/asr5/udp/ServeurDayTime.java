package asr5.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Date;

public class ServeurDayTime {
	protected DatagramPacket packet;
	protected DatagramSocket socket;
	protected byte[] buf;
	protected int nb;

	public static final int SERVICE = 9876;

	public ServeurDayTime() {
		nb=0;
		buf = new byte[30];
		packet = new DatagramPacket(buf, buf.length);
		try {
			socket = new DatagramSocket(SERVICE);
		} catch (SocketException e) {
			System.err.println("Fail creating socket");
			System.err.println(e);
			System.exit(1);
		}
	}

	public void waitPacket(){
		try {
			socket.receive(packet);
		} catch (IOException e) {
			System.err.println("Fail recieving packet");
			System.err.println(e);
		}
	}

	public void sendPacket(){
		buf = new Date().toString().getBytes();
		packet.setData(buf, 0, buf.length);
		try {
			socket.send(packet);
		} catch (IOException e) {
			System.err.println("Fail sending packet");
			System.err.println(e);
		}
		System.out.println("Envoyé au client "+ ++nb);
	}

	public void doService(){
		while(true){
			waitPacket();
			sendPacket();
		}
	}

	public static void main(String[] args){
		new ServeurDayTime().doService();
	}
}
