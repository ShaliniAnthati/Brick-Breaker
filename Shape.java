package finalbrick;
import java.awt.*;
public abstract class Shape 
{
    int width, height, posX, posY;  
    Shape(int width,int height) 
    {   
        this.width = width;
        this.height = height;
    }
    abstract void draw(Graphics graphics); 
}