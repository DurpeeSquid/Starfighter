//Name -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class Ammo extends MovingThing
{
	private int speed;
	private Image image;

	public Ammo()
	{
		super(0,0);
		speed = 0;
	}

	public Ammo(int x, int y)
	{
		super(x, y);
		speed = 3;
	}

	public Ammo(int x, int y, int s)
	{
		super(x, y, 5, 20);
		speed = s;
		
		try
		{
			image = ImageIO.read(new File("Bullet.png"));
		}
		catch (Exception e)
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

	public void draw( Graphics window )
	{
		window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
	}
	
	
	public void move( String direction )
	{
		setY(getY() - speed);
	}

	public String toString()
	{
		return "";
	}
}
