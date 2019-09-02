package Modelo;
import java.io.*;
import java.net.*;

import javax.net.ssl.SSLServerSocketFactory;

public class SSLServerSocket {

	public static final String KEYSTORE_LOCATION = "/Users/juliocesargaviriajaramillo/keystore.jks";
	public static final String KEYSTORE_PASSWORD = "shwq1998";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("javax.net.ssl.keyStore", KEYSTORE_LOCATION);
		System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASSWORD);
		SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
		try {
			ServerSocket serverSocket = ssf.createServerSocket(8000);
			System.out.println("SSLServerSocket Started in "+ serverSocket.getLocalPort());
			
			Socket socket = serverSocket.accept();
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("Client socket created");
			
			String line = null;
			while(((line = br.readLine()) != null))
			{
				System.out.println(line);
				
			}
			br.close();
			out.close();
			socket.close();
			serverSocket.close();
			System.out.println("SSLServerSocket Teminated");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
