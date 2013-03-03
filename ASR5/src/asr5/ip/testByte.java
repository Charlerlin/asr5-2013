package asr5.ip;


/**
 * 
 * @author herlinc
 *
 */

public class testByte {
	public static void main(String[] args) {
		byte [ ] octets = { 0 , 31, 32, 63, 64, 127, (byte) 128, (byte) 255, (byte) 256, (byte) 511, (byte) 512};
		for ( byte octet : octets )
			System.out.println(octet & 0xFF);


	}
}
