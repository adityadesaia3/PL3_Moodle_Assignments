import java.util.*;
import java.lang.*;
import java.net.*;
import java.io.*;
import java.lang.Math.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

class ServerThread
{
	static DatagramSocket ds;
	static boolean isDatagramSocketStart = false;
	static int countOfClientsConnected;
	
	static Vector <InetAddress> clientAddress = new Vector <InetAddress>();
	static Vector <Integer> portNumber = new Vector <Integer>();
	static Vector <String> customName = new Vector <String>();
	
	ServerThread()
	{
		if (!isDatagramSocketStart)
		{
			try
			{
				ds = new DatagramSocket(7000);
			}
			catch(Exception e) { e.printStackTrace(); }
			isDatagramSocketStart = true;
		}
	}
	
	void sendAcknowledgeMessage()
	{
		if (countOfClientsConnected == 1)
		{
			String messageToSend = "Only you are connected!\n";
			messageToSend += "Total number of clients connected to the server is " + countOfClientsConnected + ".";
			
			// Sending message to other connected clients
			ServerThreadSend sts = new ServerThreadSend(messageToSend, clientAddress.elementAt(0), portNumber.elementAt(0));			
		}
		else if (countOfClientsConnected > 1)
		{
			for (int i = 0; i < customName.size() - 1; i++)
			{
				String messageToSend = "";

				messageToSend = customName.elementAt(customName.size() - 1);
				messageToSend += " is now Connected to the server.\nTotal number of clients connected to the server is " + countOfClientsConnected + ".";
				
				// Sending message to other connected clients
				ServerThreadSend sts = new ServerThreadSend(messageToSend, clientAddress.elementAt(i), portNumber.elementAt(i));
			}
			
			String messageToSend = "You are connected to the server!\n";
			messageToSend += "Total number of clients connected to server is " + countOfClientsConnected + ".";
			
			// Sending message to other connected clients
			ServerThreadSend sts = new ServerThreadSend(messageToSend, clientAddress.elementAt(customName.size() - 1), portNumber.elementAt(customName.size() - 1));
		}
	}
	void sendMessage(String t_message, InetAddress t_clientAddress, int t_portNumber)
	{
		int sendersLocationInVector = 0;
		
		for (int i = 0; i < clientAddress.size(); i++)
		{
			if (clientAddress.elementAt(i).equals(t_clientAddress))
			{
				if (portNumber.elementAt(i) == t_portNumber)
				{
					sendersLocationInVector = i;
					break;
				}
			}
		}
		
		// Sending message to all clients except the sender (from whom server receive message)
		for (int i = 0; i < customName.size(); i++)
		{
			String messageToSend = "";
			if (i != sendersLocationInVector)
			{
				messageToSend += customName.elementAt(sendersLocationInVector);
				messageToSend += ":   ";
				messageToSend += t_message;
				
				// Sending message to other connected clients
				ServerThreadSend sts = new ServerThreadSend(messageToSend, clientAddress.elementAt(i), portNumber.elementAt(i));
				
			}
		}
	}
	void addClient(InetAddress t_clientAddress, int t_portNumber)
	{
		boolean isClientIPPresent = false;
		boolean isClientPortNumberPresent = false;
		
		for (int i = 0; i < clientAddress.size(); i++)
		{
			if (clientAddress.elementAt(i).equals(t_clientAddress))
				isClientIPPresent = true;
		}
		
		if (isClientIPPresent)		// If Client's IP Address is present
		{
			for (int i = 0; i < portNumber.size(); i++)
			{
				if (portNumber.elementAt(i) == t_portNumber)
					isClientPortNumberPresent = true;
			}
		}
		
		if (!isClientIPPresent)
		{
			clientAddress.add(t_clientAddress);
			portNumber.add(t_portNumber);
			countOfClientsConnected++;
			
			System.out.println("Number of clients connected: " + countOfClientsConnected);
			
			String str = "Client_" + countOfClientsConnected;
			customName.add(str);
			
			sendAcknowledgeMessage();
		}
		else if (isClientIPPresent && !isClientPortNumberPresent)
		{
			clientAddress.add(t_clientAddress);
			portNumber.add(t_portNumber);
			countOfClientsConnected++;
			System.out.println("Number of clients connected: " + countOfClientsConnected);
			
			String str = "Client_" + countOfClientsConnected;
			customName.add(str);
			
			sendAcknowledgeMessage();
		}
	}
}

class ServerThreadReceive extends ServerThread implements Runnable
{
	Thread ct;
	
	ServerThreadReceive()
	{
		ct = new Thread(this);
		ct.start();
	}
	
	public void run()
	{
		while(true)
		{
			try
			{
				byte bR [] = new byte[1024];
				DatagramPacket dpR = new DatagramPacket(bR, 0, bR.length);
				ds.receive(dpR);
				String strR = new String(bR);			
				
				String messageReceived = "";
				for (int i = 0; strR.charAt(i) != '\0'; i++)
					messageReceived += String.valueOf(strR.charAt(i));
				
				InetAddress clientInetAddress = dpR.getAddress();
				int clientPortNumber = dpR.getPort();
				String clientIPAddress = clientInetAddress.toString();
				
				// Always check and add Client to Connected Client List
				addClient(clientInetAddress, clientPortNumber);
				
				// MessageReceived, From this IP, and From this Port
				sendMessage(messageReceived, clientInetAddress, clientPortNumber);
				
			}
			catch(Exception e) { e.printStackTrace(); }
		}
	}
}

class ServerThreadSend extends ServerThread implements Runnable
{
	Thread ct;
	String messageToSend;
	InetAddress addressToSend;
	int portNumberToSend;
	
	ServerThreadSend(String t_messageToSend, InetAddress t_address, int t_portNumber)
	{
		ct = new Thread(this);
		messageToSend = t_messageToSend;
		addressToSend = t_address;
		portNumberToSend = t_portNumber;
		ct.start();
	}
	
	public void run()
	{
		try
		{
			messageToSend += "\n";
			byte bS [] = messageToSend.getBytes();
			DatagramPacket dpS = new DatagramPacket(bS, 0, bS.length, addressToSend, portNumberToSend);
			ds.send(dpS);
		}
		catch(Exception e) { e.printStackTrace(); }
	}
}

public class AssignmentNo13_Chatting_application_Server_R7
{
	public static void main(String args [])
	{
		// Starting "Receiving message" thread
		ServerThreadReceive ctr = new ServerThreadReceive();
	}
}
