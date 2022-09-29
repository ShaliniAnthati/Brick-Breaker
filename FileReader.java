package finalbrick;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class FileReader 
{
    public static ArrayList<Integer>OpenAndRead() 
    {
        ArrayList<Integer> leaderboardList=new ArrayList<Integer>();
        try
        { 
            BufferedReader br=new BufferedReader(new java.io.FileReader("src/finalbrick/leaderboard.txt"));
            String line=null;
            while((line=br.readLine())!=null)
            {    
                Integer result=Integer.parseInt(line);
                leaderboardList.add(result);
            }
            br.close();
        } 
        catch(FileNotFoundException e)
        {
            System.err.println("leaderboard.txt not found");
        } 
        catch(IOException e) 
        {
            System.err.println("Unable to read file");
        }
        return leaderboardList;
    }
}