package UDPScanner;

import java.net.DatagramSocket;
import java.net.SocketException;

public class ScannerUDP {
	
	
	public static void Scanning() {
		for (int i = 1024; i <= 65535; i++) {
			try {
				DatagramSocket dt = new DatagramSocket(i);
				dt.close();
			} catch (SocketException e) {
				System.out.println("There is a server on port : "+ i );
			}
		}
	}
	
	public static void main(String[] args) {
		
		Scanning();
		
	}

}
