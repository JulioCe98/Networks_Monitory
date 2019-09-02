package InetAddresses_And_SomeUsefulCode;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SpamCheck {
	
	public static final String BLACKHOLE = "sbl.spamhaus.org";
	
	
	
	public static void main(String[] args) {
		if (args.length ==0){
			args = new String[]{"207.34.56.23","125.12.32.4","130.130.130.130","130.0.0.0","207.35.64.80"};
		}
		
		for (String arg : args) {
			if(isSpammer(arg)) { 
				System.out.println(arg + " is a known spammer");
			}
			else {
				System.out.println(arg + " appears legitimate");
			}
		}

	}
	
	private static boolean isSpammer(String arg) {
		boolean respuesta = false;
		try {
			InetAddress address = InetAddress.getByName(arg);
			byte[] quad = address.getAddress();
			String query = BLACKHOLE;
			for(byte octet : quad) {
				int unsignedByte = octet < 0 ? octet + 256 : octet;
				query = unsignedByte + ". " + query;
 			}
			InetAddress.getByName(query);
			respuesta = true;
			
		} catch (UnknownHostException e) {
			System.out.println(e);
		}
		return respuesta;
	}

}
