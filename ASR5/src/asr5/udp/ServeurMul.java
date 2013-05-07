package asr5.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServeurMul {
	protected DatagramPacket packet;
	protected DatagramSocket socket;
	protected byte[] bufR;
	protected byte[] bufS;
	protected int mul;
	protected String phrase;

	public static int SERVICE = 9878;

	public ServeurMul() {
		bufR = new byte[100];
		packet = new DatagramPacket(bufR, bufR.length);
		try {
			socket = new DatagramSocket(SERVICE);
		} catch (SocketException e) {
			System.err.println("Fail creating socket");
			System.err.println(e);
			System.exit(1);
		}
		System.out.println("Serveur prêt !");
	}

	public boolean receivePacket(){
		try {
			socket.receive(packet);
			return true;
		} catch (IOException e) {
			System.err.println("Fail recieving packet");
			System.err.println(e);
			return false;
		}
	}

	public boolean displayR(){
		String s = new String(bufR, 0, packet.getLength());
		System.out.println("Reçu de : "+packet.getAddress()+":"+packet.getPort());
		try {
			mul = Integer.parseInt(s.substring(0, 1));
		} catch (NumberFormatException e) {
			System.err.println("Fail parsing the int");
			System.err.println(e);
			return false;
		}
		phrase = s.substring(1);
		System.out.println("Multiplicateur : "+mul);
		System.out.println("Phrase : "+phrase);
		return true;
	}

	public void sendPacket(){
		String ret = "";
		/*for(int i=0; i!=mul; i++){
			ret += phrase;
		}*/
		bufS = ret.getBytes();
		packet.setData(bufR, 0, bufR.length);
		try {
			socket.send(packet);
		} catch (IOException e) {
			System.err.println("Fail sending packet");
			System.err.println(e);
		}
	}

	public void doService(){
		while(true){
			if(receivePacket())
				if(displayR())
					sendPacket();
		}
	}


	public static void main(String[] args) {
		new ServeurMul().doService();
	}

}
