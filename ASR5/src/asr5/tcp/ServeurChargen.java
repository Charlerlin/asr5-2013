package asr5.tcp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author charlerlin
 *
 */

public class ServeurChargen {
	private ServerSocket servSocket;
	private PrintWriter out;

	protected static final int SERVICE = 9876;

	public ServeurChargen() throws IOException{
		servSocket = new ServerSocket(SERVICE);
	}

	public Socket awaiting() throws IOException{
		return servSocket.accept();
	}

	public void doService(Socket client) throws IOException{
		out = new PrintWriter(client.getOutputStream());
		while(!client.isOutputShutdown()){
			for(int i=33; i!=127; i++){
				out.print((char)i);
			}
			out.flush();
		}
		out.flush();
		client.close();
	}
	
	public void starten() throws IOException{
		while(true){
			Socket client = awaiting();
			System.out.println("client connecté");
			doService(client);
			System.err.println("sorti");
		}
	}
	
	public static void main(String[] args) throws IOException {
		new ServeurChargen().starten();
	}
}
