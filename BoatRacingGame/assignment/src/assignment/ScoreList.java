package assignment;
import java.util.*;
import java.lang.*;
import java.io.*;

public class ScoreList{
    private Scanner sinput;
    private Formatter foutput;
    private ArrayList<Player> TopScores = new ArrayList<Player>();

    public void loadScores(){
        sinput = openInputFile("topscores.txt");
        readScores();
        closeInputFile(sinput);
    }

    public void storeScores()
    {
        foutput = openOutputFile("topscores.txt");
        writeScores();
        closeOutputFile(foutput);
    }

    public Scanner openInputFile(String filename)
    {
        Scanner tempinput=null;
        try
        {
            tempinput = new Scanner(new File(filename));
        }
        catch (FileNotFoundException fileNotFoundException)
        {
            System.err.println("Error opening file.");
            System.exit(1);
        }
        return tempinput;
    }

    public Formatter openOutputFile(String filename)
    {
        Formatter tempoutput=null;
        try
        {
            tempoutput = new Formatter(new File(filename));
        }
        catch (FileNotFoundException fileNotFoundException)
        {
            System.err.println("Error opening file.");
            System.exit(1);
        }
        return tempoutput;
    }

    public void closeInputFile(Scanner input)
    {
        if (input!=null)
            input.close();
    }

    public void closeOutputFile(Formatter output)
    {
        if (output!=null)
            output.close();
    }

    public void readScores() {
        try {
            while(sinput.hasNext()){
                Player player = new Player();
                player.setName(sinput.next());
                player.setScore(sinput.nextInt());
                TopScores.add(player);
            }
        }catch (NoSuchElementException elementException)
        {
            System.err.println("File improperly formed.");
            sinput.close();
            System.exit(1);
        }
        catch (IllegalStateException stateException)
        {
            System.err.println("Error reading from file.");
            System.exit(1);
        }
    }

    public void writeScores()
    {
        for ( int k = 0; k < TopScores.size(); k++ )
        {
            foutput.format("%s %d\n", TopScores.get(k).getName(), TopScores.get(k).getScore());
        }
    }

    public void addPlayer(Player a){
        TopScores.add(a);
    }

    public void sortScores(){
        Collections.sort(TopScores, Player.PlayerScore);
    }

    public String toString(){
        String a="";
        for(int i = 0; i<5; i++){
            a += String.format("%d. %s %d\n", i+1,TopScores.get(i).getName(),TopScores.get(i).getScore());
        }
        return a;
    }
}