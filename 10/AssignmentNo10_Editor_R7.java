import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;

class EventHandlingForFontController implements ActionListener, ListSelectionListener, ItemListener
{
	static String fName;
	static int fStyle;
	static int fSize;
	static Color fColor;
	
	FontController fc1;
	Editor editor1;

	EventHandlingForFontController () {}

	EventHandlingForFontController(Editor t_editor)
	{
		editor1 = t_editor;
	}
	
	EventHandlingForFontController (FontController t_fc1)
	{
		fc1 = t_fc1;
	}
	
	public void itemStateChanged (ItemEvent ie)
	{
		if (fc1.jrb1.isSelected()) fStyle = Font.PLAIN;
		else if (fc1.jrb2.isSelected()) fStyle = Font.BOLD;
		else if (fc1.jrb3.isSelected()) fStyle = Font.ITALIC;
		
		Font font = new Font(fName, fStyle, fSize);
		editor1.txtArea1.setFont(font);
	}
	
	public void valueChanged(ListSelectionEvent lse)
	{
		Font font = new Font(fName, fStyle, fSize);
		
		int i = fc1.myList.getSelectedIndex();
		
		if (i == 0) fName = "Arial";
		else if (i == 1) fName = "Comic Sans MS";
		else if (i == 2) fName = "Times New Roman";
		
		int j = fc1.myList2.getSelectedIndex();
		
		if (j == 0) fSize = 10;
		else if (j == 1) fSize = 15;
		else if (j == 2) fSize = 20;
		else if (j == 3) fSize = 25;
		else if (j == 4) fSize = 30;
		else if (j == 5) fSize = 35;
		else if (j == 6) fSize = 40;
		else if (j == 7) fSize = 45;
		else if (j == 8) fSize = 50;
		
		editor1.txtArea1.setFont(font);
		
	}
	
	public void actionPerformed (ActionEvent ae1)
	{
		String display = "";
		String source = ae1.getActionCommand();

		// Write this code in good manner
		try
		{
			int i = fc1.jcb1.getSelectedIndex();
			if (i == 0) fColor = Color.RED;
			else if (i == 1) fColor = Color.GREEN;
			else if (i == 2) fColor = Color.BLUE;
			else if (i == 3) fColor = Color.BLACK;
		}
		catch (Exception e) {}
		editor1.txtArea1.setForeground(fColor);
	}
}

class EventHandling implements ActionListener
{
	Editor editor1;
	static boolean isOpen = false;
	static File currentFileName = new File("");
	static boolean isEmptyCurrentFileName = true;
	
	EventHandling () {}
	
	EventHandling(Editor t_editor)
	{
		editor1 = t_editor;
	}

	public void actionPerformed (ActionEvent ae1)
	{
		String display = "";
		String source = ae1.getActionCommand();
		
		if (source.equals("New"))
		{
			// Saving the current file
			if (!isEmptyCurrentFileName)
			{
				String wholeContentOfTextArea = editor1.txtArea1.getText();
				
				if (!isOpen)
				{	
					File fileName = new File("");
					JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

					int returnVal = chooser.showSaveDialog(null);
					
					if (returnVal == JFileChooser.APPROVE_OPTION) 
					{
						fileName = chooser.getSelectedFile();
						currentFileName = fileName;
						isEmptyCurrentFileName = false;
						System.out.println("\nName: " + chooser.getSelectedFile().getName());
					}
				}

				fileWriting(currentFileName, wholeContentOfTextArea);			
				System.out.println("\nFile is successfully saved.\n");
				isOpen = true;
			}
			
			// Preparing for new file
			editor1.txtArea1.setText("");
			
			isOpen = false;
			currentFileName = new File("");
			isEmptyCurrentFileName = true;
		}
		else if (source.equals("Open"))
		{
			File fileName = new File("");
			JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

			int returnVal = chooser.showOpenDialog(null);
			
			if (returnVal == JFileChooser.APPROVE_OPTION) 
			{
				fileName = chooser.getSelectedFile();
				currentFileName = fileName;
				isEmptyCurrentFileName = false;
			}
			
			// Read File
			String wholeFileString = fileReading(fileName);
		
			// Set the Text Area
			editor1.txtArea1.setText(wholeFileString);
			
			isOpen = true;
		}
		else if(source.equals("Save"))
		{
			String wholeContentOfTextArea = editor1.txtArea1.getText();
			
			if (!isOpen)
			{	
				File fileName = new File("");
				JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

				int returnVal = chooser.showSaveDialog(null);
				
				if (returnVal == JFileChooser.APPROVE_OPTION) 
				{
					fileName = chooser.getSelectedFile();
					currentFileName = fileName;
					isEmptyCurrentFileName = false;
					System.out.println("\nName: " + chooser.getSelectedFile().getName());
				}
			}

			fileWriting(currentFileName, wholeContentOfTextArea);			
			System.out.println("\nFile is successfully saved.\n");
			isOpen = true;
		}
		else if(source.equals("Save As"))
		{
			String wholeContentOfTextArea = editor1.txtArea1.getText();
			
			File fileName = new File("");
			JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

			int returnVal = chooser.showSaveDialog(null);
			
			if (returnVal == JFileChooser.APPROVE_OPTION) 
			{
				fileName = chooser.getSelectedFile();
				System.out.println("\nName: " + chooser.getSelectedFile().getName());
			}	

			fileWriting(fileName, wholeContentOfTextArea);			
			System.out.println("\nFile is successfully saved.\n");
		}
		else if (source.equals("Cut")) editor1.txtArea1.cut();
		else if (source.equals("Copy")) editor1.txtArea1.copy();
		else if (source.equals("Paste")) editor1.txtArea1.paste();
		else if (source.equals("Font Controller"))
		{
			FontController fc1 = new FontController();
		}
	}
	
	public static String fileReading(File file)
	{
		FileReader fr1 = null;
		BufferedReader br1 = null;
		String wholeFileString = "";
		
		try
		{	
			fr1 = new FileReader(file);
			br1 = new BufferedReader(fr1);
			
			String str1 = "";
			
			while ((str1 = br1.readLine()) != null) 
			{
				wholeFileString += str1;
				wholeFileString += "\n";
			}
		}
		catch (Exception e)
		{
			System.out.println("\nFile doesn't exit.");
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
		
		return wholeFileString;
	}
	
	public static void fileWriting(File fileName, String wholeContentOfTextArea)
	{
		FileWriter fw1 = null;
		BufferedWriter bw1 = null;
			// Creating new file
			try
			{
				fw1 = new FileWriter(fileName);
				bw1 = new BufferedWriter(fw1);
				
				bw1.write(wholeContentOfTextArea);
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
				catch (Exception e){}
			}
	}
}

class FontController
{
	EventHandlingForFontController ehfc1;
	//JDesktopPane jdp;
	static JFrame jfrm;
	JList <String> myList;
	JList <String> myList2;
	JComboBox <String> jcb1;
	JRadioButton jrb1, jrb2, jrb3;
	
	FontController()
	{
		//jdp = new JDesktopPane("Font Controller");
		jfrm = new JFrame("Font Controller");
		
		ehfc1 = new EventHandlingForFontController(this);
	
		String textColor[] = {"RED", "GREEN", "BLUE", "BLACK"};
		String fontSize[] = {"10", "15", "20", "25", "30", "35", "40", "45", "50"};
		String fontNames[] = {"Arial", "Comic Sans MS", "Times New Roman"};
			
		myList = new JList<String>(fontNames);
		myList2 = new JList<String>(fontSize);
		jcb1 = new JComboBox<String>(textColor);
		
		jrb1 = new JRadioButton("PLAIN");
		jrb2 = new JRadioButton("BOLD");
		jrb3 = new JRadioButton("ITALIC");
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(jrb1);
		bg.add(jrb2);
		bg.add(jrb3);
			
		jfrm.setLayout(new FlowLayout());
		jfrm.add(myList);
		jfrm.add(myList2);
		jfrm.add(jrb1);
		jfrm.add(jrb2);
		jfrm.add(jrb3);
		jfrm.add(jcb1);
		
		myList.addListSelectionListener(ehfc1);
		myList2.addListSelectionListener(ehfc1);
		jrb1.addItemListener(ehfc1);
		jrb2.addItemListener(ehfc1);
		jrb3.addItemListener(ehfc1);
		jcb1.addActionListener(ehfc1);
				
		jfrm.setSize(400, 250);
		jfrm.setVisible(true);
	}
}

class Editor extends JFrame
{
	static JTextArea txtArea1;
	JMenuBar menuBar1;
	EventHandling eh1;
	
	Editor()
	{
		txtArea1 = new JTextArea();
		menuBar1 = new JMenuBar();
		
		EventHandling eh2 = new EventHandling(this);
		EventHandlingForFontController ehfc = new EventHandlingForFontController(this);

		addMenus("File");
		addMenus("Edit");
		addMenus("Font");
		
		this.setJMenuBar(menuBar1);
		this.add(txtArea1);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1280, 720);
		this.setVisible(true);
	}

	void addMenus(String menuName)
	{
		eh1 = new EventHandling();
		JMenu menu1 = new JMenu(menuName);
		
		if (menuName.equals("File"))
		{
			JMenuItem menuItem1[] = new JMenuItem[4];
			menuItem1[0] = new JMenuItem("New");
			menuItem1[1] = new JMenuItem("Open");
			menuItem1[2] = new JMenuItem("Save");
			menuItem1[3] = new JMenuItem("Save As");
			
			for (int i = 0; i < 4; i++)
			{
				menu1.add(menuItem1[i]);
				menuItem1[i].addActionListener(eh1);
			}
		}
		else if (menuName.equals("Edit"))
		{
			JMenuItem menuItem1[] = new JMenuItem[3];
			menuItem1[0] = new JMenuItem("Cut");
			menuItem1[1] = new JMenuItem("Copy");
			menuItem1[2] = new JMenuItem("Paste");
			
			for (int i = 0; i < 3; i++)
			{
				menu1.add(menuItem1[i]);
				menuItem1[i].addActionListener(eh1);
			}
		}
		else if (menuName.equals("Font"))
		{
			JMenuItem menuItem = new JMenuItem();
			menuItem = new JMenuItem("Font Controller");
			menu1.add(menuItem);
			menuItem.addActionListener(eh1);
		
		}
		menuBar1.add(menu1);
	}
}

public class AssignmentNo10_Editor_R7
{
	public static void main(String args [])
	{
		Editor editor1 = new Editor();
	}
}