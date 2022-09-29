package finalbrick;
import java.awt.*;

public class Blocks 
{
    public int b[][];  
    public int bWidth;
    public int bHeight; 
    public Blocks(int row,int col) 
    {
        b=new int[row][col];
        for(int i=0;i<b.length;i++) 
        {
            for(int j=0;j<b[0].length;j++) 
            {
                b[i][j]=1;    
            }
        }
        bWidth=540/col; 
        bHeight=150/row;
    }
    public void draw(Graphics2D graphics) 
    { 
        for(int i=0;i<b.length;i++) 
        {
            for(int j=0;j<b[0].length;j++)
            {
                if(b[i][j]>0) 
                {   
                    graphics.setColor(Color.red);
                    graphics.fillRect(j*bWidth+80,i*bHeight+50,bWidth,bHeight);  
                    graphics.setStroke(new BasicStroke(4));
                    graphics.setColor(Color.black);
                    graphics.drawRect(j*bWidth+80,i*bHeight+50,bWidth,bHeight); // draws again but just the strokes(outlines)
                }
            }
        }
    }
    public void setBlockValue(int value,int row,int col)
    {
        b[row][col]=value;
    }
}