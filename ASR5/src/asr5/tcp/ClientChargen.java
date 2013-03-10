package asr5.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 
 * @author charlerlin
 *
 */

public class ClientChargen {
	
	private Socket socket;
	private BufferedReader bf;

	
	public ClientChargen() throws UnknownHostException, IOException{
		socket = new Socket(InetAddress.getByName("localhost"), 19);
		bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	
	
	@Override
	public String toString() {
		String ret = "";
		try {
			ret = bf.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}



	public static void main(String[] args) throws UnknownHostException, IOException {
		ClientChargen cc = new ClientChargen();
		System.out.println(cc);
	}
}
