package DatagramChannel_echo;

import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class UDPEchoServerWithChannels {
	
	/**
	 * 
	 * Introduccion
	 * 
	 * DatagramChannel : 
	 * 
	 * 1) Es mucho mas "No bloqueante" que UDP
	 * 2) Si mucho tiene que esperar a que se borre el buffer local
	 * 3) No debe esperar a que un cliente este listo para recibir datos
	 * 4) No se detiene si un cliente esta esperando a que otro reciba
	 * 
	 * 
	 * 
	 * 
	 * ECHO 
	 * 
	 * 1) Permite visualizar si se estan perdiendo paquetes al enviar 100 enteros
	 * 
	 */
	
	
	
	
	public static final int PORT = 8500;
	public static final int MAX_PACKET_SIZE = 65507;

	public static void main(String[] args) {
		try {
			DatagramChannel channel = DatagramChannel.open();
			DatagramSocket socket = channel.socket();
			SocketAddress address = new InetSocketAddress(PORT);
			socket.bind(address);
			ByteBuffer buffer = ByteBuffer.allocateDirect(MAX_PACKET_SIZE);
			while(true) {
				SocketAddress client = channel.receive(buffer);
				//resetea el buffer para recibir nuevos datos (vuelve a la posicion 0)
				buffer.flip();
				channel.send(buffer, client);
				//limpia el buffer
				buffer.clear();
			}
		}
		catch (Exception e) {
			System.err.println(e);
			}
		
		
		
		
		
	}




}
