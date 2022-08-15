package assignment;
import java.util.ArrayList;
import java.util.Random;

public class Map extends Game{
	//attributes

	private Random rng = new Random();
	//number variable for random number generator
	private int number=0;
	private int difficulty=0;
	//array list for map creation
	private ArrayList<Obstacle> map =new ArrayList();
	
	//constructor
	public Map(int d) {
		difficulty=d;
		//call methods to generate map and add traps and currents
		generateMap();
		generateTraps();
		generateCurrents();
	}
	
	//other methods
	//generate map
	public void generateMap() {
		//add start line
		map.add(new Start());
		for(int a=1; a<100; a++) {
			//add 100 spaces in between
			map.add(new Space());
		}
		//add finish line
		map.add(new Finish());
		//add player starting position
		map.set(1, super.player1);
		
	}
	
	//generate traps
	public void generateTraps() {
		System.out.println("generating traps");
		//check difficulty level to determine trap amount
		for(int b=0;b<(difficulty*10);b++) {
			//this line randomly decides where to place the current
			number=4+rng.nextInt(96);
			//while loop to check if the space is empty or not
			while(!(map.get(number) instanceof Space)) {    	
				number=4+rng.nextInt(96);
				}	
		//set the trap at the previously decided position
		map.set(number, new Trap());
		}	
	}
	
	//generate current
	public void generateCurrents() {
		System.out.println("generating currents");
		//loop 10 times to set 10 currents
		for(int c=0;c<10;c++) {
			//this line randomly decides where to place the current
			number=3+rng.nextInt(93);
			//check if the space is empty or not
			while(!(map.get(number) instanceof Space)) {    	
				number=2+rng.nextInt(96);
				}
			map.set(number, new Current());
		}	
	}
	
	//move player 1
	public int move1(int pos) {
		//check if player 1 is stepping on player 2
		checkPos1(pos);
		//erase previous position
		map.set(map.indexOf(super.player1), new Space());
		
		//check for current and trap
		while(map.get(pos) instanceof Current||map.get(pos) instanceof Trap) {
			if(map.get(pos)instanceof Current) {
			map.set(pos,super.player1);
			System.out.println(toString());
			map.set(pos, new Space());
			pos=pos+3;
			System.out.println("Current!");
			//check if player 1 is stepping on player 2
			checkPos1(pos);
			}
			
			if(map.get(pos)instanceof Trap) {
				map.set(pos, super.player1);
				System.out.println(toString());
				map.set(pos, new Space());
				pos=pos-3;
				System.out.println("Trap!");
				//check if player reverses into negative space
				if(pos<1) {
					pos=1;
				}
				//check if player 1 is stepping on player 2
				checkPos1(pos);
				
			}	
		}	
		//set new position
		map.set(pos, super.player1);
		//print map
		System.out.println(toString());
		System.out.println("Player 1 is currently in position: " +pos);
		
		//return position for future references
		return pos;
	}
	
	//move player 2
	public int move2(int pos) {
		//check if player 2 is stepping on player 1
		checkPos2(pos);
		//this try catch is here because at the start, player 2 object does not exist, so this try catch will allow the program to just skip over this line 
		try {
		//erase previous position	
		map.set(map.indexOf(super.player2), new Space());
		}catch (Exception e){
		}
				
		//check for current and trap
		while(map.get(pos) instanceof Current||map.get(pos) instanceof Trap) {
			if(map.get(pos)instanceof Current) {
				map.set(pos,super.player2);
				System.out.println(toString());
				map.set(pos, new Space());
				pos=pos+3;
				System.out.println("Current!");
				//check if player 2 is stepping on player 1
				checkPos2(pos);
				}
					
			if(map.get(pos) instanceof Trap) {
				map.set(pos, super.player2);
				System.out.println(toString());
				map.set(pos, new Space());
				pos=pos-3;
				System.out.println("Trap!");
				//check if player reverses into negative space
				if(pos<1) {
					pos=1;
				}
				//check if player 2 is stepping on player 1
				checkPos2(pos);
				}	
			}	
		//set new position
		map.set(pos, super.player2);
		System.out.println(toString());
		System.out.println("Player 2 is currently in position: " +pos);
		return pos;
	}
	
	//check if player 1 is stepping on player 2
	public void checkPos1(int pos) {
		if(map.get(pos) instanceof Player) {
			map.set(pos-1, super.player2);
			System.out.println("Stepped on Player 2!");
			map.set(pos, super.player1);
			}
	}
	
	//check if player 2 is stepping on player 1
	public void checkPos2(int pos) {
		if(map.get(pos) instanceof Player) {
			map.set(pos-1, super.player1);
			System.out.println("Stepped on Player 1!");
			map.set(pos, super.player2);
			}
	}
	
	//toString to print the map
	@Override
	public String toString() {
		String string = "";
		System.out.println("");
		for(int j=0; j<50; j++) {
	    	System.out.print(map.get(j)+ "  ");
	    	}
		System.out.println("");
		for(int j=50; j<101; j++) {
	    	System.out.print((map.get(j)+ "  "));
	    	}
	
		return(string);
	}	
	
		
}
