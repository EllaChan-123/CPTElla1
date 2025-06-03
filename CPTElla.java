import arc.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;

public class CPTElla{
	public static void main(String[] args){
		Console con = new Console("Multiple Choice", 1280, 720);
		int intChoice = 0;
		String strName;
		
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
				//Create the variables needed later in the code
				String strPlayerName;
				String strQuiz;
				
				//Create the screen for multiple choice
				con.setDrawColor(new Color(233,238,246));
				con.fillRoundRect(180,150,900,400,50,50);
				Font fntChoice = con.loadFont("Alkia.ttf", 60);
				con.setDrawFont(fntChoice);
				con.setDrawColor(Color.BLACK);
				
				//Ask the player for their name and store it in a variable
				con.println("What is your name?");
				strPlayerName = con.readLine();
				
				//Print a list of available quizzes
				TextInputFile QuizesAvailable = new TextInputFile("quizzes.txt");
				con.println("Here is a list of the available quizes");
				while (QuizesAvailable.eof()==false){
					strQuiz = QuizesAvailable.readLine();
					con.println(strQuiz);
				}
			

				
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
