import java.util.*;
import java.io.*;

public class AssignmentNo_7_Appending_Data_to_File_R7
{
	public static void fileReading(String fileName)
	{
		FileReader fr1 = null;
		BufferedReader br1 = null;
		
		try
		{	
			fr1 = new FileReader(fileName);
			br1 = new BufferedReader(fr1);
			
			String str1 = "";
			
			while ((str1 = br1.readLine()) != null) 
					System.out.println(str1);
			
			fileWriting(fileName, true);
		}
		catch (Exception e)
		{
			System.out.println("\nFile doesn't exit.");
			System.out.println("Creating a new file!\n");
			
			fileWriting(fileName, false);
			
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
	}
	
	public static void fileWriting(String fileName, boolean bl1)
	{
		FileWriter fw1 = null;
		PrintWriter pw1 = null;
		
		String choice1;
		do
		{
			System.out.println("\nDo you want to add data? (yes / no)");
			choice1 = new Scanner(System.in).next();
		} while (!(choice1.equals("yes")) && !(choice1.equals("no")));
		
		if (bl1 == false && choice1.equals("yes"))
		{
			// Creating new file
			try
			{
				fw1 = new FileWriter(fileName, true);
				pw1 = new PrintWriter(fw1);
				
				String str1 = "";
				System.out.println("Enter the data: ");
				do
				{
					str1 = new Scanner(System.in).nextLine();
					
					if (str1.equals("finish")) break;
					
					CharSequence cs1 = str1;
					
					pw1.append(cs1);
					pw1.append('\n');
					
				} while(!(str1.equals("finish")));
				pw1.flush();
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					if (pw1 != null)
						pw1.close();
					if (fw1 != null)
						fw1.close();
				}
				catch (Exception e){}
			}
		}
			
		if (choice1.equals("yes") && bl1 == true)
		{
			try
			{
				// writing to an existing file
				fw1 = new FileWriter(fileName, true);
				pw1 = new PrintWriter(fw1);
				
				String str1 = "";
				System.out.println("Enter the data: ");
				do
				{
					str1 = new Scanner(System.in).nextLine();
					
					if (str1.equals("finish")) break;
					
					CharSequence cs1 = str1;
					
					pw1.append(cs1);
					pw1.append('\n');
					
				} while(!(str1.equals("finish")));
				pw1.flush();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					if (pw1 != null)
						pw1.close();
					if (fw1 != null)
						fw1.close();
				}
				catch (Exception e){}
			}
		}
		else if (choice1.equals("no")) return;
		
	}
	public static void main(String args [])
	{
		System.out.println("\nGuidelines:");
		System.out.println("1. Give the file name with .txt extension.");
		System.out.println("2. If given file doesn't exist, the program will create a new file for you.");
		System.out.println("3. You must add data to newly created file to save the file otherwise it will not be saved.");
		System.out.println("4. Enter the 'finish' word in small letters only to stop inputing the data.\n\n");
		
		System.out.print("Enter the file name: ");
		String fileName = new Scanner(System.in).next();
		fileReading(fileName);
	}
}