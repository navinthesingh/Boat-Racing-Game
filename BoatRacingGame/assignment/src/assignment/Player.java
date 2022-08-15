package assignment;
import java.util.*;
import java.util.Comparator;

public class Player extends Obstacle{
    //attributes
    private String name;
    private String playernumber;
    private int position=1;
    private int turns=0;
    private int score=0;
    
    //constructor
    public Player() {
    	
    }
    
    public Player(String text) {
    	playernumber=text;
    }
    
    //setter getter
    public void setName(String nm) {
    	name=nm;
    }
    
    public String getName() {
    	return name;
    }
    
    public void setPosition(int pos) {
    	position=pos;
    }
    
    public int getPosition() {
    	return position;
    }
    
    public void setPlayerNumber(String text) {

        this.playernumber=text;
    }

    public String getPlayerNumber() {

        return playernumber;
    }
    
    public void setTurnCount(int t) {
    	turns=t;
    }
    
    public int getTurnCount(){
        return turns;
    }

    //other methods
  
    public void switchPlayer(){
        if(this.playernumber == "1"){
            this.playernumber = "2";
        }
        else if(this.playernumber == "2"){
            this.playernumber = "1";
        }
    }
    public void setScore(int score){
        this.score=score;
    }
    public int getScore(){
        return score;
    }
    public static Comparator<Player> PlayerScore = new Comparator<Player>() {
        public int compare(Player o1, Player o2) {
            int score1 = o1.getScore();
            int score2 = o2.getScore();
            return score2-score1;
        }
    };

    //toString
    @Override
    public String toString(){
        return (getPlayerNumber());
    }

}