package DatagramChannel_echo;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class UDPEchoClientWithChannels {

	public static final int PORT = 8500;
	public static final int LIMIT = 100;

	public static void main(String[] args) {
		SocketAddress remote = null;
		try {
			/**
			 * SocketAddress
			 * 
			 * This class represents a Socket Address with no protocol attachment. As an abstract class, it is meant to be subclassed with a specific, 
			 * protocol dependent, implementation.
			 * It provides an immutable object used by sockets for binding, connecting, or as returned values.
			 * 
			 * Attachment : Adjunto;
			 * Binding : Vincular;
			 * 
			 * 
			 */
			remote = new InetSocketAddress("127.0.0.1", PORT);
			
		} catch (RuntimeException e) {
			
			System.err.println("Usage: Java UDPEchoClientWithChannels host");

		}
		
		try (DatagramChannel channel = DatagramChannel.open()){
			/**
* protected abstract void implConfigureBlocking(boolean block)
                                       throws IOException
Adjusts this channel's blocking mode.
This method is invoked by the configureBlocking method in order to perform the actual work of changing the blocking mode. This method is only invoked if the new mode is different from the current mode.

Parameters:
block - If true then this channel will be placed in blocking mode; if false then it will be placed non-blocking mode
Throws:
IOException - If an I/O error occurs

Perform : Realizar;
			 */
			channel.configureBlocking(false);
			channel.connect(remote);
			Selector selector = Selector.open();
			/**
* public final SelectionKey register(Selector sel,
                                   int ops)
                            throws ClosedChannelException
Registers this channel with the given selector, returning a selection key.
An invocation of this convenience method of the form

sc.register(sel, ops)
behaves in exactly the same way as the invocation
sc.register(sel, ops, null)
Parameters:
sel - The selector with which this channel is to be registered
ops - The interest set for the resulting key
Returns:
A key representing the registration of this channel with the given selector
			 */
			channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
			/**
			 * public static ByteBuffer allocate(int capacity)
Allocates a new byte buffer.
The new buffer's position will be zero, its limit will be its capacity, its mark will be undefined, and each of its elements will be initialized to zero. 
It will have a backing array, and its array offset will be zero.

Parameters:
capacity - The new buffer's capacity, in bytes
Returns:
The new byte buffer
			 */
			ByteBuffer buffer = ByteBuffer.allocate(4);
			int n = 0;
			int numbersRead = 0;
			while(true) {
				if(numbersRead == LIMIT) break;
				//wait one minute for a connection
				selector.select(60000);
				Set<SelectionKey> readyKeys = selector.selectedKeys();
				if(readyKeys.isEmpty() && n==LIMIT) {
					/**
					 *  All packets have been written and it doesn't look like any 
					 *  more are will arrive from the network
					 */
					break;
				}
				else {
					Iterator<SelectionKey> iterator = readyKeys.iterator();
					while(iterator.hasNext()) {
						SelectionKey key = (SelectionKey)iterator.next();
						iterator.remove();
						if(key.isReadable()) {
							buffer.clear();
							channel.read(buffer);
							buffer.flip();
							int echo = buffer.getInt();
							System.out.println("Read : " + echo);
							numbersRead++;
						}
						if (key.isWritable()) {
							buffer.clear();
							buffer.putInt(n);
							buffer.flip();
							channel.write(buffer);
							System.out.println("Wrote : " + n);
							n++;
							if (n==LIMIT) {
								key.interestOps(SelectionKey.OP_READ);
							}
						}
					}
				}
			}
			System.out.println("Echoed " + numbersRead + " out of " + LIMIT + " sent");
			System.out.println("Success rate : " + (100.0 * (numbersRead/LIMIT)) + "%");
			
		} catch (Exception e) {
			System.err.println(e);
		}
			
		
	}
	
}
