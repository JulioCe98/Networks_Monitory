package Modelo;
import java.io.*;
import java.net.*;
import java.util.Scanner;

import javax.net.ssl.SSLSocketFactory;
public class SSLClient {
public static final String TRUSTTORE_LOCATION = "/Users/juliocesargaviriajaramillo/keystore.jks";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("SSLClientSocket Started");
		// this an argument like -Djavax.net.ssl.trustStore="keystore"....
		System.setProperty("javax.net.ssl.trustStore", TRUSTTORE_LOCATION);
		SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
		
		Socket socket;
		try {
			socket = sf.createSocket("localhost", 8000);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Scanner scanner = new Scanner(System.in);
			while(true)
			{
				System.out.println("Enter text: ");
				String inputLine = scanner.nextLine();
				if("quit".equalsIgnoreCase(inputLine)) 
				{
					break;
				}
				out.println(inputLine);
				System.out.println("Server response: "+  br.readLine());
			}
			System.out.println("SSLServerSocket Terminated");

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
	}

}