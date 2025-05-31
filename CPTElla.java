import arc.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;

public class CPTElla{
	public static void main(String[] args){
		Console con = new Console("Multiple Choice", 1280, 720);
		boolean blnMain = true;
		int intChoice;
		
		//Display the main menu as soon as the game starts
		if (blnMain == true){
			MainMenu(blnMain, con);
		}
			
	}
	public static void MainMenu(boolean blnMain, Console con){
		//Load in appearence of the main menu
		BufferedImage imgMain = con.loadImage("MainScreen.png");
		//Make sure it displays as long as it is on the main menu
		while(blnMain == true){
			con.drawImage(imgMain, 0, 0);
			con.repaint();
		}
		
	}
}
