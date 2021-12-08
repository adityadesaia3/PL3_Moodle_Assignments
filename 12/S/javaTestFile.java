import java.util.*;
import java.lang.*;
import java.io.*;
import java.net.*;

// MultiThreaded Server

class ImplementingThreading implements Runnable
{
	Socket client;	
	Thread ts;
	
	ImplementingThreading(Socket tClient)
	{
		client = tClient;
		ts = new Thread(this);
	}
	
	public void run()
	{
		try
		{
			BufferedOutputStream bout = new BufferedOutputStream(client.getOutputStream());
			
			try
			{
				byte b [] = "Client Connected!".getBytes();
				bout.write(b);
				bout.flush();
			}
			catch(Exception e) {}
			
			try
			{	
				BufferedInputStream bin = new BufferedInputStream(client.getInputStream());
				byte b [] = new byte[1024];
				bin.read(b);
				
				String str = new String(b);
				for (int i = 0; str.charAt(i) != '\0'; i++)
					System.out.print(str.charAt(i));
				System.out.println();
			}
			catch(Exception e) {}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

public class MultiThreaded_TCP_Server
{	
	public static void main(String args [])
	{	
		try
		{
			ServerSocket ss1 = new ServerSocket(5101);
			while (true)
			{
				Socket client = ss1.accept();
				ImplementingThreading it = new ImplementingThreading(client);
				it.ts.start();
			}
		}
		catch(Exception e) {}
	}
}
