import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UDPServer
{
	public static void main(String args[])
	{
		DatagramSocket sock = null;
			try
			{
			//1. creating a server socket, parameter is local port number
			sock = new DatagramSocket(1237);
			//buffer to receive incoming data
			byte[] buffer = new byte[65536];
			DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
			//2. Wait for an incoming data
			System.out.println("Server socket created. Waiting for incoming data...");
			//communication loop
				while(true)
				{
					sock.receive(incoming);
					byte[] data = incoming.getData();
					String s = new String(data, 0, incoming.getLength());
					System.out.println("Got Message from Client:");
					//echo the details of incoming data - client IP : client port - client message
					System.out.println("Host Address: "+incoming.getAddress().getHostAddress() + "\nPort Number: " + incoming.getPort() + "\nMessage: " + s);					
					System.out.println("Echo to the Client: " + s +"\n");
					
					//Convert Message to Upper Case
					String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
					s = s.toUpperCase() + " "+timeStamp;
					
					DatagramPacket dp = new DatagramPacket(s.getBytes() , s.getBytes().length , incoming.getAddress() , incoming.getPort());
					sock.send(dp);
				}
			}
			catch(IOException e)
			{
				System.err.println("IOException " + e);
			}
			finally
			{
				sock.close();
			}
	}
}