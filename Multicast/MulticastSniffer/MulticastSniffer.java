package MulticastSniffer;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastSniffer {
	
	public static final String dirGrupo = "230.0.0.0";
	public static final int PORT = 8500;
	
	public static void main(String[] args) {
		InetAddress group = null;
		int port = 0;
		try {
			
		} catch (Exception e) {
			System.err.println(e);
		}
		
		MulticastSocket ms = null;
		try {
			ms = new MulticastSocket(PORT);
			ms.joinGroup(group);
			byte[] buffer = new byte[8192];
			while(true) {
				DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
				ms.receive(dp);
				String s = new String(dp.getData(), "8859_1");
				System.out.println(s);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		finally {
			if (ms != null) {
				try {
					ms.leaveGroup(group);
					ms.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		
		
	}

}
