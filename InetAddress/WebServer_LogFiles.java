package InetAddresses_And_SomeUsefulCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class WebServer_LogFiles {
	
	public static void main(String[] args) {
		args = new String[]{"205.160.186.76"};
		String ip = "";
		String theRest = "";
		try(FileInputStream fin = new FileInputStream(args[0]);
			Reader in = new InputStreamReader(fin);	
			BufferedReader bin = new BufferedReader(in);) {
			
			for (String entry = bin.readLine();
				entry != null;
				entry = bin.readLine()) {
				int index  = entry.indexOf(' ');
				ip = entry.substring(0, index);
				theRest = entry.substring(index);
				
				//ask DNS for the hostname and print it out
				
				}
				
			try {
				InetAddress address = InetAddress.getByName(ip);
				System.out.println(address.getHostName() + theRest );
				
				
			} catch (UnknownHostException ex) {
				System.out.println(ex);
			}
			
			}
			 catch (IOException e) {
			System.out.println(e);
		}
	}

}
