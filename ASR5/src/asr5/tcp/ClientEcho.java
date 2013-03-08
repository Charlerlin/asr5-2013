package asr5.tcp;

import java.io.BufferedReader;
import java.io.Console;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientEcho {

	Socket socket;
	BufferedReader inBF;
	OutputStreamWriter oSR;
	Console c;
	
	public ClientEcho() throws UnknownHostException, IOException{
		socket = new Socket(InetAddress.getByName("localhost"), 7);
		inBF = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		oSR = new OutputStreamWriter(socket.getOutputStream()); 
	}
	
}
