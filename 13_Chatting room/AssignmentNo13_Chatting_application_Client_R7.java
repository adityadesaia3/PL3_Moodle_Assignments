import java.util.*;
import java.lang.*;
import java.net.*;
import java.io.*;
import java.lang.Math.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

class ClientThread
{
	static DatagramSocket ds;
	static boolean isDatagramSocketStart = false;
	
	ClientThread()
	{
		if (!isDatagramSocketStart)
		{
			try
			{
				ds = new DatagramSocket();
			}
			catch(Exception e) { e.printStackTrace(); }
			isDatagramSocketStart = true;
		}
	}
}

class ClientThreadReceive extends ClientThread implements Runnable
{
	Thread ct;
	
	ClientThreadReceive()
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
				String rawIPString = "localhost";
				//String rawIPString = "192.168.43.205";
				InetAddress addressOfServer = InetAddress.getByName(rawIPString);
				
				byte bR [] = new byte[1024];
				DatagramPacket dpR = new DatagramPacket(bR, 0, bR.length, addressOfServer, 7000);
				ds.receive(dpR);
				String strR = new String(bR);			
				
				String messageReceived = "";
				for (int i = 0; strR.charAt(i) != '\0'; i++)
					messageReceived += String.valueOf(strR.charAt(i));
				
				String str = "";
				str += messageReceived;
				ClientWindow.txtArea1.append(str);
				ClientWindow.txtArea1.append("\n");
			}
			catch(Exception e) { e.printStackTrace(); }
		}
	}
}

class ClientThreadSend extends ClientThread implements Runnable
{
	Thread ct;
	String messageToSend;
	
	ClientThreadSend(String t_messageToSend)
	{
		ct = new Thread(this);
		messageToSend = t_messageToSend;
		ct.start();
	}
	
	public void run()
	{
		try
		{
			String rawIPString = "localhost";
			//String rawIPString = "192.168.43.205";
			InetAddress addressOfServer = InetAddress.getByName(rawIPString);
			
			byte bS [] = messageToSend.getBytes();
			DatagramPacket dpS = new DatagramPacket(bS, 0, bS.length, addressOfServer, 7000);
			ds.send(dpS);
			
			String str = "";
			str = "Me: " + messageToSend + "\n";
			ClientWindow.txtArea1.append(str);
		}
		catch(Exception e) { e.printStackTrace(); }
	}
}

class EventHandling implements ActionListener
{
	public void actionPerformed(ActionEvent ae1)
	{
		String source = ae1.getActionCommand();
		if (source.equals("SEND"))
		{
			String messageToSend = "";
			messageToSend = MessageSendingPanel.txtField1.getText();
			MessageSendingPanel.txtField1.setText("");	

			ClientThreadSend cts = new ClientThreadSend(messageToSend);
		}
	}
}

class MessageSendingPanel extends JPanel
{
	JButton sendButton;
	static JTextField txtField1;
	EventHandling eh1;
	Font fVar1;
	
	MessageSendingPanel()
	{
		eh1 = new EventHandling();
		//JLabel label1 = new JLabel("Type a message");
		fVar1 = new Font("Times New Roman", Font.PLAIN, 20);
		
		this.setLayout(new FlowLayout());
		sendButton = new JButton("SEND");
		txtField1 = new JTextField(25);
		
		txtField1.setFont(fVar1);
		sendButton.setFont(fVar1);
		this.add(sendButton);
		this.add(txtField1);
		sendButton.addActionListener(eh1);
	}
}

class ClientWindow extends JFrame
{
	MessageSendingPanel msp;
	static JTextArea txtArea1;
	JScrollPane scroll1;
	
	ClientWindow ()
	{
		msp = new MessageSendingPanel();
		txtArea1 = new JTextArea();
		scroll1 = new JScrollPane(txtArea1);
		
		Font fontVar = new Font("Times New Roman", Font.PLAIN, 20);
		txtArea1.setFont(fontVar);
		
		// Layouting
		this.add(scroll1, BorderLayout.CENTER);
		this.add(msp, BorderLayout.SOUTH);
		
		// Starting Receiving message thread
		ClientThreadReceive ctr = new ClientThreadReceive();
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Client");
		this.setSize(1280, 720);
		this.setVisible(true);
	}
}
public class AssignmentNo13_Chatting_application_Client_R7
{
	public static void main(String args [])
	{
		ClientWindow cw1 = new ClientWindow();
	}
}
