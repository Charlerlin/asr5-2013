package asr5.udp;

public class Q3Buffer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		byte[] buffer= {'B','o','n','j','o','u','r'} ;
		System.out.println("buffer : "+new String(buffer));
		buffer[0]='S';buffer[1]='a';buffer[2]='l';buffer[3]='u';buffer[4]='t';
		System.out.println("buffer : "+new String(buffer,0,5));



	}

}
