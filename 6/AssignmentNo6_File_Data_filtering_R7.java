import java.util.*;
import java.io.*;

class AssignmentNo6_File_Data_filtering_R7
{
	static void outputFile(String str1)
	{
		StringTokenizer stStr = new StringTokenizer(str1, " ");
		int array [] = new int[100];
		int count = 0;
		
		while (stStr.hasMoreTokens())
		{
			try
			{
				array[count] = Integer.parseInt(stStr.nextToken());
				count++;
			}
			catch (Exception e)	{}
		}
	
		FileWriter fw1 = null;
		BufferedWriter bw1 = null;
	
		try 
		{
			fw1 = new FileWriter("outputFile_R7.txt");
			bw1 = new BufferedWriter(fw1);

			for (int i = 0; i < count; i++)
				bw1.write(array[i] + " ");
			bw1.flush();
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
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
	}
	
	static void fileReading(String fileName)
	{
		FileReader fr1 = null;
		BufferedReader br1 = null;
		try 
		{
			fr1 = new FileReader(fileName);
			br1 = new BufferedReader(fr1);
			
			String str1 = "";
			
			while ((str1 = br1.readLine()) != null)
				outputFile(str1);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
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
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
	}
	
	public static void main(String args [])
	{
		fileReading("a.txt");
	}
}