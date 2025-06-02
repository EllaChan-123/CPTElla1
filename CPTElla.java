import arc.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;

public class CPTElla{
	public static void main(String[] args){
		Console con = new Console("Multiple Choice", 1280, 720);
		int intChoice = 0;
		
		//Run the game as long as the player does not choose to quit
		while(intChoice != 4){
			//Display the main menu as soon as the game starts
			if (intChoice == 0){
				intChoice = MainMenu(intChoice, con);
				System.out.println(intChoice);
				
				//Clear the screen
				con.clear();
				con.setDrawColor(new Color(86,116,142));
				con.fillRect(0,0,1280,720);
				
			//Set up the game if the player chooses "play"
			}else if(intChoice == 1){

				
			}
			
		}
			
	}
	
	public static int MainMenu(int intOption, Console con){
		//Load in appearence of the main menu
		BufferedImage imgMain = con.loadImage("MainScreen.png");
		
		//Draw the main screen
		con.drawImage(imgMain, 0, 0);
		con.println("");
		intOption = con.readInt();

		return intOption;
		//Get the user to choose one of the options
		
	}
	
	//Create method for Playing
}
