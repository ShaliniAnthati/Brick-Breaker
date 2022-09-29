package finalbrick;
import java.awt.*;

public class Slider extends Shape
{

    Slider(int width,int height,int posX,int posY) 
    {
        super(width, height);   
        this.width = width;
        this.height = height;
        this.posX = posX;
        this.posY = posY;
    }
    void draw(Graphics graphics) 
    {  
        graphics.setColor(Color.yellow);
        graphics.fillRect(posX,posY,width,height);
    }
}