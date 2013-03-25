package asr5.tcp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * @author charlerlin
 *
 */

public class ServeurDaytime {
	
	private ServerSocket servSocket;
	protected static final int SERVICE = 9876;
	
	public ServeurDaytime() throws IOException{
		servSocket = new ServerSocket(SERVICE);
	}
	
	public Socket start() throws IOException{
		return servSocket.accept();
	}
	
	public void doService(Socket client){
		PrintWriter out; 
		GregorianCalendar date = new GregorianCalendar();
		String dateS = date.get(GregorianCalendar.DAY_OF_MONTH)+" "+(date.get(GregorianCalendar.MONTH)+1)+" "+date.get(GregorianCalendar.YEAR)+", "+date.get(GregorianCalendar.HOUR_OF_DAY)+":"+date.get(GregorianCalendar.MINUTE);
		try {
			out = new PrintWriter(client.getOutputStream(),true);
			System.out.println("client connecté");
//			out.println(dateS); 
			out.println(new Date()); 
//			System.out.println(dateS+" date envoyée");
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
