package finalbrick;
import java.io.IOException;
import java.io.PrintWriter;
public class FileWriter 
{
    public void WriteCurrentScore(int finalScore)
    {
        try
        (
                java.io.FileWriter fw=new java.io.FileWriter("src/finalbrick/leaderboard.txt",true);
                PrintWriter out=new PrintWriter(fw))
        {
	            String str=Integer.toString(finalScore);
	            out.println(str);
	            out.close();

        } 
        catch(IOException e) 
         {
            System.err.println("Unable to read file");
        }
    }
}