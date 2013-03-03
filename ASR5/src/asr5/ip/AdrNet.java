package asr5.ip;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * 
 * @author herlinc
 *
 */

public class AdrNet implements Itf_AdrNet{
	protected InetAddress ipLocale;
	//protected InetAddress ipDistante;

	public AdrNet() throws UnknownHostException{
		ipLocale = InetAddress.getLocalHost();
		//ipDistante = null;
	}

	/*public AdrNet(java.net.InetAddress ip) throws UnknownHostException{
		ipLocale = InetAddress.getLocalHost();

	}*/

	public AdrNet(String ip) throws UnknownHostException{
		ipLocale = InetAddress.getByName(ip);
		//ipDistante = InetAddress.getByName(ip);
	}

	@Override
	public boolean equal(AdrNet ip) throws UnknownHostException {
		return ip.ipLocale.equals(this.ipLocale);
	}

	@Override
	public InetAddress getBroadcast() throws UnknownHostException {
		/*String retS = "";
		if(this.getClassIP()== 'A')
			retS += this.ipLocale.toString().substring(ipLocale.toString().indexOf('/')+1,ipLocale.toString().indexOf('.'))+".255.255.255";
		else if(this.getClassIP() == 'B')
			retS += this.ipLocale.toString().substring(ipLocale.toString().indexOf('/')+1, ipLocale.toString().indexOf('.', ipLocale.toString().indexOf('.')+1))+"255.255";
		else if(this.getClassIP() == 'C')
			retS += this.ipLocale.toString().substring(ipLocale.toString().indexOf('/')+1, ipLocale.toString().indexOf('.', ipLocale.toString().indexOf('.', ipLocale.toString().indexOf('.')+1)+1))+".255";
		InetAddress ret = InetAddress.getByName(retS);
		return ret;*/
		byte[] retT = new byte[4];
		byte[] ip = ipLocale.getAddress();
		if(this.getClassIP()== 'A'){
			retT[0] = ip[0];
			retT[1] = (byte) 255;
			retT[2] = (byte) 255;
			retT[3] = (byte) 255;
		}
		else if(this.getClassIP() == 'B'){
			retT[0] = ip[0];
			retT[1] = ip[1];
			retT[2] = (byte) 255;
			retT[3] = (byte) 255;
		}
		else if(this.getClassIP() == 'C'){
			retT[0] = ip[0];
			retT[1] = ip[1];
			retT[2] = ip[2];
			retT[3] = (byte) 255;
		}
		InetAddress ret = InetAddress.getByAddress(retT);
		return ret;

	}

	@Override
	public char getClassIP() {
		/*if(ipLocale.isMulticastAddress())
			return 'D';
		else{*/
		/*String prem8S = ipLocale.toString().substring(ipLocale.toString().indexOf("/")+1,ipLocale.toString().indexOf('.'));
		int prem8 = Integer.parseInt(prem8S);
		if(prem8>=0 && prem8<=127)
			return 'A';
		else if(prem8>=128 && prem8<=191)
			return 'B';
		else if(prem8>=192 && prem8<=223)
			return 'C';
		else if(prem8>=224 && prem8<=239)
			return 'D';
		else if(prem8>=240 && prem8<=247)
			return 'E';
		//}
		else
			return 0;*/
		byte[] ip = ipLocale.getAddress();
		if((ip[0]&0xFF)>=0 && (ip[0]&0xFF)<=127)
			return 'A';
		else if((ip[0]&0xFF)>=128 && (ip[0]&0xFF)<=191)
			return 'B';
		else if((ip[0]&0xFF)>=192 && (ip[0]&0xFF)<=223)
			return 'C';
		else if((ip[0]&0xFF)>=224 && (ip[0]&0xFF)<=239)
			return 'D';
		else if((ip[0]&0xFF)>=240 && (ip[0]&0xFF)<=247)
			return 'E';
		else
			return 0;
	}

	@Override
	public InetAddress getNetwork() throws UnknownHostException {
		/*String retS = "";
		if(this.getClassIP()== 'A')
			retS += this.ipLocale.toString().substring(ipLocale.toString().indexOf('/')+1,ipLocale.toString().indexOf('.'))+".0.0.0";
		else if(this.getClassIP() == 'B')
			retS += this.ipLocale.toString().substring(ipLocale.toString().indexOf('/')+1, ipLocale.toString().indexOf('.', ipLocale.toString().indexOf('.')+1))+"0.0";
		else if(this.getClassIP() == 'C')
			retS += this.ipLocale.toString().substring(ipLocale.toString().indexOf('/')+1, ipLocale.toString().indexOf('.', ipLocale.toString().indexOf('.', ipLocale.toString().indexOf('.')+1)+1))+".0";
		InetAddress ret = InetAddress.getByName(retS);
		return ret;*/
		byte[] retT = new byte[4];
		byte[] ip = ipLocale.getAddress();
		if(this.getClassIP()== 'A'){
			retT[0] = ip[0];
			retT[1] = 0;
			retT[2] = 0;
			retT[3] = 0;
		}
		else if(this.getClassIP() == 'B'){
			retT[0] = ip[0];
			retT[1] = ip[1];
			retT[2] = 0;
			retT[3] = 0;
		}
		else if(this.getClassIP() == 'C'){
			retT[0] = ip[0];
			retT[1] = ip[1];
			retT[2] = ip[2];
			retT[3] = 0;
		}
		InetAddress ret = InetAddress.getByAddress(retT);
		return ret;
	}

	public String toString(){
		String ret = "";
		ret += "Machine : " + ipLocale.toString() + "\n";
		ret += "  classe : " + this.getClassIP() ;
		if(this.getClassIP()=='A' ||
				this.getClassIP()=='B' ||
				this.getClassIP()=='C'){
			ret+=", réseau d'appartenance : ";
			try{
				ret += this.getNetwork().toString();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			ret += "\n adresse de diffusion : ";
			try{
				ret += this.getBroadcast().toString();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		ret += "\n";
		return ret;
	}
	
	public static void scanNetwork(){
//		adresse forme 172.18.48.0/22
//		1010 1100 . 0001 0010 . 0011 00 | 00 . 0000 0000
		for(int r=48; r<52; r++){
			for(int m=1; m<255; m++){
				
			}
		}
	}

	public static void main(String[] args) throws UnknownHostException {
		AdrNet ipL = new AdrNet();
		System.out.println("Machine Locale : "+ipL);
		String ipDS = new String();
		if(args.length==0){
			Scanner sc = new Scanner(System.in);
			System.out.print("Entrer une adresse IPv4 : ");
			ipDS = sc.nextLine();
		}
		else{
			ipDS = args[0];
		}
		AdrNet ipD = new AdrNet(ipDS);
		System.out.println("Machine distante : "+ipD);
		if(ipL.getNetwork().equals(ipD.getNetwork()))
			System.out.println("==> Même réseau <==");
		else
			System.out.println("==> Réseaux différents <==");

	}

}
