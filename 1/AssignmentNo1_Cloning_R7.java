// import java.util.Scanner;
class Student implements Cloneable
{
	public int rollNumber;
	public int size_iArray;
	public int iArray[];

	Student() 
	{
		iArray = new int[5];
	}
	Student(int t_rollNumber, int t_size_iArray, int [] t_iArray)
	{
		rollNumber = t_rollNumber;
		size_iArray = t_size_iArray;
		for (int i = 0; i < size_iArray; i++)
			iArray[i] = t_iArray[i];
	}	
	public Student clone() throws CloneNotSupportedException
	{
		Student temp = (Student)super.clone();
		temp.iArray = this.iArray.clone();
		return temp;
	}
}
public class AssignmentNo1_Cloning_R7
{
	public static void main(String args[]) throws Exception
	{
		Student dCVariable1 = new Student();
		dCVariable1.rollNumber = 7;
		dCVariable1.size_iArray = 5;

		for (int j = 0; j < dCVariable1.size_iArray; j++)
			dCVariable1.iArray[j] = 100;


		// Displaying dCVariable1
		System.out.println("\nIntial contents of dCVariable1");
		System.out.println("\ndCVariable1's Roll Number: " + dCVariable1.rollNumber);
		System.out.print("dCVariable1's array: ");
		for (int j = 0; j < dCVariable1.size_iArray; j++)
			System.out.print(dCVariable1.iArray[j] + " ");
		System.out.println("\n");

		Student dCVariable2 = dCVariable1.clone();

		// Displaying dCVariable2
		System.out.println("\nIntial contents of dCVariable2");
		System.out.println("\ndCVariable2's Roll Number: " + dCVariable2.rollNumber);
		System.out.print("dCVariable2's array: ");
		for (int j = 0; j < dCVariable2.size_iArray; j++)
			System.out.print(dCVariable2.iArray[j] + " ");
		System.out.println("\n");

		System.out.println("\n\nChanging dCVariable1.iArray[3] to 45 and rollNumber to 17");
		dCVariable1.iArray[3] = 45;
		dCVariable1.rollNumber = 17;

		// Displaying dCVariable1 after change1
		System.out.println("\ndCVariable1's Roll Number: " + dCVariable1.rollNumber);
		System.out.print("dCVariable1's array: ");
		for (int j = 0; j < dCVariable1.size_iArray; j++)
			System.out.print(dCVariable1.iArray[j] + " ");
		System.out.println("\n");


		// Displaying dCVariable2 after change1
		System.out.println("\ndCVariable2's Roll Number: " + dCVariable2.rollNumber);
		System.out.print("dCVariable2's array: ");
		for (int j = 0; j < dCVariable2.size_iArray; j++)
			System.out.print(dCVariable2.iArray[j] + " ");
		System.out.println("\n");

		System.out.println("\n\nChanging dCVariable2.iArray[2] to 90 and rollNumber to 49");
		dCVariable2.iArray[2] = 90;
		dCVariable2.rollNumber = 49;

		// Displaying dCVariable1 after change2
		System.out.println("\ndCVariable1's Roll Number: " + dCVariable1.rollNumber);
		System.out.print("dCVariable1's array: ");
		for (int j = 0; j < dCVariable1.size_iArray; j++)
			System.out.print(dCVariable1.iArray[j] + " ");
		System.out.println("\n");


		// Displaying dCVariable2 after change2
		System.out.println("\ndCVariable2's Roll Number: " + dCVariable2.rollNumber);
		System.out.print("dCVariable2's array: ");
		for (int j = 0; j < dCVariable2.size_iArray; j++)
			System.out.print(dCVariable2.iArray[j] + " ");
		System.out.println("\n");
	}

}
