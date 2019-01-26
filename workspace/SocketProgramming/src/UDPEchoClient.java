import java.io.*;
import java.net.*;
public class UDPEchoClient
{
	public static void main(String args[])
	{
		DatagramSocket sock = null;
		int ServerPort = 1237;
		String s;
		BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
		try
		{
			sock = new DatagramSocket();
			InetAddress host = InetAddress.getByName("10.26.25.209"); //127.0.0.1
				while(true)
				{
					//take input and send the packet
					System.out.println("\nEnter message to send : ");
					s = (String)cin.readLine();
					byte[] b = s.getBytes();
					DatagramPacket dp = new DatagramPacket(b , b.length , host , ServerPort);
					sock.send(dp);
					//now receive reply
					//buffer to receive incoming data
					byte[] ServerReplyBuffer = new byte[65536];
					DatagramPacket reply = new DatagramPacket(ServerReplyBuffer, ServerReplyBuffer.length);
					sock.receive(reply);
					byte[] data = reply.getData();
					s = new String(data, 0, reply.getLength());
					System.out.println("Got Response from Server: ");
					//echo the details of incoming data - server IP : server port - server message
					System.out.println("Server Address: " + reply.getAddress().getHostAddress() + "\nPortNumber: " + reply.getPort() + "\nReply Message: " + s);
				}
		}
		catch(IOException e)
		{
			System.err.println("IOException " + e);
		}
	}
}

