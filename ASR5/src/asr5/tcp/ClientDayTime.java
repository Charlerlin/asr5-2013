package asr5.tcp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 
 * @author charlerlin
 *
 */

public class ClientDayTime {
	
	private Socket socket;
	
	public ClientDayTime() throws UnknownHostException, IOException{
		socket = new Socket(InetAddress.getByName("localhost"), 13);
	}

	@Override
	public String toString() {
		return "ClientDayTime [socket=" + socket + "]";
	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		ClientDayTime cdt = new ClientDayTime();
		System.out.println(cdt);

	}

}
