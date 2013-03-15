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

	private String preString(){
		String ret = "";
		while(true){
//		for(int i=0; i!=94; i++){
			try {
				System.out.print((char)bf.read());
//				ret += (char)bf.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		return ret;
	}


	@Override
	public String toString() {
		return preString();
	}



	public static void main(String[] args) throws UnknownHostException, IOException {
		ClientChargen cc = new ClientChargen();
		System.out.println(cc);
	}
}
