package asr5.tcp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.GregorianCalendar;

/**
 * 
 * @author charlerlin
 *
 */

public class ServeurDaytime {
	
	private ServerSocket socket;
	protected static final int SERVICE = 9876;
	
	public ServeurDaytime() throws IOException{
		socket = new ServerSocket(SERVICE);
	}
	
	public Socket start() throws IOException{
		return socket.accept();
	}
	
	public void doService(Socket client){
		PrintWriter out; 
		GregorianCalendar date = new GregorianCalendar();
		String dateS = date.get(GregorianCalendar.DAY_OF_MONTH)+" "+(date.get(GregorianCalendar.MONTH)+1)+" "+date.get(GregorianCalendar.YEAR)+", "+date.get(GregorianCalendar.HOUR_OF_DAY)+":"+date.get(GregorianCalendar.MINUTE);
		try {
			out = new PrintWriter(client.getOutputStream());
			System.out.println("client connecté");
			out.println(dateS);
			System.out.println(dateS+" date envoyée");
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		ServeurDaytime sdt = new ServeurDaytime();
		Socket client = sdt.start();
		sdt.doService(client);
	}

}
