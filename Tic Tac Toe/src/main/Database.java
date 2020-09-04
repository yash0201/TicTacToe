package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Database 
{
	private Formatter file;

	public void createFile()
	{
		try 
		{
			file = new Formatter("settings.t");
		} 
		catch (FileNotFoundException e) 
		{
			JOptionPane.showMessageDialog(null,"Error");
		}
	}
	public void addRecords(int sound, int undo, String player1, String player2, int theme)
	{
		file.format("s : %s u : %s p1 : %s p2 : %s theme : %s", sound+"", undo+"", player1, player2, theme+"");
	}
	public String[] readFile()
	{
		Scanner scan;
		String sound="",undo="",player1="",player2="",theme="";
		try
		{
			scan = new Scanner(new File("settings.t"));
			scan.next();
			scan.next();
			sound = scan.next();
			scan.next();
			scan.next();
			undo = scan.next();
			scan.next();
			scan.next();
			player1 = scan.next();
			scan.next();
			scan.next();
			player2 = scan.next();
			scan.next();
			scan.next();
			theme = scan.next();
		}
		catch (FileNotFoundException e)
		{
			
		}
		
		return new String[] {sound,undo,player1,player2,theme};
	}
	
	public void closeFile()
	{
		file.close();
	}
}
