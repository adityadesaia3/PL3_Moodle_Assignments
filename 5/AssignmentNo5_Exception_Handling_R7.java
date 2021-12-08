import java.util.*;

class CheckNumberString
{
	int checkWord(String str1)
	{
		try
		{
			if (str1.equals("zero")) return 0;
			else if (str1.equals("one")) return 1;
			else if (str1.equals("two")) return 2;
			else if (str1.equals("three")) return 3;
			else if (str1.equals("four")) return 4;
			else if (str1.equals("five")) return 5;
			else if (str1.equals("six")) return 6;
			else if (str1.equals("seven")) return 7;
			else if (str1.equals("eight")) return 8;
			else if (str1.equals("nine")) return 9;
			else if (str1.equals("ten")) return 10;
			else if (str1.equals("eleven")) return 11;
			else if (str1.equals("twelve")) return 12;
			else if (str1.equals("thirteen")) return 13;
			else if (str1.equals("fourteen")) return 14;
			else if (str1.equals("fifteen")) return 15;
			else if (str1.equals("sixteen")) return 16;
			else if (str1.equals("seventeen")) return 17;
			else if (str1.equals("eighteen")) return 18;
			else if (str1.equals("nineteen")) return 19;
			else if (str1.equals("twenty")) return 20;
			else if (str1.equals("thirty")) return 30;
			else if (str1.equals("forty")) return 40;
			else if (str1.equals("fifty")) return 50;
			else if (str1.equals("sixty")) return 60;
			else if (str1.equals("seventy")) return 70;
			else if (str1.equals("eighty")) return 80;
			else if (str1.equals("ninety")) return 90;
			else 
				throw new InputMismatchException();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new InputMismatchException();
		}
	}
}

class AssignmentNo5_Exception_Handling_R7
{
	public static void main(String args [])
	{
		while (true)
		{
			int value = 0;

			boolean flag = true;

			System.out.print("\nEnter the number in words: ");
			String sInput = new Scanner(System.in).nextLine();

			StringTokenizer stToken1 = new StringTokenizer(sInput, " ");

			CheckNumberString cNSObject = new CheckNumberString();

			System.out.println();

			while(stToken1.hasMoreTokens())
			{
				try
				{
					value = value + cNSObject.checkWord(stToken1.nextToken());
				}
				catch(Exception e)
				{
					flag = false;
				}
				finally
				{
					if (!flag) 
					{
						value = 0;
						break;
					}
				}
			}

			if (flag)
			{
				System.out.print("\nNumerical equivalent of inputed string: ");
				System.out.println(value);
				System.out.println();
			}
			else
				System.out.println("\nDefault value: 0\n");
		}
	}
}




























