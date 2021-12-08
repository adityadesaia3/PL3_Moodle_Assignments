import java.util.*;
import java.lang.Math.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

class EventHandling implements ActionListener
{
	String value;
	static String tempBuffer = "";
	static double operand1 = 0, operand2 = 0;
	static boolean operation = false, firstOperation = true;
	static char previousOperation;
	
	Calculator cal;
	
	EventHandling () {}
	
	EventHandling(Calculator t_cal)
	{
		cal = t_cal;
	}
	
	public void actionPerformed (ActionEvent ae1)
	{
		String display = "";
		String source = ae1.getActionCommand();
		
		try 
		{
			if (source.equals("ADD ( + )"))
			{
				tempBuffer = cal.txtField2.getText();
				tempBuffer += "+";
				cal.txtField2.setText(tempBuffer);
				
				value = cal.txtField1.getText();
				double temp = Double.parseDouble(value);
				
				if(firstOperation)
				{
					operand1 = temp;
					firstOperation = false;
					previousOperation = '+';
				}
				else 
				{
					if (previousOperation == '+') 
					{
						operand1 = operand1 + temp;
						previousOperation = '+';		
					}
					else if (previousOperation == '-')
					{
						operand1 = operand1 - temp;
						previousOperation = '+';
					}
					else if (previousOperation == '/') 
					{
						operand1 = operand1 / temp;
						previousOperation = '+';
					}
					else if (previousOperation == '*')
					{
						operand1 = operand1 * temp;
						previousOperation = '+';
					}
				}
				
				operation = true;
				
				display = display + operand1;
				cal.txtField1.setText(display);
			}
			else if (source.equals("SUB ( - )"))
			{
				tempBuffer = cal.txtField2.getText();
				tempBuffer += "-";
				cal.txtField2.setText(tempBuffer);
				
				value = cal.txtField1.getText();
				double temp = Double.parseDouble(value);
				
				if(firstOperation)
				{
					operand1 = temp;
					firstOperation = false;
					previousOperation = '-';
				}
				else 
				{
					if (previousOperation == '+') 
					{
						operand1 = operand1 + temp;
						previousOperation = '-';		// Changing previousOperation to currentOperation
					}
					else if (previousOperation == '-')
					{
						operand1 = operand1 - temp;
						previousOperation = '-';
					}
					else if (previousOperation == '/') 
					{
						operand1 = operand1 / temp;
						previousOperation = '-';
					}
					else if (previousOperation == '*')
					{
						operand1 = operand1 * temp;
						previousOperation = '-';
					}
				}
				
				operation = true;
				
				display = display + operand1;
				cal.txtField1.setText(display);
			}
			else if (source.equals("DIVIDE ( / )"))
			{
				tempBuffer = cal.txtField2.getText();
				tempBuffer += "/";
				cal.txtField2.setText(tempBuffer);
				
				value = cal.txtField1.getText();
				double temp = Double.parseDouble(value);
				
				if(firstOperation)
				{
					operand1 = temp;
					firstOperation = false;
					previousOperation = '/';
				}
				else 
				{
					if (previousOperation == '+') 
					{
						operand1 = operand1 + temp;
						previousOperation = '/';		// Changing previousOperation to currentOperation
					}
					else if (previousOperation == '-')
					{
						operand1 = operand1 - temp;
						previousOperation = '/';
					}
					else if (previousOperation == '/') 
					{
						operand1 = operand1 / temp;
						previousOperation = '/';
					}
					else if (previousOperation == '*')
					{
						operand1 = operand1 * temp;
						previousOperation = '/';
					}
				}
				
				operation = true;
				
				display = display + operand1;
				cal.txtField1.setText(display);
			}
			else if (source.equals("MULTIPLY ( * )"))
			{
				tempBuffer = cal.txtField2.getText();
				tempBuffer += "*";
				cal.txtField2.setText(tempBuffer);
				
				value = cal.txtField1.getText();
				double temp = Double.parseDouble(value);
				if(firstOperation)
				{
					operand1 = temp;
					firstOperation = false;
					previousOperation = '*';
				}
				else 
				{
					if (previousOperation == '+') 
					{
						operand1 = operand1 + temp;
						previousOperation = '*';		// Changing previousOperation to currentOperation
					}
					else if (previousOperation == '-')
					{
						operand1 = operand1 - temp;
						previousOperation = '*';
					}
					else if (previousOperation == '/') 
					{
						operand1 = operand1 / temp;
						previousOperation = '*';
					}
					else if (previousOperation == '*')
					{
						operand1 = operand1 * temp;
						previousOperation = '*';
					}
				}
				
				operation = true;
				
				display = display + operand1;
				cal.txtField1.setText(display);
			}
			else if (source.equals("="))
			{
				value = cal.txtField1.getText();
				operand2 = Double.parseDouble(value);
				
				if (previousOperation == '+') 
				{
					operand1 = operand1 + operand2;
				}
				else if (previousOperation == '-')
				{
					operand1 = operand1 - operand2;
				}
				else if (previousOperation == '/')
				{
					operand1 = operand1 / operand2;
				}
				else if (previousOperation == '*')
				{
					operand1 = operand1 * operand2;
				}

				cal.txtField1.setText("Answer: " + operand1);
				operand1 = 0;
				operand2 = 0;
			}
			else if (source.equals("Square"))
			{
				value = cal.txtField1.getText();
				operand1 = Double.parseDouble(value);
				operand1 = Math.pow(operand1, 2.0);
				cal.txtField1.setText("" + operand1);
			}
			else if (source.equals("Square Root"))
			{
				value = cal.txtField1.getText();
				operand1 = Double.parseDouble(value);
				operand1 = Math.pow(operand1, 0.5);
				cal.txtField1.setText("" + operand1);
			}
			else if (source.equals("Clear ALL"))
			{
				firstOperation = true;
				cal.txtField1.setText("");
				cal.txtField2.setText("");
				tempBuffer = "";
			}
			else if (source.equals("BackSpace"))
			{
				String value2 = cal.txtField2.getText();
				String display2 = "";
				value = cal.txtField1.getText();
				
				for (int i = 0; i < value2.length() - 1; i++) 
				{
					display2 += value2.charAt(i);
				}
				for (int i = 0; i < value.length() - 1; i++)
				{
					display += value.charAt(i);
				}
				
				cal.txtField2.setText(display2);
				cal.txtField1.setText(display);
			}
			else if (source.equals(".") || source.equals("1") || source.equals("2") || source.equals("3") || source.equals("4") || source.equals("5") || source.equals("6") || source.equals("7") || source.equals("8") || source.equals("9") || source.equals("0"))
			{
				tempBuffer = cal.txtField2.getText();
				tempBuffer += source;
				cal.txtField2.setText(tempBuffer);
				if (operation)
				{
					display = "";
					cal.txtField1.setText(display);
					operation = false;
				}
				
				display = cal.txtField1.getText();
				display += source;
				cal.txtField1.setText(display);
			}
		}
		catch (Exception e)
		{
			System.out.println("\nMalicious Input\n");
			System.out.println("Click CLEAR ALL and Re-Enter\n");
		}
	}
}

class NumberButtons extends JPanel
{
	JButton buttonArray [];
	EventHandling eh1;
	Font fVar1;
	
	NumberButtons ()
	{
		fVar1 = new Font("Arial", Font.BOLD, 36);
		eh1 = new EventHandling();
		
		buttonArray = new JButton[12];
		
		this.setLayout(new GridLayout(4, 3));

		for (int i = 0; i < 10; i++)
		{	
			buttonArray[i] = new JButton("" + i);
			buttonArray[i].setFont(fVar1);
			this.add(buttonArray[i]);
			buttonArray[i].addActionListener(eh1);
		}
		
		this.add((buttonArray[10] = new JButton(".")));
		buttonArray[10].setFont(fVar1);
		buttonArray[10].addActionListener(eh1);
		this.add((buttonArray[11] = new JButton("=")));
		buttonArray[11].setFont(fVar1);
		buttonArray[11].addActionListener(eh1);
	}
}

class SignButtons extends JPanel
{
	JButton signArray [];
	EventHandling eh1;
	Font fVar1;
	
	SignButtons ()
	{
		fVar1 = new Font("Arial", Font.BOLD, 36);
		eh1 = new EventHandling();
		
		signArray = new JButton[4];
		
		this.setLayout(new GridLayout(4, 1));

		this.add((signArray[0] = new JButton("ADD ( + )")));
		signArray[0].setFont(fVar1);
		signArray[0].addActionListener(eh1);
		this.add((signArray[1] = new JButton("SUB ( - )")));
		signArray[1].setFont(fVar1);
		signArray[1].addActionListener(eh1);
		this.add((signArray[2] = new JButton("DIVIDE ( / )")));
		signArray[2].setFont(fVar1);
		signArray[2].addActionListener(eh1);
		this.add((signArray[3] = new JButton("MULTIPLY ( * )")));
		signArray[3].setFont(fVar1);
		signArray[3].addActionListener(eh1);
	}
}

class ExtraButtons extends JPanel
{
	JButton extraArray [];
	EventHandling eh1;
	Font fVar1;
	
	ExtraButtons ()
	{
		fVar1 = new Font("Arial", Font.BOLD, 36);
		eh1 = new EventHandling();
		extraArray = new JButton[4];
		
		this.setLayout(new GridLayout(4, 1));

		this.add((extraArray[0] = new JButton("Square")));
		extraArray[0].setFont(fVar1);
		extraArray[0].addActionListener(eh1);
		this.add((extraArray[1] = new JButton("Square Root")));
		extraArray[1].setFont(fVar1);
		extraArray[1].addActionListener(eh1);
		this.add((extraArray[2] = new JButton("Clear ALL")));
		extraArray[2].setFont(fVar1);
		extraArray[2].addActionListener(eh1);
		this.add((extraArray[3] = new JButton("BackSpace")));
		extraArray[3].setFont(fVar1);
		extraArray[3].addActionListener(eh1);
	}
}

class Calculator extends JFrame
{
	NumberButtons nbPanel;
	SignButtons signPanel;
	ExtraButtons extrafeaturesPanel;

	static JTextField txtField1, txtField2;
	
	Calculator ()
	{
		
		nbPanel = new NumberButtons();
		signPanel = new SignButtons();
		extrafeaturesPanel = new ExtraButtons();
		txtField1 = new JTextField(20);
		txtField2 = new JTextField(20);
		
		Font fontVar = new Font("Times New Roman", Font.BOLD, 48);
		txtField1.setFont(fontVar);
		Font fontVar1 = new Font("Times New Roman", Font.BOLD, 32);
		txtField2.setFont(fontVar1);
		
		txtField1.setHorizontalAlignment(JTextField.RIGHT);
		txtField2.setHorizontalAlignment(JTextField.RIGHT);

		// Layouting
		this.add(txtField1, BorderLayout.NORTH);
		this.add(txtField2, BorderLayout.SOUTH);
		new EventHandling(this);
		
		this.add(extrafeaturesPanel, BorderLayout.WEST);
		this.add(nbPanel, BorderLayout.CENTER);
		this.add(signPanel, BorderLayout.EAST);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1280, 720);
		this.setVisible(true);
	}
}

public class AssignmentNo9_Calculator_R7
{
	public static void main(String [] args)
	{
		new Calculator();
	}
}
