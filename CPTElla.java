import arc.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;

public class CPTElla{
	public static void main(String[] args){
		Console con = new Console("Multiple Choice", 1100, 700);
		boolean blnMain = true;
		int intChoice;
		
		//Display the main menu as soon as the game starts
		if (blnMain == true){
			MainMenu(blnMain, con);
		}
			
	}
	public static void MainMenu(boolean blnMain, Console con){
		//Create the appearence of the main menu
		
		while(blnMain == true){
			//Set background
			con.setDrawColor(new Color(86, 116, 142));
			con.fillRect(0, 0, 1100, 700);
			//Load name of the game
			
		}
		
	}
}
