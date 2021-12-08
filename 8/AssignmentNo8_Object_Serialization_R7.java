import java.util.Scanner;
import java.io.*;

class Person implements Serializable
{
	private int id;
	private String name;

	public void setData(int t_id, String t_name)
	{
		id = t_id;
		name = t_name;
	}
	
	public int get_id() { return id; }
	
	public void displayPerson()
	{
		System.out.println("\nID = " + id);
		System.out.println("Name = " + name + "\n");
	}
}

public class AssignmentNo8_Object_Serialization_R7
{
	public static void main(String args [])
	{
		FileOutputStream fOut = null;
		ObjectOutputStream oOut = null;
		
		
		// Coping "record.dat" into temp and performing operations
		try
		{
			fOut = new FileOutputStream("temp.dat");
			oOut = new ObjectOutputStream(fOut);
			
			FileInputStream fin = null;
			ObjectInputStream oIn = null;
			
			try
			{
				 fin = new FileInputStream("record.dat");
				 oIn = new ObjectInputStream(fin);
				
				Person pObject = new Person();
				
				try
				{
					while (true)
					{
						pObject = (Person)oIn.readObject();
						oOut.writeObject(pObject);
					}
				}
				catch (Exception e) {}
				
			}
			catch (Exception e) {}
			finally 
			{
				try
				{
					oIn.close();
					fin.close();
				}
				catch (Exception e) {}
				finally {}
			}
			
			
			// Main code here	
			int choice1 = 0;
			do
			{
				System.out.println("\n");
				System.out.println("1. Add Record");
				System.out.println("2. Display Record");
				System.out.println("Press any other number than above to EXIT");
				System.out.print("\nEnter the choice: ");
				choice1 = new Scanner(System.in).nextInt();
				
				if (choice1 == 1)
				{
					Person pObject = new Person();		
		
					// Reading the data from user
					int id;
					String name;
					
					System.out.print("\nEnter the id: ");
					id = new Scanner(System.in).nextInt();
					
					System.out.print("Enter the name: ");
					name = new Scanner(System.in).next();
					
					pObject.setData(id, name);
					
					try
					{
						oOut.writeObject(pObject);
					}
					catch (Exception e) { e.printStackTrace(); }
				}
				else if (choice1 == 2)
				{
					int choice2 = 0;
					do
					{
						System.out.println("\n");
						System.out.println("1. Display all records");
						System.out.println("2. Enter id of Person to display");
						System.out.println("Press any other number than above to go back to PREVIOUS MENU");
						System.out.print("\nEnter the choice: ");
						choice2 = new Scanner(System.in).nextInt();

						if (choice2 == 1)
						{
							fileReading(1);
						}
						else if (choice2 == 2)
						{
							fileReading(2);
						}
					}
					while(choice2 >= 1 && choice2 <= 2);
				}
				else
				{
					System.out.println("\nGOODBYE!!");
				}
			}
			while (choice1 >= 1 && choice1 <= 2);
			
		}
		catch (Exception e) { e.printStackTrace(); }
		finally 
		{
			try
			{
				oOut.close();
				fOut.close();
			}
			catch (Exception e) { e.printStackTrace(); }
		}
		
		
		// Coping "temp.dat" into "record.dat"
		copyFile("record.dat", "temp.dat");
		
		// Deleting the temp file
		deleteFile("temp.dat");
	}
	
	public static void deleteFile(String s1)
	{
		try
		{
			File file = new File(s1);
			try
			{
				file.delete();
			}
			catch(Exception e) { e.printStackTrace(); }
		}
		catch(Exception e) { e.printStackTrace(); }
	}
	public static void fileReading(int choice)
	{
		FileInputStream fIn = null;
		ObjectInputStream oIn = null;
		
		// Read all records
		if (choice == 1)
		{
			try
			{
				fIn = new FileInputStream("temp.dat");
				oIn = new ObjectInputStream(fIn);
								
				Person pObject = new Person();

				try
				{
					while (true)
					{
						pObject = (Person)oIn.readObject();
						pObject.displayPerson();
					}
				}
				catch (Exception e) {}
			}
			catch (Exception e) { e.printStackTrace(); }
			finally 
			{
				try
				{
					oIn.close();
					fIn.close();
				}
				catch (Exception e) { e.printStackTrace(); }
			}
		}
		
		// Records matching the Id
		else if (choice == 2)
		{
			int id;
			boolean flag1 = false;
							
			System.out.print("\nEnter the id to show record: ");
			id = new Scanner(System.in).nextInt();
							
			try
			{
				fIn = new FileInputStream("temp.dat");
				oIn = new ObjectInputStream(fIn);
								
				Person pObject = new Person();
								
				try
				{
					while (true)
					{
						pObject = (Person)oIn.readObject();
						if (id == pObject.get_id())
						{
							pObject.displayPerson();
							flag1 = true;
						}
					}
				}
				catch (Exception e) {}
								
				if (!flag1) System.out.println("\nRecord matching the given id is not present in the file.");
								
			}
			catch (Exception e) { e.printStackTrace(); }
			finally 
			{
				try
				{
					oIn.close();
					fIn.close();
				}
					catch (Exception e) { e.printStackTrace(); }
			}
		}
	}
	
	public static void copyFile(String s1, String s2)
	{
		FileOutputStream fOut = null;
		ObjectOutputStream oOut = null;
		try
		{
			fOut = new FileOutputStream(s1);
			oOut = new ObjectOutputStream(fOut);

			FileInputStream fIn = null;
			ObjectInputStream oIn = null;
			
			try
			{
				fIn = new FileInputStream(s2);
				oIn = new ObjectInputStream(fIn);
				
				Person pObject = new Person();

				try
				{
					while (true)
					{
						pObject = (Person)oIn.readObject();
						oOut.writeObject(pObject);
					}
				}
				catch (Exception e) {}
				
			}
			catch (Exception e) { e.printStackTrace(); }
			finally 
			{
				try
				{
					oIn.close();
					fIn.close();
				}
				catch (Exception e) { e.printStackTrace(); }
			}
		}
		catch (Exception e) { e.printStackTrace(); }
		finally
		{
			try
			{
				oOut.close();
				fOut.close();
			}
			catch (Exception e) { e.printStackTrace(); }
		}
	}
}