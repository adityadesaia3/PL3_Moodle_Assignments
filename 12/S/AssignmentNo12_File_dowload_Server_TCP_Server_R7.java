import java.util.*;
import java.lang.*;
import java.io.*;
import java.net.*;
import javax.swing.filechooser.*;

class ImplementingThreading implements Runnable
{	
	Socket client;	
	Thread ts;

	ImplementingThreading(Socket tClient)
	{
		client = tClient;
		ts = new Thread(this);
	}

	public static String fileReading(String fileName)
	{
		FileReader fr1 = null;
		BufferedReader br1 = null;
		String wholeFileString = "";
		
		try
		{
			System.out.println("\nFile requested by Client: " + fileName);
			fr1 = new FileReader(fileName);
			br1 = new BufferedReader(fr1);
			
			String str1 = "";
			
			while ((str1 = br1.readLine()) != null) 
			{
				wholeFileString += str1;
				wholeFileString += "\n";
			}
			System.out.println("File sent.");
		}
		catch (Exception e)
		{
			System.out.println("File doesn't exit.");
			wholeFileString += "File doesn't exit.\n";
		}
		finally
		{
			try
			{
				if (br1 != null)
					br1.close();
				if (fr1 != null)
					fr1.close();
			}
			catch (Exception e){}
		}
		
		return wholeFileString;
	}
	
	public void run()
	{
		String fileName = "";
		String wholeFileString = "";
		Vector<String> fileNames = new Vector<String>();
		
		try
		{
			String currentFolderPath = System.getProperty("user.dir");
			File accessCurrentFolder = new File(currentFolderPath);
			
			File [] listOfTextFiles = accessCurrentFolder.listFiles();
			
			for (int i = 0; i < listOfTextFiles.length; i++)
			{
				if (listOfTextFiles[i].isFile())
				{
					if(listOfTextFiles[i].getName().endsWith(".txt")
						|| listOfTextFiles[i].getName().endsWith(".java")
						|| listOfTextFiles[i].getName().endsWith(".cpp")
						|| listOfTextFiles[i].getName().endsWith(".c"))
					{	
						fileNames.add(listOfTextFiles[i].getName());
					}
				}
			}
		}
		catch(Exception e) { e.printStackTrace(); }
		
		
		try
		{
			BufferedOutputStream bout = new BufferedOutputStream(client.getOutputStream());
			
			for (int i = 0; i < fileNames.size(); i++)
			{
				try
				{
					String str = "" + (i + 1) + ". " + fileNames.elementAt(i) + "\n";
				
					byte [] b = str.getBytes();		
					bout.write(b);
				}
				catch(Exception e) { e.printStackTrace(); }
			}
			bout.flush();
			
			try
			{
				BufferedInputStream bin = new BufferedInputStream(client.getInputStream());
				byte b [] = new byte[128];
				
				bin.read(b);
				String str = new String(b);	
				
				for (int i = 0; str.charAt(i) != '\0'; i++)
					fileName += String.valueOf(str.charAt(i));
			}
			catch(Exception e) { e.printStackTrace(); }
		

			wholeFileString = fileReading(fileName);			

			
			try
			{
				byte b[] = wholeFileString.getBytes();		
				bout.write(b);
			}
			catch(Exception e) {}
			bout.flush();
		
			client.close();
		}
		catch(Exception e) { e.printStackTrace(); }

	}
}

public class AssignmentNo12_File_dowload_Server_TCP_Server_R7
{
	public static void main(String args [])
	{
		try
		{
			ServerSocket ss1 = new ServerSocket(5100);
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
