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
				double dblScore;
			
				
				//Ask the player for their name and store it in a variable
				con.println("What is your name?");
				strPlayerName = con.readLine();
				
				//Print a list of available quizzes
				TextInputFile QuizzesAvailable = new TextInputFile("quizzes.txt");
				con.println("Here is a list of the available quizes");
				while (QuizzesAvailable.eof()==false){
					strQuiz = QuizzesAvailable.readLine();
					con.println(strQuiz);
				}
				QuizzesAvailable.close();
				
				//Have the player choose a quiz then use a method for the rest of the code
				con.println("Which quiz do you want to choose? Please type the full name including the .txt");
				strQuiz = con.readLine();
				
				dblScore = PlayGame(strQuiz, con);
				

				
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
	public static double PlayGame (String strQuiz, Console con){
		int intQnum = 0;
		double dblScore = 0;
		double dblRandom;
		String strQuestions[][];
		int intColumn;

		TextInputFile ChosenQuiz = new TextInputFile(strQuiz);
		
		//Count how many questions are inside the file
		while(ChosenQuiz.eof() == false){
			ChosenQuiz.readLine();
			intQnum = intQnum + 1;
		}
		intQnum = intQnum/6;
		System.out.println(intQnum);
		ChosenQuiz.close();
		
		//Load questions into array with the counted row
		strQuestions = new String[intQnum][7];
		TextInputFile Questions = new TextInputFile(strQuiz);
		int intCount;
		for(intCount = 0; intCount < intQnum; intCount++){
			strQuestions[intCount][0] = Questions.readLine();
			strQuestions[intCount][1] = Questions.readLine();
			strQuestions[intCount][2] = Questions.readLine();
			strQuestions[intCount][3] = Questions.readLine();
			strQuestions[intCount][4] = Questions.readLine();
			strQuestions[intCount][5] = Questions.readLine();
			//Randomize a number for the last array, covert it into string and save it
			dblRandom = Math.random();
			String strRandom = Double.toString(dblRandom);
			strQuestions[intCount][6] = strRandom;
			System.out.println(strQuestions[intCount][6]);
		}
		
		

		
		
		
		return dblScore;
			
	}
}
