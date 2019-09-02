package UDP_Discard;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPDiscardServer {
	
	public final static int PORT = 8500;
	public final static int MAX_PACKET_SIZE = 65507; 
	
	
	
	public static void main(String[] args) {
		byte[] buffer = new byte[MAX_PACKET_SIZE];
		System.out.println("Server is establishing connection .....[ok]");
		try (DatagramSocket dt = new DatagramSocket(PORT)){
			DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
			while (true) {
				dt.receive(dp);
				String line = new String(dp.getData(), 0, dp.getLength(),"8859_1");
				System.out.println(dp.getAddress() + " at port " + dp.getPort() + " says " + line);
				dp.setLength(buffer.length);
			}
		} catch (Exception e) {
			System.err.println(e);
		
		}
	}

}
