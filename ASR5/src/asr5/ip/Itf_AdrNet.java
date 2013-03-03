package asr5.ip;

import java.net.InetAddress;

/**
 * TP sur l'adresse IPv4 
 */
/**
 * @author Jean Carle
 */
public interface Itf_AdrNet {

/**
 * Au moins deux constructeurs seront n�cessaires
 * 
 * AdrNet(java.net.InetAddress ip);
 * AdrNet(String ip) throws java.io.UnknownHostException ;
 * AdrNet() throws java.io.UnknownHostException ;
 */
	/**
	 * @return : classe de l'adresse IP sous forme d'un caract�re A, B, C, D, E
	 *         ou 'Z' si erreur
	 */
	char getClassIP() ;

	InetAddress getNetwork() throws java.net.UnknownHostException ;

	InetAddress getBroadcast() throws java.net.UnknownHostException ;

	boolean equal(AdrNet ip) throws java.net.UnknownHostException ;
}
