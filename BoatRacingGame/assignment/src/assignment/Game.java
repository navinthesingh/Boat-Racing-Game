package assignment;
import java.io.File;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;

public class Game {
    //attributes
    private static int difficulty;  
    private int playerturn=1; //playerturn is used for deciding whose turn is it, not the amount of turns that has passed   
    private int userInput; //get user inputs
    private int temp=0;//temp variable
    private String name;//temp varable to print out player names
    //objects
    private Map map;
    private ScoreList scoreList = new ScoreList();
    Player player1 = new Player("P1");
    Player player2 = new Player("P2");
    private Scanner input = new Scanner(System.in);   
    private Dice dice = new Dice();   
    private Formatter foutput;
    
    //constructor
    public Game(){
        
    }
    
    //setter getter   
    public String getPlayer1() { //get player 1 and player 2 for Map class to use
    	return player1.getName();
    }
    
    public Player getPlayer2() {
    	return player2;
    }
    
    //other methods 
    public void Menu(){ //The starting screen
        System.out.println("################");
        System.out.println("#1.Start       #");
        System.out.println("#2.Exit        #");
        System.out.println("#3.Instruction #");
        System.out.println("################");
        
        try { //error handling
        Scanner input = new Scanner(System.in);
        userInput = input.nextInt();
        }catch (Exception e){
        	System.out.println("Pleae enter a valid input");
        	Menu();
        }
        switch(userInput) {       
        case 1:
        	printScore();
        	break;
        	
        case 2:
        	Exit();
        	break;
        
        case 3:
        	Instruction();
        	break;
        
        default:
        	System.out.println("Please enter a valid input");
        	Menu();
    }   
    }
    
    public void printScore(){
        try {  
        	
            File file = new File("topscores.txt");//File automatically created if file does not exist.
            if(file.createNewFile()){
                System.out.println("NEW USER DETECTED\ntopscores.txt generated");
              
            }
            else {
            System.out.println("Top 5 Players:");
            scoreList.loadScores();
            scoreList.sortScores();
            System.out.println(scoreList);
            }
        }catch(IOException e){
            System.out.println("IO Error detected");}
        catch(Exception e) {
        	
        }
        Start();
    }
     public void Start() {
        try {    
        Scanner input = new Scanner(System.in);      
        //enter difficulty section
        int k=1; //k is temporary variable used for error checking for difficulty
        while(k==1) {
        System.out.println("Please input difficulty: ");
        difficulty = (input.nextInt());
        if(difficulty <=3 && difficulty >=1) {
            k=0;
        }
        else {
            System.out.println("Please enter a number between 1 to 3");
        }
        }
        //enter name section
        System.out.println("Please enter Player 1 name: ");
        player1.setName(input.next());
        player1.setPlayerNumber("1");
        System.out.println("Please enter Player 2 name: ");
        player2.setName(input.next());
        player2.setPlayerNumber("2");
        map = new Map(difficulty);
       
        }catch(Exception a) {
            System.out.println("Please enter a number between 1 to 3");
            Start();
        }
        move();
    }
    
    
    public void Exit(){ //exit method if user enters 2
    	System.out.println("Thanks for playing!");
        System.exit(0);
    }
    
    public void Instruction() {//instruction method if user enters 3
    	System.out.println("********************");
    	System.out.println("****INSTRUCTIONS****");
    	System.out.println("");
    	System.out.println("Welcome to the boat game!");
    	System.out.println("The 'T' on the map stands for trap");
    	System.out.println("If you step on a trap, you will be set back three steps");
    	System.out.println("");
    	System.out.println("Difficulty levels determine how many traps will spawn on the map");
    	System.out.println("There are 3 difficulty levels");
    	System.out.println("");
    	System.out.println("If a Player steps on another player,");
    	System.out.println("The player that got stepped on will be set back one step");
    	System.out.println("");
    	System.out.println("The first player who reaches the finish line wins and gets bonus points depending on difficulty");
    	System.out.println("");
    	System.out.println("That is all for the instructions, good luck have fun!");
    	System.out.println("");
    	Menu();//loop back to main menu after instruction finish
    }
    
    
    
    public void move() {//move method  to process player movement
    
    	while(temp==0) {
    	int dicevalue = dice.Roll();//this is the variable that decides the dice value
    	
    	System.out.println("");
		System.out.println("Player " +PlayerTurn()+ " turn");
		System.out.print("Press Enter to roll the dice!");
		String pinput = input.nextLine();//detect whether if player has pressed the enter key or not
		
        if(playerturn == 1) {

            if (player1.getPosition() + dicevalue > 99) {
            	System.out.println("rolling");
                System.out.println(player1.getName()+ " rolled: " + dicevalue);
                player1.setTurnCount(player1.getTurnCount() + 101); //this set turn count is for scoring purposes
                System.out.println(player1.getName()+ " Has Won!");
                temp=1;
                Ending();//executes the ending method (method for calculating scores)               
            }           
            else{System.out.println(player1.getName()+ " rolled: " + dicevalue); //if player does not fulfill win condition, then move the player according to dice value           
            player1.setPosition(map.move1(player1.getPosition() + dicevalue)); //this line calls the move method in map        
            player1.setTurnCount(player1.getTurnCount() + 1);
           
            //set turn count           
            playerturn=2; //this line will prompt the program to swap to player 2 next    
            }
        }
               
        else if(playerturn == 2) {//check if it is player 2's turn or not
        	
            if (player2.getPosition() + dicevalue > 99) {//check win condition, if player 2 position exceeds 100, that means they won
                System.out.println(player2.getName()+ " rolled: " + dicevalue);
                //this set turn count is for scoring purposes
                player2.setTurnCount(player2.getTurnCount() + 101);
                System.out.println(player2.getName()+ " Has Won!");
                temp=1;
                Ending();//executes the ending method (method for calculating scores)
                             
            }
            
            else {
            System.out.println(player2.getName()+ " rolled: " + dicevalue);//if player does not fulfill win condition, then move the player according to dice value
            player2.setPosition(map.move2(player2.getPosition() + dicevalue)); //this line calls the move method in map
            player2.setTurnCount(player2.getTurnCount() + 1);
          
            playerturn=1;//this line will prompt the program to swap to player 1 next
            }
        }
    	}
    }
    
    //this method is for the main class to print out the player's name when its their turn
    public String PlayerTurn() {
    	if(playerturn==1) {
    		name=player1.getName();
    	}
    	
    	if(playerturn==2) {
    		name=player2.getName();
    	}
    	
    	return name;
    }
    
    //this method will be executed when the win conditions from the previous move method is fulfilled 
    public void Ending() {
    	
    	System.out.println("");
    	//the two setTurnCount here is for scoring purposes
    	player1.setScore(player1.getTurnCount()*difficulty);
    	player2.setScore(player2.getTurnCount()*difficulty);
    	//print final score for each player
    	System.out.println(player1.getName()+ "'s final score is: " +player1.getScore());
    	System.out.println(player2.getName()+ "'s final score is: " +player2.getScore());
        scoreList.addPlayer(player1);
        scoreList.addPlayer(player2);
        scoreList.sortScores();
        scoreList.storeScores();
    	//execute play again prompt
    	
    }
    
    
    }
    
   
    
    
