package Modelo;
import java.io.*;
import java.net.*;

public class Ejemplo_Whois {

	
	public static void main(String[] args)  throws Exception{
		
		int c;
		Socket s = new Socket("whois.internic.net",43);
		InputStream in = s.getInputStream();
		OutputStream out = s.getOutputStream();
		String str = (args.length == 0 ? "starwave.com" : args[0]) + "\n"; byte buf[] = new byte[str.length()];
		buf  = str.getBytes();
		out.write(buf);
		String concat = "";
		while ((c = in.read())!= -1) {
			concat += (char)c;
			
		}
		System.out.println(concat);
		s.close();

	}
}
