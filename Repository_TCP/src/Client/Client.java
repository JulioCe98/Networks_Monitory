package Client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	
	public static final String LOCAL_HOST = "localhost";
	
	public static final int PORT = 8500;
	
	private  Socket socket;
	
	public Client() {
		
		try {
		socket = new Socket(LOCAL_HOST, PORT);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	


	public  Socket getSocket() {
		return socket;
	}





	public  void setSocket(Socket socket) {
		this.socket = socket;
	}





	public static void main(String[] args) {
		
		try {
			
			DataOutputStream out;
			
			BufferedInputStream bis;
			
			BufferedOutputStream bos;
			
			Client cliente = new  Client();
			
			Socket socket = cliente.getSocket();
			
			Scanner scan = new Scanner(System.in);
			
			System.out.println("::Bienvenido::\nIngrese el nombre del fichero que desea descargar");
			System.out.println("Ayuda : Server_Repository/archivo.txt");
			
			String name = scan.nextLine();
			
			out = new DataOutputStream(socket.getOutputStream());
			
			out.writeUTF(name);
			
			byte[] receivedData;
			
			int in;
			
			String file;
			
			receivedData = new byte[1024];
			
			bis = new BufferedInputStream(socket.getInputStream());
			
			DataInputStream dis=new DataInputStream(socket.getInputStream());
			
			file = dis.readUTF();
			
			file = file.substring(file.indexOf('\\')+1,file.length());
			
			bos = new BufferedOutputStream(new FileOutputStream(file));
			
			while ((in = bis.read(receivedData)) != -1){
				
				bos.write(receivedData,0,in);
			 }
			
			bos.close();
			dis.close();
			socket.close();
			scan.close();
			out.close();
			bis.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		 
		
		
		
		
		
	}

}
