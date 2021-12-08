import java.lang.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

class EventHandling implements ActionListener, ItemListener
{
	static boolean isDecimal = false;
	static boolean isBinary = false;
	static boolean isHex = false;
	static boolean isOctal = false;
	
	EventHandling() {}
	
	
	int binaryToDecimal(String binary)
	{
		int decimalNumber = 0;
		int raiseTo = 0;
		
		for (int i = binary.length() - 1; i >= 0; i--)
		{
			if (binary.charAt(i) == '1')
				decimalNumber += (int)Math.pow(2.0, raiseTo++);
			else
				raiseTo++;
		}
		return decimalNumber;
	}
	int hexToDecimal(String hex)
	{
		int decimalNumber = 0;
		int raiseTo = 0;
		
		for (int i = hex.length() - 1; i >= 0; i--)
		{
			if (hex.charAt(i) == '1')
				decimalNumber += (int)Math.pow(16.0, raiseTo++);
			else if (hex.charAt(i) == '2')
				decimalNumber += (2 * (int)Math.pow(16.0, raiseTo++));
			else if (hex.charAt(i) == '3')
				decimalNumber += (3 * (int)Math.pow(16.0, raiseTo++));
			else if (hex.charAt(i) == '4')
				decimalNumber += (4 * (int)Math.pow(16.0, raiseTo++));
			else if (hex.charAt(i) == '5')
				decimalNumber += (5 * (int)Math.pow(16.0, raiseTo++));
			else if (hex.charAt(i) == '6')
				decimalNumber += (6 * (int)Math.pow(16.0, raiseTo++));
			else if (hex.charAt(i) == '7')
				decimalNumber += (7 * (int)Math.pow(16.0, raiseTo++));
			else if (hex.charAt(i) == '8')
				decimalNumber += (8 * (int)Math.pow(16.0, raiseTo++));
			else if (hex.charAt(i) == '9')
				decimalNumber += (9 * (int)Math.pow(16.0, raiseTo++));
			else if (hex.charAt(i) == 'a' || hex.charAt(i) == 'A')
				decimalNumber += (10 * (int)Math.pow(16.0, raiseTo++));
			else if (hex.charAt(i) == 'b' || hex.charAt(i) == 'B')
				decimalNumber += (11 * (int)Math.pow(16.0, raiseTo++));
			else if (hex.charAt(i) == 'c' || hex.charAt(i) == 'C')
				decimalNumber += (12 * (int)Math.pow(16.0, raiseTo++));
			else if (hex.charAt(i) == 'd' || hex.charAt(i) == 'D')
				decimalNumber += (13 * (int)Math.pow(16.0, raiseTo++));
			else if (hex.charAt(i) == 'e' || hex.charAt(i) == 'E')
				decimalNumber += (14 * (int)Math.pow(16.0, raiseTo++));
			else if (hex.charAt(i) == 'f' || hex.charAt(i) == 'F')
				decimalNumber += (15 * (int)Math.pow(16.0, raiseTo++));	
			else
				raiseTo++;
		}
		return decimalNumber;
	}
	int octalToDecimal(String octal)
	{
		int decimalNumber = 0;
		int raiseTo = 0;
		
		for (int i = octal.length() - 1; i >= 0; i--)
		{
			if (octal.charAt(i) == '1')
				decimalNumber += (int)Math.pow(8.0, raiseTo++);
			else if (octal.charAt(i) == '2')
				decimalNumber += (2 * (int)Math.pow(8.0, raiseTo++));
			else if (octal.charAt(i) == '3')
				decimalNumber += (3 * (int)Math.pow(8.0, raiseTo++));
			else if (octal.charAt(i) == '4')
				decimalNumber += (4 * (int)Math.pow(8.0, raiseTo++));
			else if (octal.charAt(i) == '5')
				decimalNumber += (5 * (int)Math.pow(8.0, raiseTo++));
			else if (octal.charAt(i) == '6')
				decimalNumber += (6 * (int)Math.pow(8.0, raiseTo++));
			else if (octal.charAt(i) == '7')
				decimalNumber += (7 * (int)Math.pow(8.0, raiseTo++));
			else
				raiseTo++;
		}
		return decimalNumber;
	}
	
	public void actionPerformed (ActionEvent ae)
	{
		String source = ae.getActionCommand();
		if (source.equals("RESULT"))
		{
			int decimalNumber = 0;
			int binaryNumber = 0;
			int octalNumber = 0;
			String hexNumber = "";
			if(isDecimal)
			{
				try
				{
					decimalNumber = Integer.parseInt(NumberFormats.txtFields[0].getText());
				}
				catch (Exception e)
				{
					JFrame frame1 = new JFrame();
					JOptionPane.showMessageDialog(frame1, "Enter int size numbers Only");
					frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
				
				NumberFormats.txtFields[1].setText("" + Integer.toBinaryString(decimalNumber));
				NumberFormats.txtFields[2].setText("" + Integer.toHexString(decimalNumber));
				NumberFormats.txtFields[3].setText("" + Integer.toOctalString(decimalNumber));
			}
			else if(isBinary)
			{
				boolean checkNumber = true;
				String str = NumberFormats.txtFields[1].getText();
				if (str.length() > 31)
					checkNumber = false;
				else if (str.length() <= 31)
				{
					for (int i = 0; i < str.length(); i++)
					{
						if (str.charAt(i) != '0' && str.charAt(i) != '1')
						{
							checkNumber = false;
							break;
						}
					}
				}
				if (checkNumber)
				{
					decimalNumber = binaryToDecimal(str);
					NumberFormats.txtFields[0].setText("" + decimalNumber);
					NumberFormats.txtFields[2].setText("" + Integer.toHexString(decimalNumber));
					NumberFormats.txtFields[3].setText("" + Integer.toOctalString(decimalNumber));
				}
				else
				{
					JFrame frame1 = new JFrame();
					JOptionPane.showMessageDialog(frame1, "Enter binary number string only( <=31 Characters)");
					frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
			}
			else if(isHex)
			{
				boolean checkNumber = true;
				String str = NumberFormats.txtFields[2].getText();
				if (str.length() >= 8)
					checkNumber = false;
				else if (str.length() < 8)
				{
					for (int i = 0; i < str.length(); i++)
					{
						if (str.charAt(i) != '0' && str.charAt(i) != '1' && str.charAt(i) != '2' &&
							str.charAt(i) != '3' && str.charAt(i) != '4' && str.charAt(i) != '5' &&
							str.charAt(i) != '6' && str.charAt(i) != '7' && str.charAt(i) != '8' &&
							str.charAt(i) != '9' && str.charAt(i) != 'a' && str.charAt(i) != 'b' &&
							str.charAt(i) != 'c' && str.charAt(i) != 'd' && str.charAt(i) != 'e' &&
							str.charAt(i) != 'f' &&
							str.charAt(i) != 'A' && str.charAt(i) != 'B' && str.charAt(i) != 'C' &&
							str.charAt(i) != 'D' && str.charAt(i) != 'E' && str.charAt(i) != 'F')
						{
							checkNumber = false;
							break;
						}
					}
				}
				if (checkNumber)
				{
					decimalNumber = hexToDecimal(str);
					NumberFormats.txtFields[0].setText("" + decimalNumber);
					NumberFormats.txtFields[1].setText("" + Integer.toBinaryString(decimalNumber));
					NumberFormats.txtFields[3].setText("" + Integer.toOctalString(decimalNumber));
				}
				else
				{
					JFrame frame1 = new JFrame();
					JOptionPane.showMessageDialog(frame1, "Enter hex number string only( <8 Characters)");
					frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
			}
			else if(isOctal)
			{
				boolean checkNumber = true;
				String str = NumberFormats.txtFields[3].getText();
				if (str.length() >= 11)
					checkNumber = false;
				else if (str.length() < 11)
				{
					for (int i = 0; i < str.length(); i++)
					{
						if (str.charAt(i) != '0' && str.charAt(i) != '1' && str.charAt(i) != '2' &&
							str.charAt(i) != '3' && str.charAt(i) != '4' && str.charAt(i) != '5' &&
							str.charAt(i) != '6' && str.charAt(i) != '7')
						{
							checkNumber = false;
							break;
						}
					}
				}
				if (checkNumber)
				{
					decimalNumber = octalToDecimal(str);
					NumberFormats.txtFields[0].setText("" + decimalNumber);
					NumberFormats.txtFields[1].setText("" + Integer.toBinaryString(decimalNumber));
					NumberFormats.txtFields[2].setText("" + Integer.toHexString(decimalNumber));
				}
				else
				{
					JFrame frame1 = new JFrame();
					JOptionPane.showMessageDialog(frame1, "Enter octal number string only( < 11 Characters)");
					frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
			}
		}
		else if (source.equals("RESET ALL"))
		{
			NumberFormats.txtFields[0].setText("");
			NumberFormats.txtFields[1].setText("");
			NumberFormats.txtFields[2].setText("");
			NumberFormats.txtFields[3].setText("");
		}
	}
	public void itemStateChanged(ItemEvent ie)
	{
		if(NumberFormatsButtons.radioButtons[0].isSelected()) 
		{
			isDecimal = true;
			isBinary = false;
			isHex = false;
			isOctal = false;
			NumberFormats.txtFields[0].setEditable(true);
			NumberFormats.txtFields[1].setEditable(false);
			NumberFormats.txtFields[2].setEditable(false);
			NumberFormats.txtFields[3].setEditable(false);
		}
		else if(NumberFormatsButtons.radioButtons[1].isSelected()) 
		{
			isDecimal = false;
			isBinary = true;
			isHex = false;
			isOctal = false;
			NumberFormats.txtFields[0].setEditable(false);
			NumberFormats.txtFields[1].setEditable(true);
			NumberFormats.txtFields[2].setEditable(false);
			NumberFormats.txtFields[3].setEditable(false);
		}
		else if(NumberFormatsButtons.radioButtons[2].isSelected()) 
		{
			isDecimal = false;
			isBinary = false;
			isHex = true;
			isOctal = false;
			NumberFormats.txtFields[0].setEditable(false);
			NumberFormats.txtFields[1].setEditable(false);
			NumberFormats.txtFields[2].setEditable(true);
			NumberFormats.txtFields[3].setEditable(false);
		}
		else if(NumberFormatsButtons.radioButtons[3].isSelected()) 
		{
			isDecimal = false;
			isBinary = false;
			isHex = false;
			isOctal = true;
			NumberFormats.txtFields[0].setEditable(false);
			NumberFormats.txtFields[1].setEditable(false);
			NumberFormats.txtFields[2].setEditable(false);
			NumberFormats.txtFields[3].setEditable(true);
		}
	}
}

class NumberFormats extends JPanel
{
	static JScrollPane scroll1 [] = new JScrollPane[4];
	static JTextField txtFields [] = new JTextField[4];
	static JLabel labels [] = new JLabel[4];
	static JPanel panels [] = new JPanel[4];
	
	NumberFormats()
	{
		Font font1 = new Font("Times New Roman", Font.PLAIN, 30);
		
		txtFields[0] = new JTextField(15);
		txtFields[0].setEditable(false);
		labels[0] = new JLabel("Decimal: ");
		labels[0].setFont(font1);
		txtFields[0].setFont(font1);
		scroll1[0] = new JScrollPane(txtFields[0]);
		panels[0] = new JPanel();
		panels[0].add(labels[0]);
		panels[0].add(scroll1[0]);
		
		txtFields[1] = new JTextField(21);
		txtFields[1].setEditable(false);
		labels[1] = new JLabel("Binary: ");
		labels[1].setFont(font1);
		txtFields[1].setFont(font1);
		scroll1[1] = new JScrollPane(txtFields[1]);
		panels[1] = new JPanel();
		panels[1].add(labels[1]);
		panels[1].add(scroll1[1]);
		
		txtFields[2] = new JTextField(15);
		txtFields[2].setEditable(false);
		labels[2] = new JLabel("Hex: ");
		labels[2].setFont(font1);
		txtFields[2].setFont(font1);
		scroll1[2] = new JScrollPane(txtFields[2]);
		panels[2] = new JPanel();
		panels[2].add(labels[2]);
		panels[2].add(scroll1[2]);
		
		txtFields[3] = new JTextField(15);
		txtFields[3].setEditable(false);
		labels[3] = new JLabel("Octal: ");
		labels[3].setFont(font1);
		txtFields[3].setFont(font1);
		scroll1[3] = new JScrollPane(txtFields[3]);
		panels[3] = new JPanel();
		panels[3].add(labels[3]);
		panels[3].add(scroll1[3]);
		
		this.setLayout(new GridLayout(4, 1));
		this.add(panels[0]);
		this.add(panels[1]);
		this.add(panels[2]);
		this.add(panels[3]);
	}
}

class NumberFormatsButtons extends JPanel
{
	static JRadioButton radioButtons [] = new JRadioButton[4];
	EventHandling eh;
	
	NumberFormatsButtons()
	{
		eh = new EventHandling();
		Font font1 = new Font("Times New Roman", Font.PLAIN, 20);
		
		radioButtons[0] = new JRadioButton("Decimal");
		radioButtons[0].setFont(font1);
		
		radioButtons[1] = new JRadioButton("Binary");
		radioButtons[1].setFont(font1);
		
		radioButtons[2] = new JRadioButton("Hex");
		radioButtons[2].setFont(font1);
		
		radioButtons[3] = new JRadioButton("Octal");
		radioButtons[3].setFont(font1);
		
		ButtonGroup bg1 = new ButtonGroup();
		bg1.add(radioButtons[0]);
		bg1.add(radioButtons[1]);
		bg1.add(radioButtons[2]);
		bg1.add(radioButtons[3]);
		
		//this.setLayout(new GridLayout(4, 1));
		
		this.add(radioButtons[0]);
		radioButtons[0].addItemListener(eh);
		this.add(radioButtons[1]);
		radioButtons[1].addItemListener(eh);
		this.add(radioButtons[2]);
		radioButtons[2].addItemListener(eh);
		this.add(radioButtons[3]);
		radioButtons[3].addItemListener(eh);
	}
}

class OperationButtons extends JPanel
{
	JButton button1;
	JButton button2;
	EventHandling eh;
	
	OperationButtons()
	{
		eh = new EventHandling();
		Font font1 = new Font("Times New Roman", Font.PLAIN, 30);
		
		button1 = new JButton("RESULT");
		button1.setFont(font1);
		
		button2 = new JButton("RESET ALL");
		button2.setFont(font1);
		
		this.add(button1);
		button1.addActionListener(eh);
		this.add(button2);
		button2.addActionListener(eh);
	}
}

class ConversionFrame extends JFrame
{	
	NumberFormats nf1;
	NumberFormatsButtons nfb1;
	OperationButtons ob1;
	
	ConversionFrame()
	{	
		nf1 = new NumberFormats();
		nfb1 = new NumberFormatsButtons();
		ob1 = new OperationButtons();
		
		//this.setLayout(new GridLayout(3, 1));
		this.add(nf1, BorderLayout.CENTER);
		this.add(nfb1, BorderLayout.NORTH);
		this.add(ob1, BorderLayout.SOUTH);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Conversion");
		this.setSize(640, 480);
		this.setVisible(true);
	}
}

public class Conversion
{
	public static void main(String args[])
	{
		ConversionFrame cf1 = new ConversionFrame();
		/*

		*/
	}
}