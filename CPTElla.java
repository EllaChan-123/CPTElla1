import arc.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;

public class CPTElla{
	public static void main(String[] args){
		Console con = new Console("Multiple Choice", 1100, 700);
		boolean blnMain = true;
		int intChoice;
		
		MainMenu(blnMain, con);
		
		
		
	}
	public static void MainMenu(boolean blnMain, Console con){
		while(blnMain == true){
			con.setDrawColor(new Color(86, 116, 142));
			con.fillRect(0, 0, 1100, 700);
		}
		
	}
}
