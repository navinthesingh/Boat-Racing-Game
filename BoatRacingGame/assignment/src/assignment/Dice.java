//this class decides the dice value
package assignment;
import java.util.Random;

public class Dice {
    private int value;
    
    public Dice() {
    	
    }
    
    public int Roll() {
    Random random = new Random();
    return value = random.nextInt(6) + 1;
}
}