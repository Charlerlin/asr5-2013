package asr5.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

/**
 * 
 * @author charlerlin
 *
 */

public class ClientDayTime {
	
	private Socket socket;
	private InputStreamReader isr;
	private BufferedReader bf;
	
	public ClientDayTime() throws UnknownHostException, IOException{
		socket = new Socket(InetAddress.getByName("localhost"), 13);
		isr = new InputStreamReader(socket.getInputStream());
		bf = new BufferedReader(isr);
	}

	@Override
	public String toString() {
		String ret = "";
		try {
			ret=  bf.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
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
