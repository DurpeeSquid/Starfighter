//Name -

import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Ship extends MovingThing
{
	private int speed;
	private Image image;

	public Ship()//default constructor
	{
		this(0,0,50,50,0);
	}

	public Ship(int x, int y)//overloaded constructor
	{
	   this(x, y, 50, 50 ,0);
	}

	public Ship(int x, int y, int s)
	{
	   this(x, y, 50, 50, s);
	   
	}

	public Ship(int x, int y, int w, int h, int s)
	{
		super(x, y, w, h);
		speed=s;
		try
		{
			
			image = ImageIO.read(new File("ship.jpg"));
		}
		catch(Exception e)
		{

		}
	}


	public void setSpeed(int s)
	{
	   speed = s;
	}

	public int getSpeed()
	{
	   return speed; 
	}

	public void move(String direction)
	{
    if(direction.equals("LEFT"))
    {
			setX(getX()-getSpeed());
    }
    else if(direction.equals("RIGHT"))
    {
			setX(getX() + getSpeed());
		}
		else if(direction.equals("UP"))
		{
			setY(getY() - getSpeed());
		}
		else if(direction.equals("DOWN"))
		{
			setY(getY() + getSpeed());
		}
           
           
	}

	public void draw( Graphics window )
	{
   		window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
	}

	public String toString()
	{
		return super.toString() + getSpeed();
	}
}
