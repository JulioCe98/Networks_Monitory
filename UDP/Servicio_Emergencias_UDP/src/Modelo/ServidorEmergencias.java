package Modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServidorEmergencias {
	
	public boolean enviarMensaje(String mensaje) {
		boolean resultado = false;
		
		try {
			DatagramSocket dSock = new DatagramSocket();
			DatagramPacket dPacket = null;
			InetAddress dirGrupo = InetAddress.getByName("239.1.2.2");
		
			if(mensaje != null) {
				byte[] buf = mensaje.getBytes();
				dPacket = new DatagramPacket(buf, buf.length,dirGrupo,5000);
				dSock.send(dPacket);
				resultado = true;
			}
			
			dSock.close();
		
		} catch (IOException e) {
			
			System.out.println(e);
		}
		return resultado;
	}
	
	
	public static void main(String[] args) throws IOException {
		
		boolean continuar = true;
		String mensaje = null;
		
		ServidorEmergencias server = new ServidorEmergencias();
		while (continuar) {
			System.out.println("Ingrese (1) para MENSAJE, Ingrese (2) para TERMINAR");
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			int opcion = Integer.parseInt(bf.readLine());
			switch (opcion) {
			case 1:
				System.out.println("digite el mensaje a enviar!");
				mensaje = bf.readLine();
				if (server.enviarMensaje(mensaje)) {
					System.out.println("mensaje enviado");
				}
				else {
					System.out.println("Error al enviar el mensaje");
				}
				break;

			case 2:
				continuar = false;
				server.enviarMensaje("FIN");
				break;
			
			
			default:
				System.out.println("por favor seleccione una opcion correcta");
				break;
			
		}
		
	}

	}
}
