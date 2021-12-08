import java.sql.*;
import java.util.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;

// javac Program.java
// java -cp .;Connector.jar Program

class EventHandling implements ActionListener
{
	static boolean isINSERT = false;
	static boolean isUPDATE = false;
	static boolean isDELETE = false;
	static boolean isConnected = false;
	static int recordCount;
	static int updateID = 0;

	DatabaseInputUI dbIUI1 = null;
	
	EventHandling() {};
	
	EventHandling(DatabaseInputUI tDbIUI)
	{
		dbIUI1 = tDbIUI;
	}
	
	void EnableDisableTextField(boolean value)
	{
		if (isINSERT && isUPDATE)
		{
			dbIUI1.txtField1[0].setEditable(value);
			dbIUI1.txtField1[1].setEditable(value);
			dbIUI1.txtField1[2].setEditable(value);
		}
		else if(isDELETE)
		{
			dbIUI1.txtField1[0].setEditable(value);
		}
		else
		{
			dbIUI1.txtField1[0].setEditable(value);
			dbIUI1.txtField1[1].setEditable(value);
			dbIUI1.txtField1[2].setEditable(value);
		}
	}
	
	void resetTextFields()
	{
		dbIUI1.txtField1[0].setText("");
		dbIUI1.txtField1[1].setText("");
		dbIUI1.txtField1[2].setText("");
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String source = ae.getActionCommand();
		
		if (source.equals("SAVE"))
		{
			EnableDisableTextField(false);
			
			int studentID = 0;
			String str1 = dbIUI1.txtField1[0].getText();
			
			try
			{
				studentID = Integer.parseInt(str1);
				String studentName = dbIUI1.txtField1[1].getText();
				String studentClass = dbIUI1.txtField1[2].getText();
				
				if(isINSERT)
				{
					try
					{
						String query = "insert into student values(" + studentID + ", \"" + studentName + "\", \"" + studentClass + "\")";
						WorkingWithDatabase.stmt1.executeUpdate(query);
						
						JFrame frame1 = new JFrame();
						JOptionPane.showMessageDialog(frame1, "Record is Successfully Inserted!");
					}
					catch(Exception e) 
					{ 
						JFrame frame1 = new JFrame();
						JOptionPane.showMessageDialog(frame1, "Change ID.\n" + e.getMessage());
					}		
					isINSERT = false;
				}
				else if(isUPDATE)
				{
					try
					{
						String query = "update student set id = " + studentID + 
										", name = \"" + studentName +"\", class = \"" + studentClass + "\" where id = " + updateID;
						WorkingWithDatabase.stmt1.executeUpdate(query);
						
						JFrame frame1 = new JFrame();
						JOptionPane.showMessageDialog(frame1, "Record is Successfully Updated!");
					}
					catch(Exception e) 
					{	
						e.printStackTrace();
					}	
					isUPDATE = false;
					updateID = 0;
				}
				else if(isDELETE)
				{
					try
					{
						String query = "delete from student where id = " + studentID;
						WorkingWithDatabase.stmt1.executeUpdate(query);
					}
					catch(Exception e) { e.printStackTrace(); }		
					isDELETE = false;
					
					JFrame frame1 = new JFrame();
					JOptionPane.showMessageDialog(frame1, "Record is successfully deleted.");
				}
			}
			catch(Exception e)
			{
				JFrame frame1 = new JFrame();
				JOptionPane.showMessageDialog(frame1, "Please enter a proper ID.");
			}
			resetTextFields();
			EnableDisableTextField(false);
		}
		else if (source.equals("CANCEL"))
		{
			resetTextFields();
			EnableDisableTextField(false);
		}
		else if (source.equals("INSERT"))
		{
			resetTextFields();
			EnableDisableTextField(true);
			isINSERT = true;
		}
		else if (source.equals("UPDATE"))
		{
			isUPDATE = true;
			try
			{
				updateID = Integer.parseInt(dbIUI1.txtField1[0].getText());
				EnableDisableTextField(true);
			}
			catch(Exception e)
			{
				JFrame frame1 = new JFrame();
				JOptionPane.showMessageDialog(frame1, "Record Not Found.");
				isUPDATE = false;
			}
		}
		else if (source.equals("DELETE"))
		{
			isDELETE = true;
			EnableDisableTextField(true);
		}		
		else if (source.equals("NEXT"))
		{
			recordCount++;
			
			try
			{
				String query = "select * from student";
				ResultSet rs1 = WorkingWithDatabase.stmt1.executeQuery(query);
				
				for (int i = 0; i < recordCount; i++)
					rs1.next();

				dbIUI1.txtField1[0].setText("" + rs1.getInt(1));
				dbIUI1.txtField1[1].setText(rs1.getString(2));
				dbIUI1.txtField1[2].setText(rs1.getString(3));
			}
			catch(Exception e)
			{ 
				JFrame frame1 = new JFrame();
						JOptionPane.showMessageDialog(frame1, "End of the table 'student'.\nClick PREVIOUS to go back to previous record.");
				if(recordCount > 0)
					recordCount--;
			}
		}
		else if (source.equals("PREVIOUS"))
		{
			if(recordCount > 1)
				recordCount--;
			try
			{
				String query = "select * from student";
				ResultSet rs1 = WorkingWithDatabase.stmt1.executeQuery(query);
				
				for (int i = 0; i < recordCount; i++)
					rs1.next();

				dbIUI1.txtField1[0].setText("" + rs1.getInt(1));
				dbIUI1.txtField1[1].setText(rs1.getString(2));
				dbIUI1.txtField1[2].setText(rs1.getString(3));
			}
			catch(Exception e) 
			{
				JFrame frame1 = new JFrame();
						JOptionPane.showMessageDialog(frame1, "Table is Empty.\nInsert a RECORD.");
			}
		}
	}
}

class DatabaseInputUI extends JPanel
{
	static JTextField txtField1 [] = new JTextField[3];
	JLabel label1 [] = new JLabel[3];
	EventHandling eh1 = null;
	
	DatabaseInputUI()
	{
		Font font1 = new Font("Times New Roman", Font.BOLD, 20);
		
		this.setLayout(new GridLayout(3, 1));
		//this.setLayout(new FlowLayout());
		
		JPanel panelForEachTxtFields0 = new JPanel();
		label1[0] = new JLabel("ID: ");
		txtField1[0] = new JTextField(20);
		txtField1[0].setFont(font1);
		txtField1[0].setEditable(false);
		
		panelForEachTxtFields0.setLayout(new FlowLayout());
		panelForEachTxtFields0.add(label1[0]);
		panelForEachTxtFields0.add(txtField1[0]);
		this.add(panelForEachTxtFields0);
		
		
		JPanel panelForEachTxtFields1 = new JPanel();
		label1[1] = new JLabel("Name: ");
		txtField1[1] = new JTextField(20);
		txtField1[1].setFont(font1);
		txtField1[1].setEditable(false);
		
		panelForEachTxtFields1.setLayout(new FlowLayout());
		panelForEachTxtFields1.add(label1[1]);
		panelForEachTxtFields1.add(txtField1[1]);
		this.add(panelForEachTxtFields1);
		
		
		JPanel panelForEachTxtFields2 = new JPanel();
		label1[2] = new JLabel("Class: ");
		txtField1[2] = new JTextField(20);
		txtField1[2].setFont(font1);
		txtField1[2].setEditable(false);
		
		panelForEachTxtFields2.setLayout(new FlowLayout());
		panelForEachTxtFields2.add(label1[2]);
		panelForEachTxtFields2.add(txtField1[2]);
		this.add(panelForEachTxtFields2);
		
		eh1 = new EventHandling(this);
	}
}

class DatabaseInputCommands extends JPanel
{
	JButton buttons1 [] = new JButton[2];
	EventHandling eh1;
	
	DatabaseInputCommands()
	{
		eh1 = new EventHandling();

		this.setLayout(new GridLayout(1, 2));
		//this.setLayout(new FlowLayout());
		
		buttons1[0] = new JButton("SAVE");
		this.add(buttons1[0]);
		buttons1[0].addActionListener(eh1);
		
		buttons1[1] = new JButton("CANCEL");
		this.add(buttons1[1]);
		buttons1[1].addActionListener(eh1);
	}
}

class DatabaseManipulation extends JPanel
{
	JButton buttons1 [] = new JButton[5];
	EventHandling eh1;
	
	DatabaseManipulation()
	{
		eh1 = new EventHandling();

		this.setLayout(new GridLayout(2, 3));
		
		buttons1[0] = new JButton("INSERT");
		this.add(buttons1[0]);
		buttons1[0].addActionListener(eh1);
		
		buttons1[1] = new JButton("UPDATE");
		this.add(buttons1[1]);
		buttons1[1].addActionListener(eh1);
		
		buttons1[2] = new JButton("DELETE");
		this.add(buttons1[2]);
		buttons1[2].addActionListener(eh1);
		
		buttons1[3] = new JButton("NEXT");
		this.add(buttons1[3]);
		buttons1[3].addActionListener(eh1);
		
		buttons1[4] = new JButton("PREVIOUS");
		this.add(buttons1[4]);
		buttons1[4].addActionListener(eh1);
	}
}

class DatabaseUI extends JFrame
{
	DatabaseInputUI dbIUI1;
	DatabaseInputCommands dbIC1;
	DatabaseManipulation dbM1;
	
	DatabaseUI()
	{
		dbIUI1 = new DatabaseInputUI();
		dbIC1 = new DatabaseInputCommands();
		dbM1 = new DatabaseManipulation();
		
		this.setLayout(new GridLayout(3, 1));
		this.add(dbIUI1);
		this.add(dbIC1);
		this.add(dbM1);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Database");
		this.setSize(640, 480);
		this.setVisible(true);
	}
}

class WorkingWithDatabase implements Runnable
{
	static Connection connection1 = null;
	static Statement stmt1;
	static boolean isDatabaseReady = false;
	Thread t;
	
	WorkingWithDatabase()
	{
		t = new Thread(this);
	}
		
	public void run()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			//Class.forName("oracle.jdbc.driver.OracleDriver");

			String str1 = "jdbc:mysql://localhost:3306/?useSSL=false&allowPublicKeyRetrieval=true";
			
			connection1 = DriverManager.getConnection(str1, "root", "aditya12345");
			stmt1 = connection1.createStatement();
			
			try
			{
				String query = "create database studentr7";
				stmt1.executeUpdate(query);
			}
			catch(Exception e) {}
			
			try
			{
				String query = "use studentr7";
				stmt1.executeUpdate(query);
				query = "create table student (id int(10), name varchar(35), class varchar(30))";
				stmt1.executeUpdate(query);
				query = "alter table student modify id int not null primary key";
				stmt1.executeUpdate(query);
			}
			catch(Exception e) {}
			
			isDatabaseReady = true;
		}
		catch(Exception e) 
		{
			System.out.println(e.getMessage());
		}
	}
}

public class AssignmentNo13_Database_R7
{
	public static void main(String args [])
	{
		WorkingWithDatabase wwd1 = new WorkingWithDatabase();
		wwd1.t.start();
		try
		{
			wwd1.t.join();
		}
		catch(Exception e) { e.printStackTrace(); }
		
		DatabaseUI dUI1;
		if(WorkingWithDatabase.isDatabaseReady)
			dUI1 = new DatabaseUI();
	}
}
