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
				
				dblScore = PlayGame(strQuiz, strPlayerName, con);
				

				

				
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
	public static double PlayGame (String strQuiz, String strName, Console con){
		int intQnum = 0;
		double dblRandom;
		String strQuestions[][];


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
			System.out.println(strQuestions[intCount][0]);
			System.out.println(strQuestions[intCount][6]);
		}
		Questions.close();
		System.out.println("finished loading file");
		//Bubble sort
		int intCount2;
		int intCount3;
		String strQuestionsTemp;
		String strAnswersTemp;
		String strRandNumTemp;
		
		for(intCount2=0; intCount2< intQnum - 1; intCount2++){
			for(intCount = 0; intCount < intQnum - 1; intCount++){
				//Convert the random number to integer and compare;
				if(Double.parseDouble(strQuestions[intCount][6]) > Double.parseDouble(strQuestions[intCount+1][6])){
					//Swap questions here
					strQuestionsTemp = strQuestions[intCount][0];
					strQuestions[intCount][0] = strQuestions[intCount+1][0];
					strQuestions[intCount + 1][0] = strQuestionsTemp;
	
					//Swap answers here
					for (intCount3 = 1; intCount3 < 6; intCount3++){
						strAnswersTemp = strQuestions[intCount][intCount3];
						strQuestions[intCount][intCount3] = strQuestions[intCount + 1][intCount3];
						strQuestions[intCount+1][intCount3] = strAnswersTemp;
					}
					//Swap the random number
					strRandNumTemp = strQuestions[intCount][6];
					strQuestions[intCount][6]= strQuestions[intCount+1][6];
					strQuestions[intCount+1][6] = strRandNumTemp;
				}
			}
		}
		

		//Gameplay
		int intCorrect = 0;
		int intAsked = 0;
		double dblScore = 0;
		
		for(intCount = 0; intCount < intQnum; intCount++){
			//Display, user interface
			con.clear();
			Font fntDisplay = con.loadFont("Alkia.ttf",30);
			con.setDrawFont(fntDisplay);
			con.setDrawColor(new Color(207,226,243));
			con.fillRect(0,0,200, 50);
			//Load in the four colors image here

			//Text
			con.setDrawColor(Color.WHITE);
			con.drawString(strName, 40, 650);
			con.drawString(strQuiz, 500, 650);
			
			//Draw questions and answers
			fntDisplay = con.loadFont("Alkia.ttf",50);
			con.drawString(strQuestions[intCount][0], 300, 70);
			con.drawString(strQuestions[intCount][1], 200, 130);
			con.drawString(strQuestions[intCount][2], 400, 130);
			con.drawString(strQuestions[intCount][3], 600, 130);
			con.drawString(strQuestions[intCount][4], 800, 130);
			
			//See if the answer they type in is correct
		}
		
		//Calculating score

		
		
		return dblScore;
			
	}
}
