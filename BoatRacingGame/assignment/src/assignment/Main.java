package assignment;
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		//create controller class object
		Game game = new Game();
		String a= "1";
		String userInput;
		//initialize game menu
		game.Menu();
		
			
		//replay
		while(a=="1") {
    	//error handling
		Scanner input = new Scanner(System.in);
    	System.out.println("Would you like to play again? (Yes: 1/ No: 0)");
    	userInput=input.next();
    	
    	if(userInput.equals("1")) {
    		game = new Game();
    		game.Menu();
    	}
    	else if(userInput.equals("0")) {
    		System.out.println("Thank you for playing!");
    		a="0";
    	}
    	else {
    		System.out.println("Please enter a valid input");
    	}
		}
		
	}
}
