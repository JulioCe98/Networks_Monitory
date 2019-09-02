package UDP_Discard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPDiscardClient {
	
	public static final int PORT =8500;
	
	public static void main(String[] args) {
		
		System.out.println("Hello, please write wherever you want to say to the server....");
		String hostName = args.length > 0 ? args[0] : "localhost";
		
		try(DatagramSocket socket = new DatagramSocket()) {
			
			InetAddress server = InetAddress.getByName(hostName);
			BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
			while(true) {
				String line = userInput.readLine();
				if(line.equals(".")) break;
				byte[] data = line.getBytes();
				DatagramPacket output = new DatagramPacket(data, data.length,server,PORT);
				socket.send(output);
			}
			
		} catch (Exception e) {
		
			System.err.println(e);
		}
		
		
	}

}
