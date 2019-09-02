package Server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	

	/**
	 * Puerto por donde el servidor atendera a los clientes
	 */
	public static final int PORT = 8500;
	/**
	 * El servidor dispone de un serversocket, para permitir la conexion a los clientes
	 */
	private ServerSocket serverSocket;
	/**
	 * El servidor dispone de un socket para atender a cada cliente por individual
	 */
	private Socket socket;
	
	private boolean isConected;
	
	
	public Server() {
		try {
			serverSocket = new ServerSocket(PORT);
			isConected = true;
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}


	public  ServerSocket getServerSocket() {
		return serverSocket;
	}


	public  void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}


	public  Socket getSocket() {
		return socket;
	}


	public  void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	public boolean isConected() {
		return isConected;
	}


	public void setConected(boolean isConected) {
		this.isConected = isConected;
	}


	public static void main(String[] args) {
		
		try {
			System.out.println("::BIENVENIDO AL SERVER - REPOSITORY- ICESI::");
			Server server = new Server();
			while(server.isConected) {
				ServerSocket serverSocket = server.getServerSocket();
				Socket socket = serverSocket.accept();
				server.setSocket(socket);
				DataInputStream input;
				BufferedInputStream bis;
				BufferedOutputStream bos;
				input = new DataInputStream(socket.getInputStream());
				int in;
				byte[] byteArray;
				String filename = input.readUTF();
			    File localFile = new File( filename );
				bis = new BufferedInputStream(new FileInputStream(localFile));
				bos = new BufferedOutputStream(socket.getOutputStream());
				DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
				dos.writeUTF("Client_Repository/"+localFile.getName());
				byteArray = new byte[8192];
				while ((in = bis.read(byteArray)) != -1){
					bos.write(byteArray,0,in);
				 }
				 
				bis.close();
				bos.close();
				socket.close();
				input.close();
				dos.close();
			}
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	}

		
	
		
		
	
	
	
	
	


