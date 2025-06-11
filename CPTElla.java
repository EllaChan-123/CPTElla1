import arc.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;

public class CPTElla{
	public static void main(String[] args){
		Console con = new Console("Multiple Choice", 1280, 720);
		String strChoice = "M";
		String strName;
		
		//Don't quit the game until the users chooses to
		while(!strChoice.equalsIgnoreCase("Q")){
			//Display the main menu as soon as the game starts
			if (strChoice.equalsIgnoreCase("M")){
				con.clear();
				strChoice = MainMenu(strChoice, con);
				System.out.println(strChoice);
				
				//Clear the screen
				con.clear();
				con.setDrawColor(new Color(86,116,142));
				con.fillRect(0,0,1280,720);
					
				
			//Set up the game if the player chooses "play"
			}else if(strChoice.equalsIgnoreCase("P")){
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
				
				//Give bonus for thsoe who chose their name as statitan
				if (strPlayerName.equalsIgnoreCase("statitan")){
					dblScore = dblScore + 5;
					System.out.println("Bonus added");
				}
				
				//Write the score along side the name to a leaderboard
				TextOutputFile Leaderboard = new TextOutputFile("Leaderboard.txt",true);
				Leaderboard.println(strPlayerName);
				Leaderboard.println(strQuiz);
				Leaderboard.println(dblScore);
				
				Leaderboard.close();
				
				//return to main menu
				strChoice = "M";
			
			//Set up the leaderboard viewing	
			}else if(strChoice.equalsIgnoreCase("V")){
				
				//Load into method
				Leader(con);
				
				//Ask if they want to return to the main menu
				con.println("if you wish to return to the main menu, press M");
				strChoice = con.readLine();
				
			//Set the add quiz option
			}else if(strChoice.equalsIgnoreCase("A")){
				
				System.out.print("Quiz added");
				//Load the method for quiz creation
				CreateQuiz(con);
				
				strChoice = "M";
			
			//Secret menu	
			}else if(strChoice.equalsIgnoreCase("S")){
				con.println("Why did the robber jump in the shower?");
				con.println("They wanted to make a clean getaway");
				con.sleep(1200);
				strChoice = "M";
			}
			
		}
		System.out.println("Game ended");

			
	}
	
	public static String MainMenu(String strOption, Console con){
		//Load in appearence of the main menu
		BufferedImage imgMain = con.loadImage("MainScreen.png");
		
		//Draw the main screen
		con.drawImage(imgMain, 0, 0);
		
		//Get the user to choose one of the options	
		con.println("");
		strOption = con.readLine();
		

		return strOption;

		
	}
	
	//Create method for Playing
	public static double PlayGame (String strQuiz, String strName, Console con){
		int intQnum = 0;
		double dblRandom;
		String strQuestions[][];
		//Load imaged
		BufferedImage imgPlay = con.loadImage("ChoiceScreen.png");


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
		double dblCorrect = 0;
		double dblAsked = 0;
		double dblScore = 0;
		String strAnswer;
		
		//Instructions
		con.println("You will be shown a question and four possible answers, please type which one you think is correct into the answer box.");
		for(intCount = 0; intCount < intQnum; intCount++){
			//Display, user interface
			con.clear();
			Font fntDisplay = con.loadFont("Alkia.ttf",30);
			con.setDrawFont(fntDisplay);
			con.setDrawColor(new Color(207,226,243));
			con.fillRect(0,0,200, 50);
			
			//Load in the four colors image here
			con.drawImage(imgPlay,0,0);

			//Text
			con.setDrawColor(Color.WHITE);
			con.drawString(strName, 40, 650);
			con.drawString(strQuiz, 500, 650);
			//number score
			con.drawString(Double.toString(dblCorrect), 1120, 650);
			con.drawString("/",1170,650);
			con.drawString(Double.toString(dblAsked), 1200,650);
			//Percent score
			con.drawString(Double.toString(dblScore) + "%", 1000,650);
			
					
			//Draw questions and answers
			fntDisplay = con.loadFont("Alkia.ttf",70);
			con.drawString(strQuestions[intCount][0], 400, 70);
			con.drawString(strQuestions[intCount][1], 370, 280);
			con.drawString(strQuestions[intCount][2], 790, 280);
			con.drawString(strQuestions[intCount][3], 370, 480);
			con.drawString(strQuestions[intCount][4], 790, 480);
			
			//See if the answer they type in is correct
			con.println("Answer:");
			strAnswer = con.readLine();
			dblAsked = dblAsked + 1;
			if(strAnswer.equalsIgnoreCase(strQuestions[intCount][5])){
				dblCorrect = dblCorrect + 1;
				con.println("Correct");
			}else{
				con.println("Incorrect");
			}
			//Find the score in percentage
			dblScore = Math.round((dblCorrect/dblAsked)*100);
			System.out.println(dblScore);
			
			con.sleep(800);
								
		}
		
		return dblScore;
			
	}
	
	//Method for the leaderboard
	public static void Leader(Console con){
		String strLeader[][];
		int intNum = 0;
		
		//Load file
		TextInputFile InLead = new TextInputFile("Leaderboard.txt");
		//count the number of names in the leader board file

		while(InLead.eof() == false){
			InLead.readLine();
			intNum = intNum + 1;
		}
		intNum = intNum/3;
		System.out.println(intNum);
		InLead.close();
		
		//Load questions into array with the counted rows
		strLeader = new String[intNum][3];
		InLead = new TextInputFile("Leaderboard.txt");
		int intCount;
		
		//Load the information into arrays
		for(intCount = 0; intCount < intNum; intCount++){
			strLeader[intCount][0] = InLead.readLine();
			strLeader[intCount][1] = InLead.readLine();
			strLeader[intCount][2] = InLead.readLine();
			System.out.println(strLeader[intCount][2]);
		}
		InLead.close();
		
		//Sort the information by the percentage
		int intCount2;
		String strNameTemp;
		String strQuizTemp;
		String strPercentTemp;
		
		for(intCount2=0; intCount2 < intNum - 1; intCount2++){
			for(intCount = 0; intCount < intNum - 1; intCount++){
				//Convert the random number to integer and compare;
				if(Double.parseDouble(strLeader[intCount][2]) < Double.parseDouble(strLeader[intCount+1][2])){
					//Swap Names here
					strNameTemp = strLeader[intCount][0];
					strLeader[intCount][0] = strLeader[intCount+1][0];
					strLeader[intCount + 1][0] = strNameTemp;
					//Swap Quiz name here
					strQuizTemp = strLeader[intCount][1];
					strLeader[intCount][1] = strLeader[intCount+1][1];
					strLeader[intCount + 1][1] = strQuizTemp;
					//Swap Score here
					strPercentTemp = strLeader[intCount][2];
					strLeader[intCount][2] = strLeader[intCount+1][2];
					strLeader[intCount + 1][2] = strPercentTemp;
					System.out.println(strLeader[intCount][2]);
					
				}
			}
		}
		con.println("Leaderboard:");
		//Print out the leaderboard in order
		for(intCount = 0; intCount < intNum; intCount++){
			con.println(strLeader[intCount][0]+" - "+strLeader[intCount][1] +" - "+strLeader[intCount][2]);
		}
				
		
		
	}
	
	//Create a new quiz
	public static void CreateQuiz(Console con){
		String strQuizName;
		String strContinue = "y";
		String strCreate;
		int intCount;
		
		
		//Ask user for their quiz name
		con.println("What is the name of your quiz? (Make sure to add .txt to the end)");
		strQuizName = con.readLine();
		
		//Create/open both files, one for the name of the new quiz, one for the quiz itself
		TextOutputFile QuizCreation = new TextOutputFile(strQuizName,true);
		TextOutputFile AddQuiz = new TextOutputFile("quizzes.txt", true);
		
		//Append the name of the new quiz to quizzes.txt
		AddQuiz.println(strQuizName);
		AddQuiz.close();
		
		while(strContinue.equalsIgnoreCase("y")){
			//Ask for the question
			con.println("What is your question?");
			strCreate = con.readLine();
			QuizCreation.println(strCreate);
			
			//Ask for the Choices
			con.println("There are four options you must input");
			for(intCount = 1; intCount < 5; intCount++){
				con.println("Please input option "+intCount);
				strCreate = con.readLine();
				QuizCreation.println(strCreate);
			}
			
			//Ask for the correct answer
			con.println("What is the correct answer");
			strCreate = con.readLine();
			QuizCreation.println(strCreate);
			
			//Ask the user if they are done adding questions
			con.println("Do you want to continue adding questions? (y/n)");
			strContinue = con.readLine();
			
		}
		//Close file
		QuizCreation.close();
			
		
		
		
	}
}

