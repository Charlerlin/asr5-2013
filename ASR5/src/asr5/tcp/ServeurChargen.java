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
	
	public Socket start() throws IOException{
		return servSocket.accept();
	}
	
	public void doService(Socket client) throws IOException{
		out = new PrintWriter(client.getOutputStream(), true);
		while(true){
			for(int i=33; i!=127; i++){
				out.write(""+(char)i);
			}
			out.write("\n");
		}
	}
	
	public static void main(String[] args) throws IOException {
		ServeurChargen sc = new ServeurChargen();
		Socket client = sc.start();
		System.out.println("client connecté");
		sc.doService(client);
	}
}
