import java.util.Scanner;
class AssignmentNo2_Inheritance_R7
{
	public static void main(String args[])
	{
		Scanner scanner = new Scanner(System.in);
		Person pObject [] = new Person[100];
		int iD = 1;
		int pIndex = 0;
		int mCount = 0;
	
		boolean flag1 = true;
		boolean flag2 = true;
		int choice = 0;

		do 
		{
			System.out.println("\n1. Create");
			System.out.println("2. Display");
			System.out.println("Any integer other than above to EXIT");
			System.out.println();
			System.out.print("Enter the choice: ");
			choice = scanner.nextInt();
			System.out.println();

			if (choice < 1 || choice > 2)
				flag1 = false;
			else if (choice == 1)
			{
				flag2 = true;
				do
				{
					String personName;	// Person
					String post;		// Employee
					int salary;			// Employee
					int managerID;		// Employee
					String department; 	// Manager


					System.out.println("\n1. Create Employee");
					System.out.println("2. Create Manager");
					System.out.println("Any integer other than above to go in PREVIOUS MENU");
					System.out.println();
					System.out.print("Enter the choice: ");
					
					choice = scanner.nextInt();
					System.out.println();

					if(choice < 1 || choice > 2)
						flag2 = false;
					else if (choice == 1)
					{
						
						System.out.println("Number of Managers starting from 1: " + mCount);
						if (mCount == 0)
						{
							System.out.println("Manager not available. Cannot create Employee!");
						}	
						else
						{
							Employee eObject = new Employee();

							System.out.print("\nEnter name of the employee: ");
							personName = scanner.next();

							System.out.print("Enter post of the employee: ");
							post = scanner.next();

							System.out.print("Enter salary of the employee: ");
							salary = scanner.nextInt();

							System.out.print("Enter manager ID of the employee: ");
							managerID = scanner.nextInt();
							
							eObject.setEmployeeData(iD++, personName, post, salary, managerID);
							pObject[pIndex++] = eObject;
						}
					}
					else if (choice == 2)
					{
						Manager mObject = new Manager();

						System.out.print("\nEnter name of the manager: ");
						personName = scanner.next();

						System.out.print("Enter post of the manager: ");
						post = scanner.next();

						System.out.print("Enter salary of the manager: ");
						salary = scanner.nextInt();

						managerID = 0;

						System.out.print("Enter department of the manager: ");
						department = scanner.next();
						
						mObject.setManagerData(iD++, personName, post, salary, managerID, department);
						pObject[pIndex++] = mObject;
						mCount++;
					}
				}
				while (flag2);
			}
			else if (choice == 2)
			{
				flag2 = true;
				do
				{
					System.out.println("\n1. Display Employees");
					System.out.println("2. Display Managers");
					System.out.println("3. Display Employees under Manager");
					System.out.println("Any integer other than above to go in PREVIOUS MENU");
					System.out.println();
					System.out.print("Enter the choice: ");
					
					choice = scanner.nextInt();
					System.out.println();


					if(choice < 1 || choice > 3)
						flag2 = false;
					else if (choice == 1)
					{
						System.out.println("\n*** Displaying Employees ***\n");
						for (int x = 0; x < pIndex; x++)
						{
							if ((pObject[x] instanceof Employee))
							{
								Employee eObject = (Employee)pObject[x];
								//if (!eObject.get_isManager())
								eObject.displayEmployee();
								System.out.println();
							}
						}
						System.out.println("\n");
					}
					else if (choice == 2)
					{
						System.out.println("\n*** Displaying Managers ***\n");
						for (int x = 0; x < pIndex; x++)
						{
							if ((pObject[x] instanceof Manager))
							{
								Manager mObject = (Manager)pObject[x];
								if (mObject.get_isManager())	
								{
									mObject.displayManager();
									System.out.println();
								}
							}
						}
						System.out.println("\n");
					}
					else if (choice == 3)
					{
						System.out.println("\nNumber of Managers: " + mCount);
						System.out.println("Enter the manager's identification below to display employees under him.");
						int managerID = scanner.nextInt();
						
						System.out.println("\n*** Displaying Employees under Manager " + managerID +" ***\n");
						for (int x = 0; x < pIndex; x++)
						{
							if (pObject[x] instanceof Employee)
							{
								Employee eObject = (Employee)pObject[x];
								if (!eObject.get_isManager() && managerID == eObject.get_managerID())
								{
									eObject.displayEmployee();
									System.out.println();
								}
							}
						}
						System.out.println("\n");
					}
				}
				while (flag2);
			}			
			
		}
		while (flag1);

		scanner.close();
	}
}
class Person
{
	private int iD;
	private String name;
	
	public void setPersonData(int t_iD, String t_name)
	{
		iD = t_iD;
		name = t_name;
	}
	public void displayPerson()
	{
		System.out.println("Identification: " + iD);
		System.out.println("Name: " + name);
	}

}
class Employee extends Person
{
	private String post;
	private int salary;
	private int managerID;
	private boolean isManager;
	
	public void setEmployeeData(int t_iD, String t_name, String t_post, int t_salary, int t_managerID)
	{
		setPersonData(t_iD, t_name);
		post = t_post;
		salary = t_salary;
		managerID = t_managerID;

		if (managerID != 0)
			isManager = false;
		else if (managerID == 0)
			isManager = true;
	}
	public boolean get_isManager() { return isManager; }
	public int get_managerID() { return managerID; }
	public void displayEmployee()
	{
		displayPerson();
		System.out.println("Post: " + post);
		System.out.println("salary: " + salary);
		System.out.println("managerID: " + managerID);
	}


}
class Manager extends Employee
{
	private String department;
	
	public void setManagerData(int t_iD, String t_name, String t_post, int t_salary, int t_managerID, String t_department)
	{
		setEmployeeData(t_iD, t_name, t_post, t_salary, t_managerID);
		department = t_department;
	}

	public void displayManager()
	{
		displayEmployee();
		System.out.println("Department: " + department);
	}
}