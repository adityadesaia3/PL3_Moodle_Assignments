import java.util.*;
import java.lang.*;
import java.io.*;
import java.net.*;

public class AssignmentNo12_File_dowload_Server_TCP_Client_R7
{
	public static void fileWriting(String fileName, String contentToWrite)
	{
		FileWriter fw1 = null;
		BufferedWriter bw1 = null;

		// Creating new file
		try
		{
			fw1 = new FileWriter(fileName);
			bw1 = new BufferedWriter(fw1);
				
			bw1.write(contentToWrite);
			bw1.flush();
			
			System.out.println("\nFile is successfully Downloaded.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (bw1 != null)
					bw1.close();
				if (fw1 != null)
						fw1.close();
			}
				catch (Exception e){}
		}
	}

	public static void main(String args [])
	{
		String contentToWrite = "";
		String fileName = "";
		
		Socket client1;
		
		try
		{
			client1 = new Socket("localhost", 5100);
			
			boolean isFileExits = true;
			
			BufferedInputStream bin = new BufferedInputStream(client1.getInputStream());
			BufferedOutputStream bout = new BufferedOutputStream(client1.getOutputStream());
			
			System.out.println("LIST OF FILES TO DOWNLOAD FROM SERVER: \n");
			try
			{
				byte b [] = new byte[2048];
				bin.read(b);
				
				String str = new String(b);
				for (int j = 0; str.charAt(j) != '\0'; j++)
					System.out.print(str.charAt(j));
			}
			catch(Exception e) {}

			try
			{
				System.out.print("\nEnter the file's name to download (Accurately): ");
				fileName = new Scanner(System.in).next();
				
				byte b [] = fileName.getBytes();
				
				bout.write(b);
				bout.flush();
			}
			catch(Exception e) {}
			
			try
			{
				byte b [] = new byte[8192];
				
				bin.read(b);
				
				String str = new String(b);

				for (int i = 0; str.charAt(i) != '\0'; i++)
					contentToWrite += String.valueOf(str.charAt(i));
				
				if (contentToWrite.equals("File doesn't exit.\n"))
				{
					System.out.println("File doesn't exit.");
					isFileExits = false;
				}
				//else 
				//	System.out.println("FileContent: " + contentToWrite);
			}
			catch(Exception e) {}

			if (isFileExits)
				fileWriting(fileName, contentToWrite);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
