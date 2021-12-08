import java.util.Scanner;
import java.math.*;

interface TwoWheel
{
	int wheels = 2;
}

interface FourWheel
{
	int wheels = 4;
}

abstract class Vehicle
{
	abstract int noOfWheels();
	abstract int cubicCapacity();
}

class Bike extends Vehicle implements TwoWheel
{
	private int bikeNumber;
	private int numberOfCylinders;
	private int boreDiameter;
	private int strokeLength;
	
	void setData(int t_totalBikes, int t_numberOfCylinders, int t_boreDiameter, int t_strokeLength)
	{
			bikeNumber = t_totalBikes;
			numberOfCylinders = t_numberOfCylinders;
			boreDiameter = t_boreDiameter;
			strokeLength = t_strokeLength;
	}
	
	int get_bikeNumber() { return bikeNumber; }
	
	int noOfWheels()
	{
		return wheels;
	}
	
	int cubicCapacity()
	{
		int volume = (int)(((Math.PI) / 4) * (Math.pow(boreDiameter * 1.0, 2.0)) * (strokeLength) * (numberOfCylinders));
		return volume;
	}
}

class Car extends Vehicle implements FourWheel
{
	private int carNumber;
	private int numberOfCylinders;
	private int boreDiameter;
	private int strokeLength;
	
	void setData(int t_totalCars, int t_numberOfCylinders, int t_boreDiameter, int t_strokeLength)
	{
			carNumber = t_totalCars;
			numberOfCylinders = t_numberOfCylinders;
			boreDiameter = t_boreDiameter;
			strokeLength = t_strokeLength;
	}
	
	int get_carNumber() { return carNumber; }
	
	int noOfWheels()
	{
		return wheels;
	}
	
	int cubicCapacity()
	{
		int volume = (int)(((Math.PI) / 4) * (Math.pow(boreDiameter * 1.0, 2.0)) * (strokeLength) * (numberOfCylinders));
		return volume;
	}
}

public class AssignmentNo4_interface_and_abstract_class_R7
{
	public static void main(String args[])
	{
		Vehicle vArray [] = new Vehicle[32];
		int vArrayIndex = 0;
		
		int totalBikes = 0;
		int totalCars = 0;
		
		int choice1 = 0;
		boolean flag1 = false;
		do
		{
			System.out.println();
			System.out.println("1. Create Bike");
			System.out.println("2. Create Car");
			System.out.println("3. Display All Bikes");
			System.out.println("4. Display All Cars");
			System.out.println("Choose any number other than above to EXIT.");
			System.out.println("\nEnter the choice: ");
			choice1 = new Scanner(System.in).nextInt();
			System.out.println("\n");
			
			if (choice1 >= 1 && choice1 <= 4)
				flag1 = true;
			else
				flag1 = false;
			
			if (choice1 == 1)
			{
				Bike bObject = new Bike();
				totalBikes++;
				
				System.out.print("Enter the number of cylinders: ");
				int numberOfCylinders = new Scanner(System.in).nextInt();
				System.out.print("Enter the bore diameter in cm: ");
				int boreDiameter = new Scanner(System.in).nextInt();
				System.out.print("Enter the stroke length in cm: ");
				int strokeLength = new Scanner(System.in).nextInt();
				
				bObject.setData(totalBikes, numberOfCylinders, boreDiameter, strokeLength);
				vArray[vArrayIndex++] = bObject;
			}
			else if (choice1 == 2)
			{
				Car cObject = new Car();
				totalCars++;
				
				System.out.print("Enter the number of cylinders: ");
				int numberOfCylinders = new Scanner(System.in).nextInt();
				System.out.print("Enter the bore diameter in cm: ");
				int boreDiameter = new Scanner(System.in).nextInt();
				System.out.print("Enter the stroke length in cm: ");
				int strokeLength = new Scanner(System.in).nextInt();
				
				cObject.setData(totalCars, numberOfCylinders, boreDiameter, strokeLength);
				vArray[vArrayIndex++] = cObject;				
			}
			else if (choice1 == 3)
			{
				System.out.println("\n***** Displaying Bikes *****\n");
				for (int i = 0; i < vArrayIndex; i++)
				{
					if (vArray[i] instanceof Bike)
					{
						Bike bObject = (Bike)vArray[i];
						System.out.println("\nBike Number: " + bObject.get_bikeNumber());
						System.out.println("Number of Wheels: " + bObject.noOfWheels());
						System.out.println("Cubic Capacity: " + bObject.cubicCapacity() + " cc");
						System.out.println();
					}
				}
			}
			else if (choice1 == 4)
			{
				System.out.println("\n***** Displaying Cars *****\n");
				for (int i = 0; i < vArrayIndex; i++)
				{
					if (vArray[i] instanceof Car)
					{
						Car cObject = (Car)vArray[i];
						System.out.println("\nCar Number: " + cObject.get_carNumber());
						System.out.println("Number of Wheels: " + cObject.noOfWheels());
						System.out.println("Cubic Capacity: " + cObject.cubicCapacity() + " cc");
						System.out.println();
					}
				}				
			}
		
		} while (flag1);
	}
}
