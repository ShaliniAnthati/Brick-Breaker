package finalbrick;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class Game extends JPanel implements ActionListener,KeyListener,MouseListener 
{   
    public int finalScore=0;
    public boolean ended; 
    private boolean ranOnce=false; //runs file writer
    private boolean start=false;  
    private int totalBlocks=21;   
    private int currentScore=0;   
    private Timer time;             
    private int delay=1;          
    private int sliderX=310;
    private int ballX=120;
    private int ballY=350;
    private int directionX=-1;
    private int directionY=-2;
    private Blocks b1;
    public Game() 
    {

        b1=new Blocks(3,7);  
        addKeyListener(this);   
        addMouseListener(this); 
        time=new Timer(delay,this);  
        time.start(); 
    }
    public void paint(Graphics graphics) 
    { 
        FileReader returnList=new FileReader();   
        ArrayList<Integer> freshList= returnList.OpenAndRead();
        Collections.sort(freshList,Collections.reverseOrder());    
        if(ended) 
        {
            this.finalScore=currentScore;  
        }
        graphics.setColor(Color.black);    
        graphics.fillRect(1,1,1000,600);
        b1.draw((Graphics2D) graphics);
        graphics.setColor(Color.green);   
        graphics.fillRect(0,0,3,592); //left border
        graphics.fillRect(0,0,1000,3); //up
        graphics.fillRect(691,0,3,592); //middle
        Shape sliderShape;
        sliderShape=new Slider(100,8,sliderX,550);
        sliderShape.draw(graphics);
        graphics.setColor(Color.yellow);  
        Shape ballShape;
        ballShape=new Ball(20,20,ballX,ballY);
        ballShape.draw(graphics);
        graphics.setFont(new Font("arial",Font.BOLD,14));
        graphics.drawString("Leaderboard:",720,160);
        graphics.setFont(new Font("arial",Font.BOLD,20));
        int textSpaceY=170;
        for(int x=0;x<10;x++) 
        {
            textSpaceY=textSpaceY+20;   
            graphics.drawString((x + 1)+" Place:       " + freshList.get(x),720,textSpaceY);
        }
        if(ended==true&&ranOnce==false)
        {  
            time.stop();
            System.out.println("ended = true and WriteCurrentScore() is ran once, score appended to text file is: " + finalScore);
            FileWriter fW=new FileWriter();
            fW.WriteCurrentScore(finalScore);
           ranOnce=true;
        } 
       
        ranOnce = false;
        if(totalBlocks<=0) 
        {
            start=false; 
            ended=true;
            directionX=0;
            directionY=0;
            graphics.setColor(Color.yellow);
            graphics.setFont(new Font("arial",Font.PLAIN,40));
            graphics.drawString("You Won!",210,300);
            graphics.setFont(new Font("arial",Font.PLAIN,20));
            graphics.drawString("your score: " +currentScore,230,330);
            graphics.drawString("Press Enter to restart!",230,370);
        }
        //gameover
        if(ballY>570) 
        {  
            start=false;  
            ended=true;
            directionX=0;
            directionY=0;
            graphics.setColor(Color.red);
            graphics.setFont(new Font("arial",Font.PLAIN,40));
            graphics.drawString("Game Over!",210,300);
            graphics.setFont(new Font("arial",Font.PLAIN,20));
            graphics.drawString("your score: " +currentScore,230,330);
            graphics.drawString("Press Enter to restart!",230,370);
        }
        graphics.setColor(Color.white);   
        graphics.setFont(new Font("arial",Font.PLAIN,20));
        graphics.drawString("Your score: "+currentScore,780,50);
        graphics.setColor(Color.gray);
        graphics.setFont(new Font("arial",Font.PLAIN,9));
        graphics.drawString("Final score: "+finalScore,600,20);
        graphics.drawString("Has ended? " +ended,500,20);
        graphics.drawString("Has started? " +start,400,20);
        graphics.setColor(Color.white);
        graphics.setFont(new Font("arial",Font.PLAIN,12));
        graphics.drawString("Controls: Left click on mouse and left arrow",720,85);
        graphics.drawString("on keyboard to go left.",720,100);
        graphics.drawString("Right click on mouse and right arrow to go",720,115);
        graphics.drawString("right. Press any stated above to start.",720,130);
    }
    public void actionPerformed(ActionEvent e)
    {
       time.start();   
        if(start) 
        {    
            if(new Rectangle(ballX,ballY,20,20).intersects(new Rectangle(sliderX,550,100,8))) 
            {
                directionY=-directionY;  
            }
            a:
            for(int i=0;i<b1.b.length;i++) 
            {
                for(int j=0;j<b1.b[0].length;j++)
                {
                	 if(b1.b[i][j]>0) 
                	 {    
                         int blockX=j*b1.bWidth+80;
                         int blockY=i* b1.bHeight+50;
                         int blockWidth=b1.bWidth;
                         int blockHeight=b1.bHeight;
                        Rectangle r=new Rectangle(blockX,blockY,blockWidth,blockHeight);                      
                        Rectangle ballR=new Rectangle(ballX,ballY,20,20);
                        Rectangle blockR=r;
                        if(ballR.intersects(blockR))
                        {  
                            b1.setBlockValue(0,i,j);  
                            totalBlocks--;  
                            currentScore+=100; 
                            if (ballX+19<=blockR.x||ballX+1>=blockR.x+blockR.width) //ball hitting top/bottom of block
                            {     
                                directionX=-directionX;   
                            } 
                            else 
                            {
                                directionY=-directionY;   
                            }
                            break a;   
                        }

                    }

                }
            }
            ballX+=directionX; //borders are hit   
            ballY+=directionY;
            if(ballX<0) //left
            {      
                directionX=-directionX;
            }
            if(ballY<0) //top
            {      
                directionY=-directionY;
            }
            if(ballX>670)//right
            {      
                directionX=-directionX;
            }
        }
        repaint();      
    }

    
    public void keyTyped(KeyEvent e) 
    {
    }

    public void keyReleased(KeyEvent e) 
    {
        if(e.getKeyCode()==KeyEvent.VK_ENTER)
        {    
           time.restart();
        }
    }

    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT &&!ended) 
        {   
            
            if(sliderX>=600) 
            { 
                sliderX=600;
            } 
            else
            {
                goRight();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT&&!ended)
        {   
            
            if(sliderX<10) 
            {  
                sliderX=10;  
            }
            else 
            {
                goLeft();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER) 
        {   
           
            if(!start) 
            {
                start=true;  
                ended=false;
                ranOnce=false;
                finalScore=0; 
                ballY=350;   
                ballX=120;
                directionY=-2;
                directionX=-1;
                sliderX=310;
                currentScore=0;
                totalBlocks=21;
                b1=new Blocks(3,7);
                repaint();
            }
        }
    }
    public void mousePressed(MouseEvent e)
    {
        requestFocus();
        if(e.getButton()==MouseEvent.BUTTON3&!ended) 
        {  //right
            requestFocus();
            if(sliderX>=600) 
            {
                sliderX=600;
            } 
            else 
            {
                goRight();
            }
        }
        if(e.getButton()==MouseEvent.BUTTON1&!ended)
        { //left
            requestFocus();
            if(sliderX<10)
            {
                sliderX=10;
            } 
            else 
            {
                goLeft();
            }
        }
    }

   
    public void mouseClicked(MouseEvent e) 
    {
    }

    
    public void mouseReleased(MouseEvent e)
    {
    }

    public void mouseEntered(MouseEvent e)
    {
    }

    public void mouseExited(MouseEvent e)
    {
    }

    public void goRight() 
    {
        start=true;
        ended=false;
        sliderX+=20;     
    }

    public void goLeft() 
    {
        start=true;
        ended=false;
        sliderX-=20; 
    }

}
