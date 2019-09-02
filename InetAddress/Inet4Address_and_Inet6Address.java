package InetAddresses_And_SomeUsefulCode;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class Inet4Address_and_Inet6Address {
	
	public Inet4Address_and_Inet6Address() {
		
	}	
	
	public static void main(String[] args) throws SocketException {
		NetworkInterface ni = getByName("eth0");
		System.out.println("-----------------------------");
		ni = getByInetAddres("127.0.0.1");
		System.out.println("-----------------------------");
		getNetworkInterfaces();
		System.out.println("-----------------------------");
		getInetAddresses("utun0");
		System.out.println("-----------------------------");
	}
	
	//FACTORY METHODS
	
	public static NetworkInterface getByName(String name) throws SocketException{
		NetworkInterface ni = null;
		try {
			ni = NetworkInterface.getByName(name);
			if(ni == null) {
				System.out.println("El nombre de la interface no es eth0");
			}
			else {
				System.out.println(ni.getDisplayName());
				
			}
		
		
		} catch (SocketException e) {
			System.out.println("Salto socketException");
		}
		
		return ni;
	}
	
	
	public static NetworkInterface getByInetAddres(String address) throws SocketException{
		
		NetworkInterface ni = null;
		try {
			InetAddress local = InetAddress.getByName(address);
			ni = NetworkInterface.getByInetAddress(local);
			if(ni == null) {
				System.out.println("No es la direccion Local del loopback");
			}
			else {
				System.out.println(ni.getName());
			}
		}
		catch(SocketException e) {
			System.out.println("no se pueden listar las interfaces");
		}
		catch(UnknownHostException ex) {
			System.out.println("no se pudo buscar el loopback 127.0.0.1");
		}
		
		return null;
		
	}
	
	public static Enumeration getNetworkInterfaces() throws SocketException{
		Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
		while(interfaces.hasMoreElements()) {
			NetworkInterface ni = interfaces.nextElement();
			System.out.println("Interface " + ni);
			System.out.println("Nombre : "+ ni.getName());
			System.out.println("Display Name : " + ni.getDisplayName());
		}
		
		return interfaces;
		
	}
	
	
	//GETTER METHODS
	
	public static Enumeration getInetAddresses(String nameOfInterface) throws SocketException {
		NetworkInterface ni = NetworkInterface.getByName(nameOfInterface);
		Enumeration addresses = ni.getInetAddresses();
		while(addresses.hasMoreElements()) {
			System.out.println(addresses.nextElement());
		}
		return addresses;
	}
	
	

}
