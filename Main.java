package finalbrick;
import javax.swing.*;
public class Main
{

    public static void main(String[] args) 
    {
        JFrame gameFrame=new JFrame();     
        gameFrame.setResizable(false);
        gameFrame.setVisible(true);
        gameFrame.setBounds(10,10,1000,600); 
        gameFrame.setTitle("Block Breaker");
        Game game=new Game(); 
        gameFrame.add(game) ;
    }
}
