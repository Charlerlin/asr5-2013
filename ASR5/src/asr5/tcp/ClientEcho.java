package asr5.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 
 * @author charlerlin
 *
 */

public class ClientEcho {
	protected static final int SERVICE = ServeurEcho.SERVICE;//7;
	private Socket socket;
	private BufferedReader inSrv, inUser;
	private PrintWriter outSrv;

	public ClientEcho() throws UnknownHostException, IOException{
		socket = new Socket(InetAddress.getByName("localhost"), SERVICE);
		inSrv = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		outSrv = new PrintWriter(socket.getOutputStream(), true); 
		inUser = new BufferedReader(new InputStreamReader(System.in));
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		new ClientEcho().start();
	}

	public void start(){
		while(true){
			String line = "";
			try {
				line = inUser.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(line.equals(".") || line.toLowerCase().equals("fin")){
				break;
			}
			outSrv.println(line);
			try {
				System.out.println("echo : "+inSrv.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
